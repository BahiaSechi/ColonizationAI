package sensors.lv223;

import planet.Tile;
import planet.TileType;

public class OreSensor extends ViewSensor {
    @Override
    double isExploitable(Tile tile) {
        return tile.getType() == TileType.ORE ? 1.0 : 0.0;
    }
}
