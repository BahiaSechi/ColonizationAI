package simulation.sensors.lv223;

import simulation.planet.Planet;
import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.Map;
import java.util.stream.Collectors;

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

        return planet.getSurrounding(absolutePos)
                .stream()
                .collect(Collectors.toMap(tile -> new Pos(tile.getTileX(), tile.getTileY()), this::isExploitable));
    }

    public double isCurrentExploitable(Robot robot) {
        Pos absolutePos = robot.getController().getAbsolutePos(robot);
        return isExploitable(planet.getTile(absolutePos.getX(), absolutePos.getY()));
    }

    abstract double isExploitable(Tile tile);
}
