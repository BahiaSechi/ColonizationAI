package planet.tiles;

import java.util.LinkedList;
import java.util.List;

public abstract class ObservableTile {
    private List<Observer> observers;

    public ObservableTile() {
        observers = new LinkedList<Observer>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers)
            o.update();
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
