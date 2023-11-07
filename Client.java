import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        view.setTitle("Client");

        GameController controller = new GameController(model, view);

        try {
            Socket socket = new Socket("localhost", 12345);
            DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());
            DataInputStream serverInput = new DataInputStream(socket.getInputStream());

            view.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int controlledClientIndex = model.getControlledClientIndex(); // Obtener el índice controlado desde el modelo
                    
                    model.moveClientPosition(controlledClientIndex, e);
                    view.repaint();

                    try {
                        serverOutput.writeInt(controlledClientIndex);
                        serverOutput.writeInt(model.getXClient(controlledClientIndex));
                        serverOutput.writeInt(model.getYClient(controlledClientIndex));
                        serverOutput.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            while (true) {
                int controlledServerIndex = serverInput.readInt(); // Recibir el índice controlado
                model.setControlledServerIndex(controlledServerIndex); // Actualizar el índice controlado en el modelo
                int xServer = serverInput.readInt();
                int yServer = serverInput.readInt();
                
                model.updateServerPosition(controlledServerIndex, xServer, yServer);
                view.repaint();

                int controlledClientIndex = model.getControlledClientIndex(); // Obtener el índice controlado desde el modelo
                int xClient = model.getXClient(controlledClientIndex);
                int yClient = model.getYClient(controlledClientIndex);
                serverOutput.writeInt(controlledClientIndex);
                serverOutput.writeInt(xClient);
                serverOutput.writeInt(yClient);
                serverOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.setSize(900, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
