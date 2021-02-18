package robots.states;

import robots.Robot;

import java.util.Optional;

public class UsingTool extends State {
    @Override
    public Optional<State> nextMove(Robot robot) {
        return false;
    }

    @Override
    public State nextState() {
        return null;
    }
}
