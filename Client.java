import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        view.setTitle("Client");

        try {
            try (Socket socket = new Socket("localhost", 12345)) {
                DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());
                DataInputStream serverInput = new DataInputStream(socket.getInputStream());

                view.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int controlledClientIndex = PersonajeModel.getControlledClientIndex(); // Obtener el índice
                                                                                               // controlado desde
                        // el modelo
                        boolean agarraBola = PersonajeModel.getagarrabolaClient(controlledClientIndex);
                        PersonajeModel.moveClientPosition(controlledClientIndex, e);
                        view.repaint();

                        try {
                            serverOutput.writeInt(controlledClientIndex);
                            serverOutput.writeInt(PersonajeModel.getXClient(controlledClientIndex));
                            serverOutput.writeInt(PersonajeModel.getYClient(controlledClientIndex));
                            serverOutput.writeBoolean(agarraBola);
                            serverOutput.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                while (true) {
                    int controlledServerIndex = serverInput.readInt(); // Recibir el índice controlado
                    PersonajeModel.setControlledServerIndex(controlledServerIndex); // Actualizar el índice controlado
                                                                                    // en el
                                                                                    // modelo
                    int xServer = serverInput.readInt();
                    int yServer = serverInput.readInt();
                    boolean agarraServer = serverInput.readBoolean();
                    PersonajeModel.updateServerPosition(controlledServerIndex, xServer, yServer,agarraServer);

                    BallModel.updateBalls();
                    
                    view.repaint();

                    int controlledClientIndex = PersonajeModel.getControlledClientIndex(); // Obtener el índice                                                        // desde el
                    // modelo
                    int xClient = PersonajeModel.getXClient(controlledClientIndex);
                    int yClient = PersonajeModel.getYClient(controlledClientIndex);
                    boolean agarraBola = PersonajeModel.getagarrabolaClient(controlledClientIndex);
                    serverOutput.writeInt(controlledClientIndex);
                    serverOutput.writeInt(xClient);
                    serverOutput.writeInt(yClient);
                    serverOutput.writeBoolean(agarraBola);
                    serverOutput.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.setSize(900, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
