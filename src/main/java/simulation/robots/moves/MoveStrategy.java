package simulation.robots.moves;

import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class MoveStrategy {

    /**
     * currentPath holds a path that the robot must follow. It is optional. It can be a path leading to the base.
     */
    private List<Pos> currentPath;

    /**
     * The actual position of the robot, relative to its base.
     */
    private Pos     currentPos;
    private Pos     lastPos;
    private boolean goingToBase = false;
    private boolean onBase      = false;

    public abstract List<Pos> findNewPath();

    public void toBase() {
        // TODO: Find the shortest path to base
        setGoingToBase(true);
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
            var maxPosOpt = surrounding
                    .entrySet()
                    .stream()
                    .max((tile1, tile2) -> {
                        if (tile1.getValue() == tile2.getValue()) return 0;
                        return tile1.getValue() > tile2.getValue() ? 1 : -1;
                    });

            maxPosOpt.ifPresent(value -> {
                var maxPos = maxPosOpt.get().getKey();
                setCurrentPos(maxPos);
            });
        } else {
            // TODO: Check Q-surrounding from q-tables

            int rand = (new Random()).nextInt(7) + 1;

            switch (rand) {
                case 1:
                    currentPos.setX(currentPos.getX() - 1);
                    currentPos.setY(currentPos.getY() - 1);
                    break;
                case 2:
                    currentPos.setY(currentPos.getY() - 1);
                    break;
                case 3:
                    currentPos.setX(currentPos.getX() + 1);
                    currentPos.setY(currentPos.getY() - 1);
                    break;
                case 4:
                    currentPos.setX(currentPos.getX() + 1);
                    break;
                case 5:
                    currentPos.setX(currentPos.getX() + 1);
                    currentPos.setY(currentPos.getY() + 1);
                    break;
                case 6:
                    currentPos.setY(currentPos.getY() + 1);
                    break;
                case 7:
                    currentPos.setX(currentPos.getX() - 1);
                    currentPos.setY(currentPos.getY() + 1);
                    break;
                case 8:
                    currentPos.setY(currentPos.getX() - 1);
                    break;
            }
        }

        return currentPath.isEmpty();
    }

    public Pos getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Pos currentPos) {
        this.currentPos = currentPos;
    }

    public List<Pos> getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(List<Pos> currentPath) {
        this.currentPath = currentPath;
    }

    public boolean isGoingToBase() {
        return goingToBase;
    }

    public void setGoingToBase(boolean goingToBase) {
        this.goingToBase = goingToBase;
    }

    public boolean isOnBase() {
        return onBase;
    }

    public void setOnBase(boolean onBase) {
        this.onBase = onBase;
    }

    public Pos getLastPos() {
        return lastPos;
    }

    public void setLastPos(Pos lastPos) {
        this.lastPos = lastPos;
    }
}
