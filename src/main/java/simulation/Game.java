package simulation;

import simulation.planet.Planet;
import simulation.planet.exception.MissingTileTypeException;
import simulation.planet.tiles.TileType;

public class Game {

    private Planet planet;

    public Game() {

        planet = new Planet();

        try {
            planet.initialize();
        } catch (MissingTileTypeException e) {
            e.printStackTrace();
        }

        for (int i=0 ; i<20 ; i++) {
            planet.consumeResourcesOnRandomCase(TileType.WATER, 1000);
            planet.consumeResourcesOnRandomCase(TileType.ORE, 10);
            planet.update();
        }

    }

    public Planet getPlanet() {
        return planet;
    }
}

