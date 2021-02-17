package simulation.robots.moves;

import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.List;

public abstract class MoveStrategy {
    private List<Pos> currentPath;
    private Pos currentPos;

    abstract List<Pos> findNewPath();

    void toBase() {
        System.out.println("Retour à la base");
    }

    abstract void nextMove(Robot robot);
}
