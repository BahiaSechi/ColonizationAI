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

@Getter
@Setter
public class Robot {
    private int             id;
    private RobotController controller;
    private float           health;

    private MoveStrategy movement;
    private float        epsilon;
    private State        state;
    private ViewSensor   viewSensor;
    private Tool         tool;

    private RobotType type;

    public Robot(int id, MoveStrategy movement, RobotController controller, State state, float epsilon, ViewSensor viewSensor, RobotType type) {
        this.id = id;
        this.movement = movement;
        this.controller = controller;
        this.state = state;
        this.epsilon = epsilon;
        this.viewSensor = viewSensor;
        this.type = type;
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
