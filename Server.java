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

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado");

            DataOutputStream clientOutput = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());

            while (true) {
                int xClient = clientInput.readInt();
                int yClient = clientInput.readInt();
                model.updateClientPosition(xClient, yClient);
                view.repaint();

                int xServer = model.getXServer();
                int yServer = model.getYServer();
                clientOutput.writeInt(xServer);
                clientOutput.writeInt(yServer);
                clientOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
