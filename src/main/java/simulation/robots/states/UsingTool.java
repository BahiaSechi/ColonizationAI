package simulation.robots.states;

import javafx.util.Pair;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.Optional;

public class UsingTool extends State {
    public UsingTool(Pos pos) {
        super(pos);
    }

    public UsingTool(State state) {
        super(state);
    }

    @Override
    public Pair<Integer, Optional<State>> nextMove(Robot robot) {
        if (robot.getViewSensor().isCurrentExploitable(robot) > 0.0) {
            robot.getTool().use(robot);
        }

        return Optional.empty();
    }

    @Override
    public State nextState() {
        return null;
    }
}
