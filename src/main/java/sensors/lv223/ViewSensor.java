package sensors.lv223;

import planet.Planet;
import planet.Tile;
import robots.Pos;
import robots.Robot;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ViewSensor {
    Planet planet;

    public List<Boolean> exploitableSurrounding(Robot robot) {
        System.out.println("Je regarde les alentours...");
        Pos absolutePos = robot.getController().getAbsolutePos(robot);

        return planet.getSurrounding(absolutePos)
                .stream()
                .map(this::isExploitable)
                .collect(Collectors.toList());
    }

    abstract boolean isExploitable(Tile tile);
}
