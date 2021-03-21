package simulation.robots.moves;

import javafx.util.Pair;
import simulation.robots.Pos;
import simulation.robots.Robot;

/**
 *
 */
public class Immobile extends MoveStrategy {
    @Override
    public Pair<Pos, Action> bestMove(Robot robot) {
        return new Pair<>(robot.getState().getPos(), null);
    }
}
