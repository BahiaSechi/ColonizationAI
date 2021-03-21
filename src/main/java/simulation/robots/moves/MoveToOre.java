package simulation.robots.moves;

import javafx.util.Pair;
import simulation.robots.OperatorRobot;
import simulation.robots.Pos;
import simulation.robots.Robot;
import simulation.robots.states.State;

/**
 *
 */
public class MoveToOre extends MoveStrategy {
    @Override
    public Pair<Pos, Action> bestMove(Robot robot) {
        State state = robot.getState();
        Pos pos = state.getPos();
        int qState = pos.getY() * 23 + pos.getX();
        OperatorRobot operator = robot.getController().getOperator();

        Action action = bestActionFromState(operator.getQArrayOres()[qState]);
        if (action == null) {
            return randomMove(robot);
        }

        return new Pair<>(doAction(robot, action), action);
    }
}
