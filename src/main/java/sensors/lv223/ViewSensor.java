package sensors.lv223;

import planet.Planet;
import planet.Tile;
import robots.Pos;
import robots.Robot;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class ViewSensor {
    private Planet planet;

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
