package modelos;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private static List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}