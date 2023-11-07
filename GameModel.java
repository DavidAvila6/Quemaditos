import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private List<Personaje> serverPersonajes;
    private List<Personaje> clientPersonajes;
    private int currentNumber = 0;

    private int controlledClientIndex = 0;
    private int controlledServerIndex = 0;

    public GameModel() {
        serverPersonajes = new ArrayList<>();
        clientPersonajes = new ArrayList<>();

        // Agrega 6 personajes del servidor a la derecha
        for (int i = 0; i < 6; i++) {
            serverPersonajes.add(new Personaje(50 + i * 60, 50, Color.RED, true));
        }

        // Agrega 6 personajes del cliente a la izquierda
        for (int i = 0; i < 6; i++) {
            clientPersonajes.add(new Personaje(200 + i * 60, 200, Color.BLUE, true));
        }
    }

    public List<Personaje> getServerPersonajes() {
        return serverPersonajes;
    }

    public List<Personaje> getClientPersonajes() {
        return clientPersonajes;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int number) {
        currentNumber = number;
    }

    public int getXServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public int getYServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public int getXClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public int getYClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public void updateServerPosition(int index, int x, int y) {
        if (index >= 0 && index < serverPersonajes.size()) {
            serverPersonajes.get(index).setX(x);
            serverPersonajes.get(index).setY(y);
        }
    }

    public void updateClientPosition(int index, int x, int y) {
        if (index >= 0 && index < clientPersonajes.size()) {
            clientPersonajes.get(index).setX(x);
            clientPersonajes.get(index).setY(y);
        }
    }

    public void moveClientPosition(int index, KeyEvent e) {
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
            }

            updateClientPosition(index, xClient, yClient);
        }
    }

    public void moveServerPosition(int index, KeyEvent e) {
        if (index >= 0 && index < serverPersonajes.size()) {
            System.out.println(index);
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
            }

            updateServerPosition(index, xServer, yServer);
        }
    }

    public int getControlledClientIndex() {
        return controlledClientIndex;
    }

    public void setControlledClientIndex(int index) {
        controlledClientIndex = index;
    }
    public int getControlledServerIndex() {
        return controlledServerIndex;
    }

    public void setControlledServerIndex(int index) {
        controlledServerIndex = index;
        
    }
       
}
