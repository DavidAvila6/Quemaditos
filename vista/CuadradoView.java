package vista;
import javax.swing.*;
import java.awt.*;

public class CuadradoView {
    private JFrame frame;
    private JPanel panel;
    private JLabel cuadrado;

    public CuadradoView() {
        frame = new JFrame("Cuadrado Azul");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        panel = new JPanel();
        frame.add(panel);

        cuadrado = new JLabel();
        cuadrado.setPreferredSize(new Dimension(50, 50));
        cuadrado.setBackground(Color.RED);
        cuadrado.setOpaque(true);
        panel.add(cuadrado);

        frame.setVisible(true);
    }

    public void actualizarVista(int x, int y) {
        cuadrado.setBounds(x, y, 50, 50);
    }
}
