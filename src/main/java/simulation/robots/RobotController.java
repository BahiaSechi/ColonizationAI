package simulation.robots;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class RobotController {
    @Getter
    @Setter
    private Pos                 colonyCenter;
    @Getter
    @Setter
    private OperatorRobot       operator;
    @Getter
    @Setter
    private Map<Integer, Robot> robots;

    public RobotController(Pos colonyCenter) {
        this.colonyCenter = colonyCenter;
        this.operator = new OperatorRobot(this, 0);
        this.robots = new HashMap<>();
    }

    public boolean addRobot(int id, Robot robot) {
        if (robots.containsKey(id)) return false;

        robots.put(id, robot);
        return true;
    }

    public boolean removeRobot(int id, Robot robot) {
        if (!robots.containsKey(id)) return false;

        robots.remove(id);
        return true;
    }

    public Pos getAbsolutePos(Robot robot) {
        return new Pos(colonyCenter.getX() + robot.getState().getPos().getX(),
                colonyCenter.getY() + robot.getState().getPos().getY());
    }
}
