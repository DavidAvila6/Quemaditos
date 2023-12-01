import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class PersonajeModel {
    private static List<Personaje> serverPersonajes = new ArrayList<>();
    private static List<Personaje> clientPersonajes = new ArrayList<>();
    public static String pikarun = "sprites\\pikarun.gif";
    public static String pikan = "sprites\\pikan.png";
    public static String pikae = "sprites\\pika.gif";
    public static String scorun = "sprites\\scorun.gif";
    public static String scorn = "sprites\\scorn.png";
    public static String score = "sprites\\score.gif";
    private int currentNumber = 0;

    private static int controlledClientIndex = 0;
    private static int controlledServerIndex = 0;

    // Ruta de la imagen del personaje


    public static List<Personaje> getServerPersonajes() {
        return serverPersonajes;
    }

    public static List<Personaje> getClientPersonajes() {
        return clientPersonajes;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int number) {
        currentNumber = number;
    }
    
    

    public static int getXServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static int getYServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static int getXClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).getX();
        }
        return -1; // Manejar el caso de índice no válido
    }

    public static int getYClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).getY();
        }
        return -1; // Manejar el caso de índice no válido
    }
    public static boolean getagarrabolaClient(int index) {
        if (index >= 0 && index < clientPersonajes.size()) {
            return clientPersonajes.get(index).isAgarraBola();
        }
        return false; // Manejar el caso de índice no válido
    }
    public static boolean getagarrabolaServer(int index) {
        if (index >= 0 && index < serverPersonajes.size()) {
            return serverPersonajes.get(index).isAgarraBola();
        }
        return false; // Manejar el caso de índice no válido
    }

    public static void updateServerPosition(int index, int x, int y,boolean agarra) {
        ImageIcon imageIcon;
        if (index >= 0 && index < serverPersonajes.size()) {
            serverPersonajes.get(index).setX(x);
            serverPersonajes.get(index).setY(y);
            serverPersonajes.get(index).setAgarraBola(agarra);
            if (agarra){
                imageIcon = new ImageIcon(score);
                serverPersonajes.get(index).setImage(imageIcon);
            }
            
        }
    }

    public static void updateClientPosition(int index, int x, int y,boolean agarra) {
        ImageIcon imageIcon;
        if (index >= 0 && index < clientPersonajes.size()) {
            clientPersonajes.get(index).setX(x);
            clientPersonajes.get(index).setY(y);
            clientPersonajes.get(index).setAgarraBola(agarra);
            if (agarra){
                imageIcon = new ImageIcon(pikae);
                clientPersonajes.get(index).setImage(imageIcon);
            }
        }
    }

    public static void moveClientPosition(int index, KeyEvent e) {
        if (index >= 0 && index < clientPersonajes.size()) {
            int xClient = clientPersonajes.get(index).getX();
            int yClient = clientPersonajes.get(index).getY();
    
            // Manejar el cambio de imagen fuera de las condiciones de teclas
            ImageIcon imageIcon;
    
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                xClient -= 5;
                imageIcon = new ImageIcon(pikarun);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                xClient += 5;
                imageIcon = new ImageIcon(pikarun);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                yClient -= 5;
                imageIcon = new ImageIcon(pikarun);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                yClient += 5;
                imageIcon = new ImageIcon(pikarun);
            } else if (e.getKeyChar() == 'e') {
                clientPersonajes.get(index).setAgarraBola(true);
                imageIcon = new ImageIcon(pikae);
            } else if (e.getKeyChar() == 'r') {
                clientPersonajes.get(index).setLanzar(true);
                imageIcon = new ImageIcon(pikae);
                System.out.println("Tecla ando");
            }else {
                imageIcon = new ImageIcon(pikan);
            }
    
            clientPersonajes.get(index).setImage(imageIcon);

    
            updateClientPosition(index, xClient, yClient,clientPersonajes.get(index).isAgarraBola());
        }
    }
    

    public static void moveServerPosition(int index, KeyEvent e) {
        if (index >= 0 && index < serverPersonajes.size()) {
            int xServer = serverPersonajes.get(index).getX();
            int yServer = serverPersonajes.get(index).getY();

            ImageIcon imageIcon;
    
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                xServer -= 5;
                imageIcon = new ImageIcon(scorun);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                xServer += 5;
                imageIcon = new ImageIcon(scorun);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                yServer -= 5;
                imageIcon = new ImageIcon(scorun);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                yServer += 5;
                imageIcon = new ImageIcon(scorun);
            } else if (e.getKeyChar() == 'e') {
                serverPersonajes.get(index).setAgarraBola(true);    
                          
                imageIcon = new ImageIcon(score);
            }else if (e.getKeyChar() == 'r') {
                serverPersonajes.get(index).setLanzar(true);
                imageIcon = new ImageIcon(pikae);
            } else {
                imageIcon = new ImageIcon(scorn);
            }
    
            serverPersonajes.get(index).setImage(imageIcon);
            updateServerPosition(index, xServer, yServer,serverPersonajes.get(index).isAgarraBola());
        }
    }

    public static int getControlledClientIndex() {
        return controlledClientIndex;
    }

    public static void setControlledClientIndex(int index) {
        controlledClientIndex = index;
    }

    public static int getControlledServerIndex() {
        return controlledServerIndex;
    }

    public static void setControlledServerIndex(int index) {
        controlledServerIndex = index;

    }
    

}
