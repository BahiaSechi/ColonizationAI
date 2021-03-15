package simulation.robots;

import simulation.robots.moves.Immobile;
import simulation.robots.moves.MoveStrategy;
import simulation.robots.states.State;
import simulation.robots.states.Waiting;
import simulation.sensors.lv223.NoSensor;
import simulation.sensors.lv223.ViewSensor;

public class OperatorRobot extends Robot {

    private double  oreExploitationLimit = 0.5;
    private int[][] qArrayOres           = new int[530][530];
    private int[][] qArrayWater          = new int[530][530];

    public OperatorRobot(RobotController controller, int id) {
        super(id, new Immobile(), controller, new Waiting(), 0.0, new NoSensor());
    }

    public OperatorRobot(MoveStrategy movement, RobotController controller, State state, double epsilon, ViewSensor viewSensor, int id) {
        super(id, movement, controller, state, epsilon, viewSensor);
    }
}
