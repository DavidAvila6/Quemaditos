import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private int size;
    private Color color;
    private int maxX;
    private int maxY;
    private boolean agarrada;
    private double speed;

    public Ball(double x, double y, double speedX, double speedY, int size, Color color, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.size = size;
        this.color = color;
        this.maxX = maxX;
        this.maxY = maxY;
        this.agarrada = false;
        this.speed = 0.9991;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
        g.setColor(color);
        g.fillOval((int)x, (int)y, size, size);
    }

    public boolean isAgarrada() {
        return agarrada;
    }

    public void setAgarrada(boolean agarrada) {
        this.agarrada = agarrada;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void updateSpeed(){
        System.out.println( speedX+ "INCIIAL");
        System.out.println( speedY+ "INCIIAL");
        this.speedX *= speed; 
        this.speedY *= speed; 
        System.out.println( speedX+ "FINAL");
        System.out.println( speedY+ "FINAL");
    }
}
