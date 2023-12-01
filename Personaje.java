import javax.swing.*;
import java.awt.Color;

public class Personaje {
    private int x;
    private int y;
    private Color color;
    private boolean activo;
    private boolean agarraBola;
    private ImageIcon image;

    public Personaje(int x, int y, ImageIcon image, boolean activo) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.activo = activo;
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
        this.color = color;
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

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    private MovimientoEstrategia estrategia;

    public void setEstrategia(MovimientoEstrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void mover() {
        if (estrategia != null) {
            estrategia.mover(this);
        }
    }
    // metodso para las saber estado de coger bolas

    public boolean isAgarraBola() {
        return agarraBola;
    }

    public void setAgarraBola(boolean agarraBola) {
        this.agarraBola = agarraBola;
    }

}
