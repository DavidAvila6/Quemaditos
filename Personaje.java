import java.awt.*;

public class Personaje {
    private int x;
    private int y;
    private Color color;
    private boolean activo;
    private boolean agarraBola;

    public Personaje(int x, int y, Color color, boolean activo) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.activo = true; // Por defecto, el personaje está activo
        this.agarraBola = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color= color;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //metodso para las saber estado de coger bolas
    
    public boolean isAgarraBola() {
        return agarraBola;
    }

    public void setAgarraBola(boolean agarraBola) {
        this.agarraBola = agarraBola;
    }
}
