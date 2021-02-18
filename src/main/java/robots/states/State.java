package robots.states;

import robots.Robot;

import java.util.Optional;

public abstract class State {
    public abstract Optional<State> nextMove(Robot robot);

    public abstract State nextState();
}
