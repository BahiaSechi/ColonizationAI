package simulation.robots;

import lombok.Data;

/**
 *
 */
@Data
public class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
