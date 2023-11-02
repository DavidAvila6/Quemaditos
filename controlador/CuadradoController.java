package controlador;

import modelo.CuadradoModel;
import vista.CuadradoView;

public class CuadradoController {
    private CuadradoModel modelo;
    private CuadradoView vista;

    public CuadradoController(CuadradoModel modelo, CuadradoView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void moverCuadrado(int x, int y) {
        modelo.setX(x);
        modelo.setY(y);
        vista.actualizarVista(x, y);
    }
}

