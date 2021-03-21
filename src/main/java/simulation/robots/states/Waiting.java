package simulation.robots.states;

import javafx.util.Pair;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.Optional;

/**
 *
 */
public class Waiting extends State {
    public Waiting(Pos pos) {
        super(pos);
    }

    public Waiting(State state) {
        super(state);
    }

    @Override
    public Pair<Integer, Optional<State>> nextMove(Robot robot) {
        return new Pair<>(0, Optional.empty());
    }

    @Override
    public State nextState() {
        return new Waiting(this.getPos());
    }
}
