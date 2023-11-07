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
        GameController controller = new GameController(model, view);

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor en espera...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());

                 // Índice del personaje controlado por el cliente


                while (true) {
                    int controlledServerIndex = model.getControlledServerIndex();
                    int controlledClientIndex = clientInput.readInt();
                    model.setControlledClientIndex(controlledClientIndex); // Recibe el índice controlado por el cliente
                    int xClient = clientInput.readInt();
                    int yClient = clientInput.readInt();
                    model.updateClientPosition(controlledClientIndex, xClient, yClient);
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

        view.setSize(900, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
