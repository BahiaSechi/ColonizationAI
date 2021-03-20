package simulation.robots;

import simulation.robots.moves.Immobile;
import simulation.robots.moves.MoveStrategy;
import simulation.robots.states.State;
import simulation.robots.states.Waiting;
import simulation.sensors.lv223.NoSensor;
import simulation.sensors.lv223.ViewSensor;

public class OperatorRobot extends Robot {

    private double  oreExploitationLimit = 0.5;
    private int[][] qArrayOres           = new int[529][4];
    private int[][] qArrayWater          = new int[529][4];

    public OperatorRobot(RobotController controller, int id) {
        super(id, new Immobile(), controller, new Waiting(new Pos(0, 0)), 0.0f, new NoSensor());
    }

    public OperatorRobot(MoveStrategy movement, RobotController controller, State state, float epsilon, ViewSensor viewSensor, int id) {
        super(id, movement, controller, state, epsilon, viewSensor);
    }
}
