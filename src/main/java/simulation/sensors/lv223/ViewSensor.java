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
 * @file ViewSensor.java
 * @version 1.0
 */

package simulation.sensors.lv223;

import simulation.planet.Planet;
import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public abstract class ViewSensor {
    protected Planet planet;

    public abstract float getExploitationLevel(Pos pos);

    public int ratePos(Robot robot) {
        if (isCurrentExploitable(robot) > 0.0) return 5;
        else return 1;
    }

    public boolean isAnObstacle(Pos pos) {
        Tile tile = planet.getTile(pos.getX(), pos.getY());
        return tile.getType() == TileType.OBSTACLE;
    }

    public Map<Pos, Double> exploitableSurrounding(Robot robot) {
        Pos absolutePos = robot.getController().getAbsolutePos(robot);

        return planet.getSurrounding(absolutePos, 1)
                .stream()
                .collect(Collectors.toMap(tile -> new Pos(tile.getTileX(), tile.getTileY()), this::isExploitable));
    }

    public double isCurrentExploitable(Robot robot) {
        Pos absolutePos = robot.getController().getAbsolutePos(robot);
        return isExploitable(planet.getTile(absolutePos.getX(), absolutePos.getY()));
    }

    abstract double isExploitable(Tile tile);
}
