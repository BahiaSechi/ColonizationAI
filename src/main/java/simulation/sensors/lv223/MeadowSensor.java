package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;

public class MeadowSensor extends ViewSensor {
    @Override
    public float getExploitationLevel(Pos pos) {
        Tile tile = planet.getTile(pos.getX(), pos.getY());
        if (isExploitable(tile) > 0.0) {
            // Report exploitation level
        }

        return 0.0f;
    }

    @Override
    double isExploitable(Tile tile) {
        if (tile.getType() == TileType.MEADOW_DRY) return 1.0;
        else if (tile.getType() == TileType.MEADOW_GREASY) return 3.0;
        else if (tile.getType() == TileType.MEADOW_NORMAL) return 2.0;

        return 0.0;
    }
}
