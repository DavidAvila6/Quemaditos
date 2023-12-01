public interface MovimientoEstrategia {
    void mover(Personaje personaje);

    class MoverIzquierda implements MovimientoEstrategia {
        @Override
        public void mover(Personaje personaje) {
            int x = personaje.getX();
            personaje.setX(x - 5);
        }
    }

    class MoverDerecha implements MovimientoEstrategia {
        @Override
        public void mover(Personaje personaje) {
            int x = personaje.getX();
            personaje.setX(x + 5);
        }
    }

    class MoverArriba implements MovimientoEstrategia {
        @Override
        public void mover(Personaje personaje) {
            int y = personaje.getY();
            personaje.setY(y - 5);
        }
    }

    class MoverAbajo implements MovimientoEstrategia {
        @Override
        public void mover(Personaje personaje) {
            int y = personaje.getY();
            personaje.setY(y + 5);
        }
    }
}
