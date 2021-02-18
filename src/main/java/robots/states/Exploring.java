package robots.states;

import robots.Robot;
import robots.moves.MoveStrategy;

import java.util.Optional;

public class Exploring extends State {
    @Override
    public Optional<State> nextMove(Robot robot) {
        MoveStrategy strat = robot.getMovement();
        boolean bool = strat.nextMove(robot);

        if (robot.getViewSensor().isCurrentExploitable(robot) > 0.0) return Optional.of(nextState());
        else return Optional.empty();
    }

    @Override
    public State nextState() {
        return new UsingTool();
    }
}
