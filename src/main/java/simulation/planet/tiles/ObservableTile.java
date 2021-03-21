/**
 * Address :
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 * Note :
 * This file is owned by an ENSICAEN student.  No portion of this
 * document may be reproduced, copied  or revised without written
 * permission of the authors.
 *
 * @author PRUNIER Bastien <bastien.prunier@ecole.ensicaen.fr>
 * @author RABOTIN Mateo <mateo.rabotin@ecole.ensicaen.fr>
 * @author SECHI Bahia <bahia.sechi@ecole.ensicaen.fr>
 * @author SERVAT Clement <clement.servat@ecole.ensicaen.fr>
 * @date February 2021
 * @file ObservableTile.java
 * @version 1.0
 */

package simulation.planet.tiles;

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
