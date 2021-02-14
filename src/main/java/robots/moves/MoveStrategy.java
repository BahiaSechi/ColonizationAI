package robots.moves;

import planet.Tile;
import robots.Pos;
import robots.Robot;

import java.util.List;

public abstract class MoveStrategy {
    private List<Pos> currentPath;
    private Pos currentPos;

    abstract List<Pos> findNewPath();

    public void toBase() {
        System.out.println("Retour à la base");
    }

    public boolean nextMove(Robot robot) {
        List<Boolean> surrounding = robot.getViewSensor().exploitableSurrounding(robot);
        if (!surrounding.isEmpty()) {

        }

        return currentPath.isEmpty();
    }

    public Pos getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Pos currentPos) {
        this.currentPos = currentPos;
    }
}
