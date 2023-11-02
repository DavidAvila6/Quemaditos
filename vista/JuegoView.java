package vista;

import javax.swing.*;

import modelo.JugadorModel;

import java.awt.*;

public class JuegoView extends JPanel {
    private JugadorModel jugador1;
    private JugadorModel jugador2;

    public JuegoView(JugadorModel jugador1, JugadorModel jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(jugador1.getX(), jugador1.getY(), 50, 50);
        g.setColor(Color.BLUE);
        g.fillRect(jugador2.getX(), jugador2.getY(), 50, 50);
    }
}
