import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        view.setTitle("Server");
        try {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                System.out.println("Servidor en espera...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente conectado");

                    DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
                    DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());
                    view.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int controlledServerIndex = PersonajeModel.getControlledServerIndex(); // Obtener el índice
                                                                                                   // controlado
                            // desde el modelo
                            PersonajeModel.moveServerPosition(controlledServerIndex, e);
                            view.repaint();

                            try {
                                clientOutput.writeInt(controlledServerIndex);
                                clientOutput.writeInt(PersonajeModel.getXClient(controlledServerIndex));
                                clientOutput.writeInt(PersonajeModel.getYClient(controlledServerIndex));
                                clientOutput.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    // Índice del personaje controlado por el cliente

                    while (true) { // Toda esta parte se envia y se recibe datos del Servidor
                        int controlledServerIndex = PersonajeModel.getControlledServerIndex();
                        int controlledClientIndex = clientInput.readInt();
                        PersonajeModel.setControlledClientIndex(controlledClientIndex); // Recibe el índice controlado
                                                                                        // por
                                                                                        // el cliente
                        int xClient = clientInput.readInt();
                        int yClient = clientInput.readInt();

                        PersonajeModel.updateClientPosition(controlledClientIndex, xClient, yClient);
                        BallModel.updateBalls();
                        

                        view.repaint();

                        int xServer = PersonajeModel.getXServer(controlledServerIndex);
                        int yServer = PersonajeModel.getYServer(controlledServerIndex);
                        clientOutput.writeInt(controlledServerIndex);
                        clientOutput.writeInt(xServer);
                        clientOutput.writeInt(yServer);
                        clientOutput.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
