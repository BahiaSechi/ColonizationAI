package simulation;

import simulation.planet.Planet;
import simulation.planet.exception.MissingTileTypeException;

public class Game {

    private Planet planet;

    public Game() {

        planet = new Planet();

        try {
            planet.initialize();
        } catch (MissingTileTypeException e) {
            e.printStackTrace();
        }
    }

    public Planet getPlanet() {
        return planet;
    }
}

