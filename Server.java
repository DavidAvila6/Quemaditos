import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends JFrame {
    private GameModel model;
    private GameView view;
    private ServerSocket serverSocket;
    private List<DataOutputStream> clientOutputs = new ArrayList<>();

    public Server() {
        model = new GameModel();
        view = new GameView(model);
        view.setTitle("Server");

        JButton startServerButton = new JButton("Iniciar Servidor");
        JButton stopServerButton = new JButton("Detener");

        startServerButton.addActionListener(e -> startServer());
        stopServerButton.addActionListener(e -> stopServer());

        add(startServerButton);
        add(stopServerButton);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        stopServerButton.setEnabled(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor en espera...");

            new Thread(() -> {
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Cliente conectado");
                        DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
                        clientOutputs.add(clientOutput);

                        new Thread(() -> {
                            try {
                                DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());

                                while (true) {
                                    handleClientInput(clientInput);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopServer() {
        try {
            serverSocket.close();
            clientOutputs.forEach(output -> {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            clientOutputs.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        int controlledServerIndex = PersonajeModel.getControlledServerIndex();
        PersonajeModel.moveServerPosition(controlledServerIndex, e);
        view.repaint();

        broadcastServerState(controlledServerIndex);
    }

    private void handleClientInput(DataInputStream clientInput) throws IOException {
        int controlledClientIndex = clientInput.readInt();
        if (controlledClientIndex == -1) {
            // Valor especial que indica que el cliente se desconectó
            return;
        }

        PersonajeModel.setControlledClientIndex(controlledClientIndex);
        int xClient = clientInput.readInt();
        int yClient = clientInput.readInt();

        SwingUtilities.invokeLater(() -> {
            PersonajeModel.updateClientPosition(controlledClientIndex, xClient, yClient);
            BallModel.updateBalls();
            view.repaint();
        });

        int xServer = PersonajeModel.getXServer(controlledClientIndex);
        int yServer = PersonajeModel.getYServer(controlledClientIndex);

        broadcastServerState(controlledClientIndex, xServer, yServer);
    }

    private void broadcastServerState(int controlledIndex, int x, int y) {
        clientOutputs.forEach(output -> {
            try {
                output.writeInt(controlledIndex);
                output.writeInt(x);
                output.writeInt(y);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void broadcastServerState(int controlledIndex) {
        int xServer = PersonajeModel.getXServer(controlledIndex);
        int yServer = PersonajeModel.getYServer(controlledIndex);
        broadcastServerState(controlledIndex, xServer, yServer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Server());
    }
}