package robots;

import robots.moves.MoveStrategy;
import robots.states.State;
import sensors.lv223.ViewSensor;


public class Robot {
    MoveStrategy movement;
    RobotController controller;
    private State state;
    private double epsilon;
    private ViewSensor viewSensor;

    public Robot(MoveStrategy movement, RobotController controller, State state, double epsilon, ViewSensor viewSensor) {
        this.movement = movement;
        this.controller = controller;
        this.state = state;
        this.epsilon = epsilon;
        this.viewSensor = viewSensor;
    }

    public void nextMove() {
        if (state.nextMove(this))
            state = state.nextState();
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
}
