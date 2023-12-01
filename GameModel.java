import java.awt.Color;

import javax.swing.ImageIcon;

public class GameModel {
    BallModel model = new BallModel();
    PersonajeModel model2 = new PersonajeModel();

    public GameModel() {

        // Ajusta la separación entre las tablas de personajes
        int separationX = 50;

        // Agrega 3 filas y 2 columnas de personajes del servidor a la izquierda
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                int x = 50 + col * 100 + separationX;
                int y = 50 + row * 130;
                // Utiliza la ruta de la imagen correspondiente para el servidor
                PersonajeModel.getServerPersonajes().add(new Personaje(x, y, new ImageIcon("sprites/ser.gif"), true));
            }
        }

        // Ajusta la posición inicial de los personajes azules
        int startingX = 650;

        // Agrega 3 filas y 2 columnas de personajes del cliente a la derecha
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                int x = startingX + col * 100;
                int y = 50 + row * 130;
                // Utiliza la ruta de la imagen correspondiente para el cliente
                PersonajeModel.getClientPersonajes().add(new Personaje(x, y, new ImageIcon("sprites/pika.gif"), true));
            }
        }
    }
}
