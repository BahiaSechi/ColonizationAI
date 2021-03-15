package simulation.robots.states;

import simulation.robots.Robot;
import simulation.robots.moves.MoveStrategy;

import java.util.Optional;

public class ToBase extends State {
    @Override
    public Optional<State> nextMove(Robot robot) {
        MoveStrategy move = robot.getMovement();
        if (!move.isGoingToBase()) {
            move.toBase();
        } else if (move.isOnBase()) return Optional.of(nextState());

        return Optional.empty();
    }

    @Override
    public State nextState() {
        return new Exploring();
    }
}
