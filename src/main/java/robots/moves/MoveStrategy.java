package robots.moves;

import robots.Pos;
import robots.Robot;

import java.util.List;
import java.util.Map;

public abstract class MoveStrategy {
    private List<Pos> currentPath;
    private Pos currentPos;

    abstract List<Pos> findNewPath();

    public void toBase() {
        System.out.println("Retour à la base");
    }

    public boolean nextMove(Robot robot) {
        Map<Pos, Double> surrounding = robot.getViewSensor().exploitableSurrounding(robot);
        if (!surrounding.isEmpty()) {
            Pos maxPos = surrounding.entrySet().stream()
                    .max((tile1, tile2) -> tile1.getValue() >= tile2.getValue() ? 1 : -1).get().getKey();

            setCurrentPos(maxPos);
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
