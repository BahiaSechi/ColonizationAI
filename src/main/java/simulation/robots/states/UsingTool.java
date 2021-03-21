package simulation.robots.states;

import javafx.util.Pair;
import simulation.robots.OperatorRobot;
import simulation.robots.Pos;
import simulation.robots.Robot;
import simulation.robots.moves.MoveStrategy;
import simulation.robots.tools.Tool;

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
        MoveStrategy strat = robot.getMovement();
        int quality = 0;
        OperatorRobot operator = robot.getController().getOperator();
        Tool tool = robot.getTool();

        if (!operator.allowExploitation(robot)) {
            return new Pair<>(quality, Optional.of(nextState()));
        }

        tool.use(robot);
        return new Pair<>(quality, Optional.empty());
    }

    @Override
    public State nextState() {
        return new Exploring(getPos());
    }
}
