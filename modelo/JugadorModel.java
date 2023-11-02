package modelo;
public class JugadorModel {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
