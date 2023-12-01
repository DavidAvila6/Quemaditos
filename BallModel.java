import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallModel {
    private static List<Ball> balls;
    static List<Personaje> serverPersonajes = PersonajeModel.getServerPersonajes();
    static List<Personaje> clientPersonajes = PersonajeModel.getClientPersonajes();
    

    // Imágenes para las bolas
    private static ImageIcon ballImage = new ImageIcon("sprites/pokeball.gif"); // Cambia la ruta según tu imagen

    public BallModel() {
        balls = new ArrayList<>();
        balls.add(new Ball(450, 200, -0.5, -0.5, 20, ballImage, 900, 400));
    }

    public static void updateBalls() {
        for (Ball ball : balls) {
            ball.move();
               
            // Detecta los bordes y hace que la bola rebote
            if (ball.getX() < 0 || ball.getX() > ball.getMaxX() - ball.getSize()) { 
                ball.updateSpeed();
                ball.setSpeedX(-ball.getSpeedX());
            }
            if (ball.getY() < 0 || ball.getY() > ball.getMaxY() - ball.getSize()) {
                ball.updateSpeed();
                ball.setSpeedY(-ball.getSpeedY());
            }

            ball.updateSpeed();
        }

        checkCollisions();
    }


    //COLISIONES BOLA CON PJ

    public static void checkCollisions() {
        List<Ball> balls = getBalls();
        

        for (Ball ball : balls) {
            for (Personaje personaje : serverPersonajes) {
                if (checkCollision(ball, personaje)) {
                    handleCollision(ball, personaje);
                }
            }

            for (Personaje personaje : clientPersonajes) {
                if (checkCollision(ball, personaje)) {
                    handleCollision(ball, personaje);
                }
            }
        }
    }

    private static boolean checkCollision(Ball ball, Personaje personaje) {
        // Verificar si la bola ha colisionado con el rectángulo del personaje
        return ball.getX() < personaje.getX() + 50 &&
               ball.getX() + ball.getSize() > personaje.getX() &&
               ball.getY() < personaje.getY() + 50 &&
               ball.getY() + ball.getSize() > personaje.getY();
    }

    private static void handleCollision(Ball ball, Personaje p) {
         if (!p.isAgarraBola()){
                eliminado(p);
            }else{
                ball.setX(p.getX());
                ball.setY(p.getY());
                if (p.isLanzar()){
                    ball.setSpeedX(-ball.getSpeedX());
                    ball.setSpeed(0.9991);
                    p.setAgarraBola(false);
                    
                }
            }
         
    }

    public static void eliminado(Personaje p){
        ImageIcon imageIcon;
        if (serverPersonajes.contains(p)) {
            
                if (!p.isAgarraBola()){
                    if (!p.isLanzar()){
                        imageIcon = new ImageIcon("sprites\\pokeball.gif");
                            p.setImage(imageIcon);
                            p.setX(-2000);
                    }else{
                        p.setLanzar(false);
            }
            
        }}  
        if (clientPersonajes.contains(p)) {
            if (!p.isAgarraBola()){
                imageIcon = new ImageIcon("sprites\\pokeball.gif");
                p.setImage(imageIcon);
                p.setX(-2000);
            }
        } 
    }

        //**************** */


    public void ballAgarrada(){

    }

    public static List<Ball> getBalls() {
        return balls;
    }

    public static void drawBalls(Graphics g) {
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    public int getXBalls(int index) {
        if (index >= 0 && index < balls.size()) {
            return (int)balls.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public int getYballs(int index) {
        if (index >= 0 && index < balls.size()) {
            return (int)balls.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }
}
