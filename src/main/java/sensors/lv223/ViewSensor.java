package sensors.lv223;

import planet.Planet;
import planet.Tile;
import robots.Pos;
import robots.Robot;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class ViewSensor {
    Planet planet;

    public Map<Pos, Double> exploitableSurrounding(Robot robot) {
        System.out.println("Je regarde les alentours...");
        Pos absolutePos = robot.getController().getAbsolutePos(robot);

        return planet.getSurrounding(absolutePos)
                .stream()
                .collect(Collectors.toMap(tile -> new Pos(tile.getTileX(), tile.getTileY()), this::isExploitable));
    }

    abstract double isExploitable(Tile tile);
}
