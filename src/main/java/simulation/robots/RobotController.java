package simulation.robots;

import java.util.HashMap;
import java.util.Map;

public class RobotController {
    private Pos                 colonyCenter;
    private OperatorRobot       operator;
    private Map<Integer, Robot> robots;

    public RobotController(Pos colonyCenter) {
        this.colonyCenter = colonyCenter;
        this.operator = new OperatorRobot(this, 0);
        this.robots = new HashMap<Integer, Robot>();
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

    public int getTileNumber(Pos pos) {
        return pos.getY() * 23 + pos.getX();
    }

    public Pos getColonyCenter() {
        return colonyCenter;
    }

    public void setColonyCenter(Pos colonyCenter) {
        this.colonyCenter = colonyCenter;
    }

    public OperatorRobot getOperator() {
        return operator;
    }

    public void setOperator(OperatorRobot operator) {
        this.operator = operator;
    }

    public Map<Integer, Robot> getRobots() {
        return robots;
    }

    public void setRobots(Map<Integer, Robot> robots) {
        this.robots = robots;
    }
}
