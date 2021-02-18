package sensors.lv223;

import planet.Tile;

public class MeadowSensor extends ViewSensor {
    @Override
    double isExploitable(Tile tile) {
        return 0;
    }
}
