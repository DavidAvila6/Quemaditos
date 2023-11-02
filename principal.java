/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author David
 */
import modelo.CuadradoModel;
import vista.CuadradoView;
import controlador.CuadradoController;
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CuadradoModel modelo = new CuadradoModel();
        CuadradoView vista = new CuadradoView();
        CuadradoController controlador = new CuadradoController(modelo, vista);

        // Simulación de movimiento del cuadrado azul
        controlador.moverCuadrado(100, 100);
    }
    
}
