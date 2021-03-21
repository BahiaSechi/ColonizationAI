/**
 * Address :
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 * Note :
 * This file is owned by an ENSICAEN student.  No portion of this
 * document may be reproduced, copied  or revised without written
 * permission of the authors.
 *
 * @author PRUNIER Bastien <bastien.prunier@ecole.ensicaen.fr>
 * @author RABOTIN Mateo <mateo.rabotin@ecole.ensicaen.fr>
 * @author SECHI Bahia <bahia.sechi@ecole.ensicaen.fr>
 * @author SERVAT Clement <clement.servat@ecole.ensicaen.fr>
 * @date February 2021
 * @file MeadowSensor.java
 * @version 1.0
 */

package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;

/**
 *
 */
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
