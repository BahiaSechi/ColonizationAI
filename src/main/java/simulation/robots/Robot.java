package simulation.robots;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import simulation.robots.moves.MoveStrategy;
import simulation.robots.states.State;
import simulation.robots.states.ToBase;
import simulation.robots.tools.Tool;
import simulation.sensors.lv223.ViewSensor;

import java.util.Optional;


public class Robot {
    private int             id;
    @Getter
    @Setter
    private RobotController controller;
    @Getter
    @Setter
    private float           health;

    @Getter
    @Setter
    private MoveStrategy movement;
    @Getter
    @Setter
    private float        epsilon;
    @Getter
    @Setter
    private State        state;
    @Getter
    @Setter
    private ViewSensor   viewSensor;
    @Getter
    @Setter
    private Tool         tool;

    public Robot(int id, MoveStrategy movement, RobotController controller, State state, float epsilon, ViewSensor viewSensor) {
        this.id = id;
        this.movement = movement;
        this.controller = controller;
        this.state = state;
        this.epsilon = epsilon;
        this.viewSensor = viewSensor;
    }

    /**
     * Represents a game iteration for the robot
     * <p>
     * The order is :
     * - check life
     * - decide between Exploit. and Explor.
     * - report the quality of the move
     */
    public void nextMove() {
        if (this.isInDanger()) {
            this.state = new ToBase(this.state);
        }

        Pair<Integer, Optional<State>> result = this.state.nextMove(this);
        result.getValue().ifPresent(value -> state = value);

        // Q reporting
//        controller.getOperator()
    }

    public boolean isInDanger() {
        return health > 0.50;
    }
}
