import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private List<Personaje> serverPersonajes;
    private List<Personaje> clientPersonajes;
    private List<Ball> balls;
    private int currentNumber = 0;

    private int controlledClientIndex = 0;
    private int controlledServerIndex = 0;

    public GameModel() {
        serverPersonajes = new ArrayList<>();
        clientPersonajes = new ArrayList<>();
        balls = new ArrayList<>();
        balls.add(new Ball(450, 200, 1, 1, 20, Color.GREEN, 900, 400));

    // Ajusta la separación entre las tablas de personajes
    int separationX = 50;

    // Agrega 3 filas y 2 columnas de personajes del servidor a la izquierda
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 2; col++) {
            int x = 50 + col * 100 + separationX;
            int y = 50 + row * 130;
            serverPersonajes.add(new Personaje(x, y, Color.RED, true));
        }
    }

    // Ajusta la posición inicial de los personajes azules
    int startingX = 650;

    // Agrega 3 filas y 2 columnas de personajes del cliente a la derecha
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 2; col++) {
            int x = startingX + col * 100;
            int y = 50 + row * 130;
            clientPersonajes.add(new Personaje(x, y, Color.BLUE, true));
        }
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
            } else if (e.getKeyChar() == 'e') {
                if (clientPersonajes.get(index).getColor()!=Color.PINK) {
                    clientPersonajes.get(index).setColor(Color.PINK);
                } else {
                    clientPersonajes.get(index).setColor(Color.BLUE);
                }
            }

            updateClientPosition(index, xClient, yClient);
        }
    }

    public void moveServerPosition(int index, KeyEvent e) {
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
                if (serverPersonajes.get(index).getColor()!=Color.ORANGE) {
                    serverPersonajes.get(index).setColor(Color.ORANGE);
                } else {
                    serverPersonajes.get(index).setColor(Color.RED);
                }
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

    public void updateBalls() {
        for (Ball ball : balls) {
            ball.move();
        // Detecta los bordes y hace que la bola rebote
        if (ball.getX() < 0 || ball.getX() > ball.getMaxX() - ball.getSize()) {
            ball.setSpeedX(-ball.getSpeedX());
        }
        if (ball.getY() < 0 || ball.getY() > ball.getMaxY() - ball.getSize()) {
            ball.setSpeedY(-ball.getSpeedY());
        }
        }
    }

    public void drawBalls(Graphics g) {
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    public int getXBalls(int index) {
        if (index >= 0 && index < balls.size()) {
            return balls.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public int getYballs(int index) {
        if (index >= 0 && index < balls.size()) {
            return balls.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }

}
       

