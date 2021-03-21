package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;

public class OreSensor extends ViewSensor {
    @Override
    public float getExploitationLevel(Pos pos) {
        Tile tile = planet.getTile(pos.getX(), pos.getY());
        if (tile.getType() == TileType.ORE) {
            // Report exploitation level
        }

        return 0.0f;
    }

    @Override
    double isExploitable(Tile tile) {
        return tile.getType() == TileType.ORE ? 1.0 : 0.0;
    }
}
