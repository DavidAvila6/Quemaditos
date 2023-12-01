import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BallModel implements Observer {
    private static List<Ball> balls;

    public BallModel() {
        balls = new ArrayList<>();
        balls.add(new Ball(450, 200, 1, 1, 20, Color.GREEN, 900, 400));
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
        notifyObservers(); // Notifica a los observadores después de actualizar las bolas
    }

    private static void notifyObservers() {
    }

    public static void drawBalls(Graphics g) {
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    @Override
    public void update() {

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

    public static void addObserver(GameModel gameModel) {
    }
}
