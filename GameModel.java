import java.awt.event.KeyEvent;
public class GameModel {
    
    private int xServer = 50, yServer = 50;
    private int xClient = 200, yClient = 200;
    

    public int getXServer() {
        return xServer;
    }

    public int getYServer() {
        return yServer;
    }

    public int getXClient() {
        return xClient;
    }

    public int getYClient() {
        return yClient;
    }

    public void updateServerPosition(int x, int y) {
        xServer = x;
        yServer = y;
    }

    public void updateClientPosition(int x, int y) {
        xClient = x;
        yClient = y;
    }
    public void moveClientPosition(KeyEvent e) {
        int xClient = getXClient();
                    int yClient = getYClient();

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
        int xServer = getXServer();
        int yServer =getYServer();

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
