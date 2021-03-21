package simulation.robots.moves;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.List;
import java.util.Random;

public abstract class MoveStrategy {

    /**
     * currentPath holds a path that the robot must follow. It is optional. It can be a path leading to the base.
     */
    @Getter
    @Setter
    private List<Pos> currentPath;

    /**
     * The actual position of the robot, relative to its base.
     */
    @Getter
    @Setter
    private boolean goingToBase = false;
    @Getter
    @Setter
    private boolean onBase      = false;

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
    public abstract Pair<Pos, Action> bestMove(Robot robot);


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

    protected Action bestActionFromState(int[] actions) {
        if (actions.length > 4) {
            return null;
        }
        int max = -1;

        for (int action : actions) {
            if (action > max) max = action;
        }

        switch (max) {
            case 0:
                return Action.MOVE_UP;
            case 1:
                return Action.MOVE_DOWN;
            case 2:
                return Action.MOVE_LEFT;
            case 3:
                return Action.MOVE_RIGHT;
            default:
                return null;
        }
    }

    protected Pos doAction(Robot robot, Action action) {
        Pos currentPos = robot.getState().getPos();
        Pos newPos;

        switch (action) {
            case MOVE_UP:
                newPos = new Pos(currentPos.getX(), currentPos.getY() + 1);
                break;
            case MOVE_DOWN:
                newPos = new Pos(currentPos.getX(), currentPos.getY() - 1);
                break;
            case MOVE_LEFT:
                newPos = new Pos(currentPos.getX() - 1, currentPos.getY());
                break;
            case MOVE_RIGHT:
                newPos = new Pos(currentPos.getX() + 1, currentPos.getY());
                break;
            default:
                newPos = currentPos;
        }

        return newPos;
    }
}
