package robots;

import robots.moves.Immobile;
import robots.moves.MoveStrategy;
import robots.states.State;
import robots.states.Waiting;
import sensors.lv223.NoSensor;
import sensors.lv223.ViewSensor;

public class OperatorRobot extends Robot {

    private double  oreExploitationLimit = 0.5;
    private int[][] qArrayOres           = new int[530][530];
    private int[][] qArrayWater          = new int[530][530];

    public OperatorRobot(RobotController controller) {
        super(new Immobile(), controller, new Waiting(), 0.0, new NoSensor());
    }

    public OperatorRobot(MoveStrategy movement, RobotController controller, State state, double epsilon, ViewSensor viewSensor) {
        super(movement, controller, state, epsilon, viewSensor);
    }
}
