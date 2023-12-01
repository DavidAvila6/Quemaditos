import javax.swing.*;
import java.awt.*;

public class launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Launcher");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel titleLabel = new JLabel("Todos me la chupan");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));

            JButton serverButton = createStyledButton("Iniciar Servidor");
            serverButton.addActionListener(e -> SwingUtilities.invokeLater(Server::new));

            JButton clientButton = createStyledButton("Iniciar Cliente");
            clientButton.addActionListener(e -> SwingUtilities.invokeLater(Client::new));

            buttonPanel.add(serverButton);
            buttonPanel.add(clientButton);

            panel.add(titleLabel, BorderLayout.NORTH);
            panel.add(buttonPanel, BorderLayout.CENTER);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.black);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }
}
