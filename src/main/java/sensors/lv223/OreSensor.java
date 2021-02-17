package sensors.lv223;

import planet.Tile;

public class OreSensor extends ViewSensor {
    @Override
    boolean isExploitable(Tile tile) {
        return true;
//        TODO: Mettre un tile.getType()
    }
}
