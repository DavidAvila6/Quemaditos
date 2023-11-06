import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView extends JFrame {
    private JPanel panel;
    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.fillRect(model.getXServer(), model.getYServer(), 50, 50);
                g.setColor(Color.BLUE);
                g.fillRect(model.getXClient(), model.getYClient(), 50, 50);
            }
        };

        add(panel);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                model.moveServerPosition(e);
                panel.repaint();
            }
        });
    }
}
