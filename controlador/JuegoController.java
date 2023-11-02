package controlador;
import modelo.JugadorModel;
import vista.JuegoView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class JuegoController implements KeyListener {
    private JugadorModel jugador1;
    private JugadorModel jugador2;
    private JuegoView vista;
    private ObjectOutputStream outputStream;

     public JuegoController(JugadorModel jugador1, JugadorModel jugador2, JuegoView vista, OutputStream output) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.vista = vista;

        try {
            this.outputStream = new ObjectOutputStream(output);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int dx = 0;
        int dy = 0;

        if (keyCode == KeyEvent.VK_W) {
            dy = -10;
        } else if (keyCode == KeyEvent.VK_S) {
            dy = 10;
        } else if (keyCode == KeyEvent.VK_A) {
            dx = -10;
        } else if (keyCode == KeyEvent.VK_D) {
            dx = 10;
        }

        jugador1.mover(dx, dy);
        vista.repaint();

        // Envía la posición del jugador1 al otro jugador a través del socket
        try {
            outputStream.writeObject(jugador1);
            outputStream.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
