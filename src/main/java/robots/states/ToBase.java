package robots.states;

import robots.Robot;

public class ToBase extends State {
    @Override
    public boolean nextMove(Robot robot) {
        return false;
    }

    @Override
    public State nextState() {
        return null;
    }
}
