package robots.states;

import robots.Robot;

import java.util.Optional;

public class Waiting extends State {
    @Override
    public Optional<State> nextMove(Robot robot) {
        return Optional.empty();
    }

    @Override
    public State nextState() {
        return new Waiting();
    }
}
