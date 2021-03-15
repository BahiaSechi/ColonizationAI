package simulation.robots.states;

import simulation.robots.Robot;
import simulation.robots.moves.MoveStrategy;

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
