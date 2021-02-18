package robots.moves;

import robots.Pos;
import robots.Robot;

import java.util.List;
import java.util.Map;

public abstract class MoveStrategy {

    /**
     * currentPath holds a path that the robot must follow. It is optional. It can be a path leading to the base.
     */
    private List<Pos> currentPath;
    /**
     * The actual position of the robot, relative to its base.
     */
    private Pos currentPos;

    abstract List<Pos> findNewPath();

    public void toBase() {
        System.out.println("Retour à la base");
    }

    /**
     * Generic method that simply checks for exploitable tile around robot and eventually go on them. If there are
     * not any, choose a random location.
     *
     * @param robot The robot to move
     * @return Does the robot has a path to follow ?
     */
    public boolean nextMove(Robot robot) {
        Map<Pos, Double> surrounding = robot.getViewSensor().exploitableSurrounding(robot);
        if (!surrounding.isEmpty()) {
            Pos maxPos = surrounding.entrySet()
                    .stream()
                    .max((tile1, tile2) -> tile1.getValue() >= tile2.getValue() ? 1 : -1)
                    .get()
                    .getKey();

            setCurrentPos(maxPos);
        } else {
            // Find a random tile to go.
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
