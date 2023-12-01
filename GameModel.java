import java.awt.Color;

public class GameModel implements Observer {
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
                PersonajeModel.getServerPersonajes().add(new Personaje(x, y, Color.RED, true));

                // En algún lugar donde creas o modificas personajes
                Personaje personaje = new Personaje(y, y, null, false/* parámetros */);

                // Asigna una estrategia de movimiento (por ejemplo, MoverDerecha)
                personaje.setEstrategia(new MovimientoEstrategia.MoverDerecha());
                personaje.mover();

                personaje.setEstrategia(new MovimientoEstrategia.MoverIzquierda());
                personaje.mover();

                personaje.setEstrategia(new MovimientoEstrategia.MoverArriba());
                personaje.mover();

                personaje.setEstrategia(new MovimientoEstrategia.MoverAbajo());
                personaje.mover();

            }
        }

        // Ajusta la posición inicial de los personajes azules
        int startingX = 650;

        // Agrega 3 filas y 2 columnas de personajes del cliente a la derecha
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                int x = startingX + col * 100;
                int y = 50 + row * 130;
                PersonajeModel.getClientPersonajes().add(new Personaje(x, y, Color.BLUE, true));
            }
        }

        // Agrega GameModel como observador de PersonajeModel y BallModel
        PersonajeModel.addObserver(this);
        BallModel.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("¡El modelo del juego ha sido actualizado!");
    }
}
