package simulation.planet;

import com.fuzzylite.Engine;
import com.fuzzylite.imex.FllImporter;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;
import simulation.planet.exception.MissingTileTypeException;
import simulation.planet.tiles.Observer;
import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileFactory;
import simulation.planet.tiles.TileType;

import java.io.File;
import java.io.IOException;

public class Planet implements Observer {

    private Tile[][] map;
    private Tile[] recentlyChangedTiles;
    private final int SIZE_X = 21;
    private final int SIZE_Y = 21;
    private TileFactory tileFactory = new TileFactory();

    private final int[][] squeleton = {
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}
    };

    private final int[][] initialState = {
            { 0,  0,  0,  0,  8, 10,  5,  5,  5,  5,  5,  5, 10,  8,  8,  8, 10,  8,  8,  2,  3},
            { 0,  0,  8,  8,  8, 10,  5,  5,  5,  5,  8, 10, 10, 10,  8, 10,  8,  8,  6,  3,  3},
            { 0,  8, 10, 10, 10,  8,  5,  5,  5,  8, 10, 10, 10, 10, 10, 10, 10,  9,  3,  0,  3},
            { 0,  3, 10, 10,  8,  8,  5,  5,  7,  8, 10,  0, 10, 10, 10, 10, 10,  9,  3,  3,  6},
            { 8,  3,  3,  3,  8,  8,  8,  5,  8,  8, 10,  0,  0, 10,  6,  9,  9,  9,  0,  0,  0},
            { 8,  8, 10,  3,  3,  3, 10,  8,  8,  8, 10, 10, 10,  6, 10,  9,  7,  0,  0,  0,  9},
            {10,  8,  3,  3,  3,  2, 10,  9,  9,  9, 10, 10, 10, 10, 10, 10,  0,  0,  0,  9, 10},
            { 8,  8,  3,  2,  3,  3,  3,  3,  9, 10, 10, 10, 10, 10, 10, 10,  3,  0,  9, 10, 10},
            {10,  8,  8,  8,  8,  3,  3,  3, 10,  8,  9,  8,  9,  9,  3,  3, 10,  6, 10,  9,  9},
            { 8, 10,  8,  8,  8,  8,  8,  3, 10, 10,  3, 10, 10,  3,  3, 10, 10, 10, 10,  9,  9},
            { 4,  8, 10,  8,  8,  8,  7,  3,  3, 10,  1,  3,  3, 10, 10,  8,  8, 10,  9,  0,  0},
            { 4, 10, 10,  8, 10, 10,  8,  8,  3,  0,  3,  3,  9, 10, 10,  9,  0,  9,  0,  0,  9},
            { 4, 10,  8,  0,  8,  8, 10,  8, 10, 10,  3,  3,  9,  9,  6,  0,  9,  9,  0,  4,  4},
            { 4, 10, 10,  9,  6,  0, 10, 10, 10,  6,  3, 10,  3,  9,  9,  6,  4,  4,  4,  4,  4},
            {10, 10, 10,  6,  6,  0, 10, 10, 10, 10, 10,  2,  3,  9,  8,  4,  4,  4,  4,  4, 10},
            {10,  6,  6, 10,  0,  0, 10,  7, 10, 10, 10,  3,  3,  8,  9,  4,  4, 10,  4, 10,  9},
            { 8,  8, 10, 10,  8,  8, 10, 10,  3,  3,  3,  3,  3,  3,  9, 10, 10, 10,  6,  8,  8},
            { 9,  9,  9,  9,  9,  3,  3,  3,  3,  3, 10,  9,  9,  3,  3,  3,  8, 10,  9,  9,  0},
            {10,  9,  3,  3,  3,  3,  3, 10,  9, 10, 10, 10, 10, 10, 10,  3,  3,  3,  0,  0,  0},
            { 0,  3,  3,  0,  0, 10, 10, 10,  0,  0,  9, 10, 10, 10, 10, 10,  2,  3,  3,  0,  0},
            { 3,  3,  0, 10,  7,  6, 10,  9,  8,  8, 10, 10, 10, 10, 10, 10,  8,  0,  0,  0,  0}
    };

    public void initialize() throws MissingTileTypeException {
        map = new Tile[21][21];
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                TileType type = TileType.getType(initialState[y][x]);
                map[y][x] = tileFactory.createTile(x, y, 10, 10, type);
            }
        }
    }

    public Tile[] getRecentlyChangedTiles() {
        return this.recentlyChangedTiles;
    }

    public Tile[][] getMap() { return map; }

    public int getSIZE_X() { return SIZE_X; }

    public int getSIZE_Y() { return SIZE_Y; }

    public int[][] getSqueleton() { return squeleton; }

    public int[][] getInitialState() { return initialState; }

    public void update() {
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                map[y][x] = tileFactory.createTile(x,y,10, 10, map[y][x].nextTile());
            }
        }
    }

    private void afficheDebug() {
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                System.out.printf("'%14s' ", map[y][x].getType());
            }
            System.out.println();
        }
    }

    public void metamorphosisLogic() {

        File fileFLL = new File("../resources/fuzzylogic/Metamorphosis.fll");

        try {

            Engine engine = new FllImporter().fromFile(fileFLL);

            StringBuilder status = new StringBuilder();
            if (!engine.isReady(status))
                throw new RuntimeException("[engine error] engine is not ready:n" + status);

            InputVariable sampledOre = engine.getInputVariable("sampledOre");
            InputVariable drawnedWater = engine.getInputVariable("drawnedWater");

            OutputVariable metamorphosis = engine.getOutputVariable("metamorphosis");

//            for (int i = 0; i <= 50; ++i){
//                double location = obstacle.getMinimum() + i * (obstacle.range() / 50);
//                obstacle.setValue(location);
//                engine.process();
//                FuzzyLite.logger().info(String.format(
//                        "obstacle.input = %s -> steer.output = %s",
//                        Op.str(location), Op.str(steer.getValue())));
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
