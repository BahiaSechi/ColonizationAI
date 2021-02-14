package robots.states;

import robots.Robot;

public class Exploring extends State {
    @Override
    public boolean nextMove(Robot robot) {
        return robot.getMovement().nextMove(robot);
    }

    @Override
    public State nextState() {
        return new UsingTool();
    }
}
