import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView extends JFrame {
    private JPanel panel;
    private GameModel model;
    private JLabel numberLabel; // Agregamos un JLabel para mostrar el número
    private int currentNumber = 0; // Inicializamos el número en 0

    public GameView(GameModel model) {
        this.model = model;

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Personaje serverPersonaje = model.getServerPersonaje();
                Personaje clientPersonaje = model.getClientPersonaje();

                g.setColor(serverPersonaje.getColor());
                g.fillRect(serverPersonaje.getX(), serverPersonaje.getY(), 50, 50);
                g.setColor(clientPersonaje.getColor());
                g.fillRect(clientPersonaje.getX(), clientPersonaje.getY(), 50, 50);
            }
        };

        numberLabel = new JLabel(String.valueOf(currentNumber)); // Inicializamos el JLabel con el número actual
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(numberLabel, BorderLayout.SOUTH);

        setSize(400, 450); // Aumentamos la altura para dar espacio al JLabel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyPressed = e.getKeyChar();
                if (Character.isDigit(keyPressed)) {
                    currentNumber = Character.getNumericValue(keyPressed);
                    numberLabel.setText(String.valueOf(currentNumber)); // Actualizamos el número en el JLabel
                }
                model.moveServerPosition(e);
                panel.repaint();
            }
        });
    }
}
