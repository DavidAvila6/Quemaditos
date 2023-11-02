import java.io.*;
import java.net.*;
import java.io.Serializable;

import modelo.JugadorModel;

public class JugadorClient implements Serializable{
    public static void main(String[] args) {
        
        try {
            
            // Crear una conexión con el servidor en la dirección IP y puerto correctos
            Socket socket = new Socket("localhost", 12345);  // Cambia "localhost" por la IP del servidor si es necesario

            // Preparar entrada y salida de datos
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Realizar acciones del cliente
            JugadorModel jugador = new JugadorModel();

            // Ejemplo: Enviar una acción al servidor (movimiento)
            jugador.mover(10, 10);
            outputStream.writeObject(jugador);
            outputStream.flush();

            // Ejemplo: Recibir actualizaciones del servidor (posición de los jugadores)
            JugadorModel jugadorActualizado = (JugadorModel) inputStream.readObject();
            System.out.println("Posición actual del jugador: X = " + jugadorActualizado.getX() + ", Y = " + jugadorActualizado.getY());

            // Cerrar la conexión
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
