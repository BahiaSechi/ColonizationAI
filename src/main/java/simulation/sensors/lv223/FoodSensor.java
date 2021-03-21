package simulation.sensors.lv223;

import simulation.planet.Planet;
import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;

/**
 *
 */
public class FoodSensor extends ViewSensor {
    public FoodSensor(Planet planet) {
        super(planet);
    }

    @Override
    public float getExploitationLevel(Pos pos) {
        return 0;
    }

    @Override
    double isExploitable(Tile tile) {
        if (tile.getType() == TileType.FOOD) return 1.0;

        return 0.0;
    }
}
