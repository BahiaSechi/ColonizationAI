package robots;

import robots.moves.MoveStrategy;
import robots.states.State;


public class Robot {
    MoveStrategy movement;
    RobotController controller;
    private State state;
    private double epsilon = 0.5;


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
}
