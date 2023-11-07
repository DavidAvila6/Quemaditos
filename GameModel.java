import java.awt.Color;
import java.awt.event.KeyEvent;
public class GameModel {
    private Personaje sP; //Server Personaje
    private Personaje cP; //Client Personajes
    private int currentNumber = 0;

    public GameModel() {
        sP = new Personaje(50, 50, Color.RED,true);
        cP = new Personaje(200, 200, Color.BLUE,true);
    }
    
    public Personaje getServerPersonaje() {
        return sP;
    }

    public Personaje getClientPersonaje() {
        return cP;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int number) {
        currentNumber = number;
    }

    public int getXServer() {
        return sP.getX();
    }

    public int getYServer() {
        return sP.getY();
    }

    public int getXClient() {
        return cP.getX();
    }

    public int getYClient() {
        return cP.getY();
    }

    public void updateServerPosition(int x, int y) {
        sP.setX(x);
        sP.setY(y);
    }

    public void updateClientPosition(int x, int y) {
        cP.setX(x);
        cP.setY(y);
    }
    public void moveClientPosition(KeyEvent e) {
                    int xClient = cP.getX();
                    int yClient = cP.getY();

                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        xClient -= 5;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        xClient += 5;
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        yClient -= 5;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        yClient += 5;
                    }

                    updateClientPosition(xClient, yClient);
    }
    public void moveServerPosition(KeyEvent e) {
        
        int xServer = sP.getX();
        int yServer = sP.getY();

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    xServer -= 5;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    xServer += 5;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    yServer -= 5;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    yServer += 5;
                }
        updateServerPosition(xServer, yServer);
    }
}
