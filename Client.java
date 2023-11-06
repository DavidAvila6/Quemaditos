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
        GameController controller = new GameController(model, view);

        try {
            Socket socket = new Socket("localhost", 12345);
            DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());
            DataInputStream serverInput = new DataInputStream(socket.getInputStream());

            view.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int xClient = model.getXClient();
                    int yClient = model.getYClient();

                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        xClient -= 5;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        xClient += 5;
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        yClient -= 5;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        yClient += 5;
                    }

                    model.updateClientPosition(xClient, yClient);
                    view.repaint();

                    try {
                        serverOutput.writeInt(xClient);
                        serverOutput.writeInt(yClient);
                        serverOutput.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            while (true) {
                int xServer = serverInput.readInt();
                int yServer = serverInput.readInt();
                model.updateServerPosition(xServer, yServer);
                view.repaint();

                int xClient = model.getXClient();
                int yClient = model.getYClient();
                serverOutput.writeInt(xClient);
                serverOutput.writeInt(yClient);
                serverOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.setSize(400, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setVisible(true);
    }
}
