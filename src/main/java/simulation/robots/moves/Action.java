package simulation.robots.moves;

public enum Action {
    MOVE_UP(0),
    MOVE_DOWN(1),
    MOVE_LEFT(2),
    MOVE_RIGHT(3),
    ;

    public final int label;

    Action(int label) {
        this.label = label;
    }
}
