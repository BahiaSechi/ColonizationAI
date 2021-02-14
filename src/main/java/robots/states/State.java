package robots.states;

import robots.Robot;

public abstract class State {
    public abstract boolean nextMove(Robot robot);

    public abstract State nextState();
}
