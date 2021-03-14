package robots;

import robots.moves.MoveStrategy;
import robots.states.State;
import robots.tools.Tool;
import sensors.lv223.ViewSensor;

import java.util.Optional;


public class Robot {
    private int             id;
    private MoveStrategy    movement;
    private RobotController controller;
    private State           state;
    private double          epsilon;
    private ViewSensor      viewSensor;
    private Tool            tool;

    public Robot(int id, MoveStrategy movement, RobotController controller, State state, double epsilon, ViewSensor viewSensor) {
        this.id = id;
        this.movement = movement;
        this.controller = controller;
        this.state = state;
        this.epsilon = epsilon;
        this.viewSensor = viewSensor;
    }

    public void nextMove() {
        Optional<State> newState = state.nextMove(this);
        newState.ifPresent(value -> state = value);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public RobotController getController() {
        return controller;
    }

    public void setController(RobotController controller) {
        this.controller = controller;
    }

    public MoveStrategy getMovement() {
        return movement;
    }

    public void setMovement(MoveStrategy movement) {
        this.movement = movement;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public ViewSensor getViewSensor() {
        return viewSensor;
    }

    public void setViewSensor(ViewSensor viewSensor) {
        this.viewSensor = viewSensor;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }
}
