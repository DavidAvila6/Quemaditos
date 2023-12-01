import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class Client extends JFrame {
    private GameModel model;
    private GameView view;
    private DataOutputStream serverOutput;

    public Client() {
        model = new GameModel();
        view = new GameView(model);
        view.setTitle("Client");

        JButton connectButton = new JButton("Conectar como Cliente");
        JButton disconnectButton = new JButton("Desconectar");

        connectButton.addActionListener(e -> connectToServer());
        disconnectButton.addActionListener(e -> disconnectFromServer());

        add(connectButton);
        add(disconnectButton);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        disconnectButton.setEnabled(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void connectToServer() {
        String ipAddress = JOptionPane.showInputDialog("Ingrese la dirección IP del servidor:");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            try {
                Socket socket = new Socket(ipAddress, 12345);
                serverOutput = new DataOutputStream(socket.getOutputStream());

                new Thread(() -> {
                    try {
                        DataInputStream serverInput = new DataInputStream(socket.getInputStream());

                        while (true) {
                            handleServerInput(serverInput);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void disconnectFromServer() {
        try {
            if (serverOutput != null) {
                serverOutput.writeInt(-1); // Valor especial para indicar la desconexión al servidor
                serverOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        int controlledClientIndex = PersonajeModel.getControlledClientIndex();

        PersonajeModel.moveClientPosition(controlledClientIndex, e);
        view.repaint();

        try {
            serverOutput.writeInt(controlledClientIndex);
            serverOutput.writeInt(PersonajeModel.getXClient(controlledClientIndex));
            serverOutput.writeInt(PersonajeModel.getYClient(controlledClientIndex));
            serverOutput.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleServerInput(DataInputStream serverInput) throws IOException {
        int controlledServerIndex = serverInput.readInt();
        PersonajeModel.setControlledServerIndex(controlledServerIndex);
        int xServer = serverInput.readInt();
        int yServer = serverInput.readInt();

        SwingUtilities.invokeLater(() -> {
            PersonajeModel.updateServerPosition(controlledServerIndex, xServer, yServer);
            BallModel.updateBalls();
            view.repaint();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client());
    }
}
