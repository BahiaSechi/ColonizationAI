package simulation.robots.moves;

import javafx.util.Pair;
import simulation.robots.OperatorRobot;
import simulation.robots.Pos;
import simulation.robots.Robot;
import simulation.robots.states.State;

/**
 *
 */
public class MoveToWater extends MoveStrategy {
    @Override
    public Pair<Pos, Action> bestMove(Robot robot) {
        State state = robot.getState();
        Pos pos = state.getPos();
        int qState = pos.getY() * 21 + pos.getX();
        OperatorRobot operator = robot.getController().getOperator();

        if (qState < 0) {
            qState = 0;
        }
        Action action = bestActionFromState(operator.getQArrayWater()[qState]);
        if (action == null) {
            return randomMove(robot);
        }

        return new Pair<>(doAction(robot, action), action);
    }
}
