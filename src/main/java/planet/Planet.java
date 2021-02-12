package planet;

import planet.tiles.Observer;
import planet.tiles.Tile;
import planet.tiles.TileType;

public class Planet implements Observer {

    private Tile[][] map;
    private final int SIZE_X = 21;
    private final int SIZE = 21;
    private final int[][] initialState = {
            {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
            {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0},
            {0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
            {0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0},
            {0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,0,0,1,0,0,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,2,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,1},
            {0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
            {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1}
    };

    public void intialize() {
        map = new Tile[21][21];
        for (int x = 0; x< SIZE_X; x++) {
            for (int y = 0; y< SIZE; y++) {
                TileType type = TileType.FOREST;
                if (initialState[y][x] != 0) {
                    type = initialState[y][x] == 1 ? TileType.BEDROCK : TileType.BASE;
                }
                map[y][x] = new Tile(x, y, 10,10, type);
            }
        }

        afficheDebug();
    }

    public Tile[][] getRecentChanges() {
        return this.map;
    }

    public void update() {

    }

    private void afficheDebug() {
        for (int x = 0; x< SIZE_X; x++) {
            for (int y = 0; y< SIZE; y++) {
                System.out.print(map[y][x].getType());
            }
            System.out.println();
        }
    }
}
