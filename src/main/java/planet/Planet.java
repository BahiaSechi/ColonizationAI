package planet;

import robots.Pos;

import java.util.Arrays;
import java.util.List;

public class Planet {

    public Tile[][] map;

    public List<Tile> getSurrounding(Pos absolutePos) {
        return List.of(null);
    }

    public Tile getTile(int x, int y) {
        return map[x][y];
    }
}
