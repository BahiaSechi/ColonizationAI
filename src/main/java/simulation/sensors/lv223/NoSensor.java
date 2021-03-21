package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;
import simulation.robots.Pos;

public class NoSensor extends ViewSensor {
    @Override
    public float getExploitationLevel(Pos pos) {
        return 0.0f;
    }

    @Override
    double isExploitable(Tile tile) {
        return 0;
    }
}
