import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView extends JFrame {
    private JPanel panel;
    private GameModel model;
    private JLabel numberLabel; // Agregamos un JLabel para mostrar el número

    public GameView(GameModel model) {
        this.model = model;

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Personaje personaje : model.getServerPersonajes()) {
                    g.setColor(personaje.getColor());
                    g.fillRect(personaje.getX(), personaje.getY(), 50, 50);
                }
                for (Personaje personaje : model.getClientPersonajes()) {
                    g.setColor(personaje.getColor());
                    g.fillRect(personaje.getX(), personaje.getY(), 50, 50);
                }
            }
        };

        numberLabel = new JLabel("Controlled Index: " + model.getControlledClientIndex());
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(numberLabel, BorderLayout.SOUTH);

        setSize(900, 450); // Aumentamos la altura para dar espacio al JLabel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyPressed = e.getKeyChar();
                if (Character.isDigit(keyPressed)) {
                    int newControlledIndex = Character.getNumericValue(keyPressed);
                    model.setControlledClientIndex(newControlledIndex); // Actualizamos el índice en el JLabel
                    model.setControlledServerIndex(newControlledIndex);
                    numberLabel.setText("Controlled Index Server: " + newControlledIndex); // Actualizamos el índice en el JLabel
                }
                model.moveServerPosition(model.getControlledServerIndex(), e);
                panel.repaint();
            }
        });
    }
}
