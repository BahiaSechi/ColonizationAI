package sensors.lv223;

import planet.Tile;

public class OreSensor extends ViewSensor {
    @Override
    double isExploitable(Tile tile) {
        return 1.0;
//        TODO: Mettre un tile.getType()
    }
}
