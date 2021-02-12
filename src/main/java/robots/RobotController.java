package robots;

public class RobotController {
    Pos colonyCenter;

    public RobotController(Pos colonyCenter) {
        this.colonyCenter = colonyCenter;
    }

    public Pos getAbsolutePos(Robot robot) {
        return new Pos(colonyCenter.getX() + robot.getMovement().getCurrentPos().getX(),
                colonyCenter.getY() + robot.getMovement().getCurrentPos().getY());
    }
}
