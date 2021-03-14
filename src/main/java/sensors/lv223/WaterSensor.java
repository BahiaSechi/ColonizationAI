package sensors.lv223;

import planet.Tile;
import planet.TileType;

public class WaterSensor extends ViewSensor {
    @Override
    double isExploitable(Tile tile) {
        return tile.getType() == TileType.WATER ? 1.0 : 0.0;
    }
}
