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
}
