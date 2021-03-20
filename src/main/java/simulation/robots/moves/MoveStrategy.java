package simulation.robots.moves;

import javafx.util.Pair;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.List;
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
     * Choose the best move possible according to the Q-table in OperatorRobot.
     *
     * @param robot The robot to move
     * @return The new pos
     */
    public abstract Pos bestMove(Robot robot);


//    Map<Pos, Double> surrounding = robot.getViewSensor().exploitableSurrounding(robot);
//        if (!surrounding.isEmpty()) {
//            var maxPosOpt = surrounding
//                    .entrySet()
//                    .stream()
//                    .max((tile1, tile2) -> {
//                        if (tile1.getValue() == tile2.getValue()) return 0;
//                        return tile1.getValue() > tile2.getValue() ? 1 : -1;
//                    });
//
//            maxPosOpt.ifPresent(value -> {
//                var maxPos = maxPosOpt.get().getKey();
//                setCurrentPos(maxPos);
//            });
//        }

    /**
     * Choose a random location to go next. Does not update robot pos directly !
     *
     * @param robot The robot to move
     * @return The new pos
     */
    public Pair<Pos, Action> randomMove(Robot robot) {
        int rand = (new Random()).nextInt(4);
        Pos newPos;
        Action action;
        Pos currentPos = robot.getState().getPos();


        switch (rand) {
            case 0:
                newPos = new Pos(currentPos.getX(), currentPos.getY() + 1);
                action = Action.MOVE_UP;
                break;
            case 1:
                newPos = new Pos(currentPos.getX(), currentPos.getY() - 1);
                action = Action.MOVE_DOWN;
                break;
            case 2:
                newPos = new Pos(currentPos.getX() - 1, currentPos.getY());
                action = Action.MOVE_LEFT;
                break;
            case 3:
                newPos = new Pos(currentPos.getX() + 1, currentPos.getY());
                action = Action.MOVE_RIGHT;
                break;
            default:
                newPos = currentPos;
                action = null;
        }

        return new Pair<>(newPos, action);
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
