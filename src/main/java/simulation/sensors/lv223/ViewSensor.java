package simulation.sensors.lv223;

import simulation.planet.Planet;
import simulation.planet.tiles.Tile;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class ViewSensor {
    private Planet planet;

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
