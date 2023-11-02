/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author David
 */
import modelo.CuadradoModel;
import modelo.JugadorModel;
import vista.CuadradoView;
import vista.JuegoView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

import controlador.CuadradoController;
import controlador.JuegoController;
public class principal {
    public static void main(String[] args) {
        JugadorModel jugador1 = new JugadorModel();
        JugadorModel jugador2 = new JugadorModel();
        JuegoView vista = new JuegoView(jugador1, jugador2);
        System.out.println("Prueba1");
        try {
            // Crear el servidor y aceptar dos clientes
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket jugador1Socket = serverSocket.accept();
            Socket jugador2Socket = serverSocket.accept();

            JuegoController jugador1Controller = new JuegoController(jugador1, jugador2, vista, jugador2Socket.getOutputStream());
            JuegoController jugador2Controller = new JuegoController(jugador2, jugador1, vista, jugador1Socket.getOutputStream());
            System.out.println("Prueba2");
            JFrame frame = new JFrame("Juego Multijugador");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(vista);
            frame.addKeyListener(jugador1Controller);
            frame.setVisible(true);
        } catch (IOException e) {
            System.out.println("Prueba3");
            e.printStackTrace();
            
        }
    }
    
}
