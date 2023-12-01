import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallModel {
    private static List<Ball> balls;

    // Imágenes para las bolas
    private static ImageIcon ballImage = new ImageIcon("sprites/pokeball.gif"); // Cambia la ruta según tu imagen

    public BallModel() {
        balls = new ArrayList<>();
        balls.add(new Ball(450, 200, 1, 1, 20, ballImage, 900, 400));
    }

    public static void updateBalls() {
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

    public static void drawBalls(Graphics g) {
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
