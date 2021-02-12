package robots;

import robots.moves.MoveStrategy;

public class Robot {
    MoveStrategy movement;

    RobotController controller;

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
