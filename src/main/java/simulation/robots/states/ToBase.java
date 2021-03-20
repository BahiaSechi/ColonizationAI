package simulation.robots.states;

import javafx.util.Pair;
import simulation.robots.Pos;
import simulation.robots.Robot;
import simulation.robots.moves.MoveStrategy;

import java.util.Optional;

public class ToBase extends State {
    public ToBase(Pos pos) {
        super(pos);
    }

    public ToBase(State state) {
        super(state);
    }

    @Override
    public Pair<Integer, Optional<State>> nextMove(Robot robot) {
        MoveStrategy move = robot.getMovement();
        if (!move.isGoingToBase()) {
            move.toBase();
        } else if (move.isOnBase()) return Optional.of(nextState());

        return Optional.empty();
    }

    @Override
    public State nextState() {
        return new Exploring(this.getPos());
    }
}
