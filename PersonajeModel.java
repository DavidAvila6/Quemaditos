import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PersonajeModel {
    private static List<Personaje> serverPersonajes = new ArrayList<>();
    private static List<Personaje> clientPersonajes = new ArrayList<>();
    private static List<Socket> clientSockets = new ArrayList<>();

    private static int controlledClientIndex = 0;
    private static int controlledServerIndex = 0;

    // Ruta de la imagen del personaje


    public static List<Personaje> getServerPersonajes() {
        return serverPersonajes;
    }

    public static List<Personaje> getClientPersonajes() {
        return clientPersonajes;
    }

    public static List<Socket> getClientSockets() {
        return clientSockets;
    }

    public static void addClientSocket(Socket socket) {
        clientSockets.add(socket);
    }

    public static void clearClientSockets() {
        clientSockets.clear();
    }
    

    public static int getXServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static int getYServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static int getXClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static int getYClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static void updateServerPosition(int index, int x, int y) {
        if (index >= 0 && index < serverPersonajes.size()) {
            serverPersonajes.get(index).setX(x);
            serverPersonajes.get(index).setY(y);
        }
    }

    public static void updateClientPosition(int index, int x, int y) {
        if (index >= 0 && index < clientPersonajes.size()) {
            clientPersonajes.get(index).setX(x);
            clientPersonajes.get(index).setY(y);
        }
    }

    public static void moveClientPosition(int index, KeyEvent e) {
        if (index >= 0 && index < clientPersonajes.size()) {
            int xClient = clientPersonajes.get(index).getX();
            int yClient = clientPersonajes.get(index).getY();
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                xClient -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                xClient += 5;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                yClient -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                yClient += 5;
            } else if (e.getKeyChar() == 'e') {
                
            }

            updateClientPosition(index, xClient, yClient);
        }
    }

    public static void moveServerPosition(int index, KeyEvent e) {
        if (index >= 0 && index < serverPersonajes.size()) {
            int xServer = serverPersonajes.get(index).getX();
            int yServer = serverPersonajes.get(index).getY();

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                xServer -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                xServer += 5;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                yServer -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                yServer += 5;
            } else if (e.getKeyChar() == 'e') {
               
            }
            updateServerPosition(index, xServer, yServer);
        }
    }

    public static int getControlledClientIndex() {
        return controlledClientIndex;
    }

    public static void setControlledClientIndex(int index) {
        controlledClientIndex = index;
    }

    public static int getControlledServerIndex() {
        return controlledServerIndex;
    }

    public static void setControlledServerIndex(int index) {
        controlledServerIndex = index;
    }
}
