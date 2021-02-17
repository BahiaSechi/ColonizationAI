package simulation.robots.moves;

import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.List;

public class MoveToOre extends MoveStrategy {
    List<Pos> findNewPath() {
        return null;
    }

    void nextMove(Robot robot) {
        System.out.println("On se dirige vers des minerais.");
    }
}
