package simulation.robots;

import lombok.Getter;
import lombok.Setter;
import simulation.robots.moves.Immobile;
import simulation.robots.moves.MoveStrategy;
import simulation.robots.states.State;
import simulation.robots.states.Waiting;
import simulation.robots.tools.*;
import simulation.sensors.lv223.NoSensor;
import simulation.sensors.lv223.ViewSensor;

public class OperatorRobot extends Robot {

    @Getter
    @Setter
    private double oreExploitationLimit    = 0.5;
    @Getter
    @Setter
    private double waterExploitationLimit  = 0.5;
    @Getter
    @Setter
    private double meadowExploitationLimit = 0.5;
    @Getter
    @Setter
    private double foodExploitationLimit   = 0.5;

    @Getter
    @Setter
    private int[][] qArrayOres   = new int[529][4];
    @Getter
    @Setter
    private int[][] qArrayWater  = new int[529][4];
    @Getter
    @Setter
    private int[][] qArrayMeadow = new int[529][4];
    @Getter
    @Setter
    private int[][] qArrayFood   = new int[529][4];

    public OperatorRobot(RobotController controller, int id) {
        super(id, new Immobile(), controller, new Waiting(new Pos(0, 0)), 0.0f, new NoSensor());
    }

    public OperatorRobot(MoveStrategy movement, RobotController controller, State state, float epsilon, ViewSensor viewSensor, int id) {
        super(id, movement, controller, state, epsilon, viewSensor);
    }

    public boolean allowExploitation(Robot robot) {
        Tool tool = robot.getTool();
        ViewSensor sensor = robot.getViewSensor();
        State state = robot.getState();

        if (tool instanceof Extractor) {
            return sensor.getExploitationLevel(state.getPos()) >= oreExploitationLimit;
        } else if (tool instanceof Gloves) {
            return sensor.getExploitationLevel(state.getPos()) >= foodExploitationLimit;
        } else if (tool instanceof Pipeliner) {
            return sensor.getExploitationLevel(state.getPos()) >= waterExploitationLimit;
        } else if (tool instanceof Scythe) {
            return sensor.getExploitationLevel(state.getPos()) >= meadowExploitationLimit;
        }

        return false;
    }
}
