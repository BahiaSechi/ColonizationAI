package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;

public class MeadowSensor extends ViewSensor {
    @Override
    double isExploitable(Tile tile) {
        return 0;
    }
}
