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
 * @file WaterSensor.java
 * @version 1.0
 */

package simulation.sensors.lv223;

import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;

public class WaterSensor extends ViewSensor {
    /**
     * @param pos
     * @return
     */
    @Override
    public float getExploitationLevel(Pos pos) {
        Tile tile = planet.getTile(pos.getX(), pos.getY());
        if (tile.getType() == TileType.WATER) {
            // Report exploitation level
        }

        return 0.0f;
    }

    @Override
    double isExploitable(Tile tile) {
        return tile.getType() == TileType.WATER ? 1.0 : 0.0;
    }
}
