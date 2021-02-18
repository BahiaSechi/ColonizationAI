package robots;

import robots.moves.MoveStrategy;
import robots.states.State;
import sensors.lv223.ViewSensor;

public class OperatorRobot extends Robot {

    private double oreExploitationLimit = 0.5;

    public OperatorRobot(MoveStrategy movement, RobotController controller, State state, double epsilon, ViewSensor viewSensor) {
        super(movement, controller, state, epsilon, viewSensor);
    }
}
