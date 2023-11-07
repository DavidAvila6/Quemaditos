import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        view.setTitle("Server");
        GameController controller = new GameController(model, view);

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor en espera...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());
                view.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int controlledServerIndex = model.getControlledServerIndex(); // Obtener el índice controlado desde el modelo
                        model.moveServerPosition(controlledServerIndex,e);
                        view.repaint();
    
                        try {
                            clientOutput.writeInt(controlledServerIndex);
                            clientOutput.writeInt(model.getXClient(controlledServerIndex));
                            clientOutput.writeInt(model.getYClient(controlledServerIndex));
                            clientOutput.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                 // Índice del personaje controlado por el cliente


                while (true) { //Toda esta parte se envia y se recibe datos del Servidor
                    int controlledServerIndex = model.getControlledServerIndex();
                    int controlledClientIndex = clientInput.readInt();
                    model.setControlledClientIndex(controlledClientIndex); // Recibe el índice controlado por el cliente
                    int xClient = clientInput.readInt();
                    int yClient = clientInput.readInt();

                    model.updateClientPosition(controlledClientIndex, xClient, yClient);
                    model.updateBalls();
                    
                    view.repaint();

                    int xServer = model.getXServer(controlledServerIndex);
                    int yServer = model.getYServer(controlledServerIndex);
                    clientOutput.writeInt(controlledServerIndex);
                    clientOutput.writeInt(xServer);
                    clientOutput.writeInt(yServer);
                    clientOutput.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
    }
}
