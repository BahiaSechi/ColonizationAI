package simulation.robots.states;

import javafx.util.Pair;
import simulation.robots.Pos;
import simulation.robots.Robot;
import simulation.robots.moves.Action;
import simulation.robots.moves.MoveStrategy;
import simulation.sensors.lv223.ViewSensor;

import java.util.Optional;
import java.util.Random;

/**
 *
 */
public class Exploring extends State {
    public Exploring(Pos pos) {
        super(pos);
    }

    public Exploring(State state) {
        super(state);
    }

    @Override
    public Pair<Integer, Optional<State>> nextMove(Robot robot) {
        MoveStrategy strat = robot.getMovement();
        float choice = (new Random()).nextFloat();
        Pair<Pos, Action> pair;

        if (choice >= robot.getEpsilon()) {
            pair = strat.randomMove(robot);
        } else {
            pair = strat.bestMove(robot);
        }

        ViewSensor sensor = robot.getViewSensor();
        int quality;
        Pos absolutePos = robot.getController().getAbsolutePos(robot);
        if (absolutePos.getX() >= 0 && absolutePos.getY() >= 0 && absolutePos.getX() < 21
                && absolutePos.getY() < 21 && !sensor.isAnObstacle(absolutePos)) {
            this.setPos(pair.getKey());
            quality = sensor.ratePos(robot);
        } else {
            quality = -10;
        }

        if (sensor.isCurrentExploitable(robot) > 0.0) return new Pair<>(quality, Optional.of(nextState()));
        else return new Pair<>(quality, Optional.empty());
    }

    @Override
    public State nextState() {
        return new UsingTool(this.getPos());
    }
}
