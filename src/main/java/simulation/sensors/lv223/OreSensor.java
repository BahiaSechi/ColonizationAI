package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;

public class OreSensor extends ViewSensor {
    @Override
    double isExploitable(Tile tile) {
        return tile.getType() == TileType.ORE ? 1.0 : 0.0;
    }
}
