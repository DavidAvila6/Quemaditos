import javax.swing.*;
import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private double speedX;
    private double speedY;
    private int size;
    private ImageIcon image;
    private int maxX;
    private int maxY;

    public Ball(int x, int y, double speedX, double speedY, int size, ImageIcon image, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.size = size;
        this.image = image;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public void draw(Graphics g) {
        Image img = image.getImage();
        g.drawImage(img, x, y, size, size, null);
    }
}
