package robots.states;

import robots.Robot;

import java.util.Optional;

public class UsingTool extends State {
    @Override
    public Optional<State> nextMove(Robot robot) {
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
