import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView extends JFrame {
    private JPanel panel;
    private JLabel numberLabel;

    // Ruta de la imagen de fondo
    private String backgroundImagePath = "sprites\\fondo2.png";// Cambia el nombre y la extensión según tu imagen

    

    public GameView(GameModel model) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dibuja el fondo de imagen
                Image backgroundImage = new ImageIcon(backgroundImagePath).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                // Dibuja los personajes con imágenes
                for (Personaje personaje : PersonajeModel.getServerPersonajes()) {
                   
                    Image characterImage = new ImageIcon( personaje.charimageS).getImage();
                    drawImage(g, characterImage, personaje.getX(), personaje.getY());
                }
                for (Personaje personaje : PersonajeModel.getClientPersonajes()) {
                    Image characterImage = new ImageIcon(personaje.charimageC).getImage();
                    drawImage(g, characterImage, personaje.getX(), personaje.getY());
                }

                // Dibuja las bolas
                BallModel.drawBalls(g);
            }

            // Método auxiliar para dibujar una imagen
            private void drawImage(Graphics g, Image image, int x, int y) {
                g.drawImage(image, x, y, 50, 50, this);
            }
        };

        numberLabel = new JLabel("Controlled Index: " + PersonajeModel.getControlledClientIndex());
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(numberLabel, BorderLayout.SOUTH);

        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyPressed = e.getKeyChar();
                if (Character.isDigit(keyPressed)) {
                    int newControlledIndex = Character.getNumericValue(keyPressed);
                    PersonajeModel.setControlledClientIndex(newControlledIndex);
                    PersonajeModel.setControlledServerIndex(newControlledIndex);
                    numberLabel.setText("Controlled Index: " + newControlledIndex);
                }
                panel.repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView(new GameModel()));
    }
}
