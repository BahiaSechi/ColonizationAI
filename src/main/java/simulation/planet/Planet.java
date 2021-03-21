/**
 * Address :
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 * Note :
 * This file is owned by an ENSICAEN student.  No portion of this
 * document may be reproduced, copied  or revised without written
 * permission of the authors.
 *
 * @author PRUNIER Bastien <bastien.prunier@ecole.ensicaen.fr>
 * @author RABOTIN Mateo <mateo.rabotin@ecole.ensicaen.fr>
 * @author SECHI Bahia <bahia.sechi@ecole.ensicaen.fr>
 * @author SERVAT Clement <clement.servat@ecole.ensicaen.fr>
 *
 * @date February 2021
 * @file Planet.java
 * @version 1.0
 */

package simulation.planet;

import com.fuzzylite.Engine;
import com.fuzzylite.FuzzyLite;
import com.fuzzylite.Op;
import com.fuzzylite.imex.FllImporter;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;
import lombok.Data;
import simulation.planet.exception.EmptyExploitableTileException;
import simulation.planet.exception.MissingTileTypeException;
import simulation.planet.tiles.*;
import simulation.robots.Pos;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
public class Planet implements Observer {

    private       Tile[][]   map;
    private       List<Tile> recentlyChangedTiles;
    private final int        SIZE_X      = 21;
    private final int SIZE_Y   = 21;
    private final int         WIDTH       = 10;
    private final int         HEIGHT      = 10;
    private       TileFactory tileFactory = new TileFactory();
    private       Engine      engine = null;

    /** The skeleton of the planet */
    private final int[][] skeleton = {
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

    /** Initial state of the map with the predefined integers */
    private final int[][] initialState = {
            {0, 0, 0, 0, 8, 10, 5, 5, 5, 5, 5, 5, 10, 8, 8, 8, 10, 8, 8, 2, 3},
            {0, 0, 8, 8, 8, 10, 5, 5, 5, 5, 8, 10, 10, 10, 8, 10, 8, 8, 6, 3, 3},
            {0, 8, 10, 10, 10, 8, 5, 5, 5, 8, 10, 10, 10, 10, 10, 10, 10, 9, 3, 0, 3},
            {0, 3, 10, 10, 8, 8, 5, 5, 7, 8, 10, 0, 10, 10, 10, 10, 10, 9, 3, 3, 6},
            {8, 3, 3, 3, 8, 8, 8, 5, 8, 8, 10, 0, 0, 10, 6, 9, 9, 9, 0, 0, 0},
            {8, 8, 10, 3, 3, 3, 10, 8, 8, 8, 10, 10, 10, 6, 10, 9, 7, 0, 0, 0, 9},
            {10, 8, 3, 3, 3, 2, 10, 9, 9, 9, 10, 10, 10, 10, 10, 10, 0, 0, 0, 9, 10},
            {8, 8, 3, 2, 3, 3, 3, 3, 9, 10, 10, 10, 10, 10, 10, 10, 3, 0, 9, 10, 10},
            {10, 8, 8, 8, 8, 3, 3, 3, 10, 8, 9, 8, 9, 9, 3, 3, 10, 6, 10, 9, 9},
            {8, 10, 8, 8, 8, 8, 8, 3, 10, 10, 3, 10, 10, 3, 3, 10, 10, 10, 10, 9, 9},
            {4, 8, 10, 8, 8, 8, 7, 3, 3, 10, 1, 3, 3, 10, 10, 8, 8, 10, 9, 0, 0},
            {4, 10, 10, 8, 10, 10, 8, 8, 3, 0, 3, 3, 9, 10, 10, 9, 0, 9, 0, 0, 9},
            {4, 10, 8, 0, 8, 8, 10, 8, 10, 10, 3, 3, 9, 9, 6, 0, 9, 9, 0, 4, 4},
            {4, 10, 10, 9, 6, 0, 10, 10, 10, 6, 3, 10, 3, 9, 9, 6, 4, 4, 4, 4, 4},
            {10, 10, 10, 6, 6, 0, 10, 10, 10, 10, 10, 2, 3, 9, 8, 4, 4, 4, 4, 4, 10},
            {10, 6, 6, 10, 0, 0, 10, 7, 10, 10, 10, 3, 3, 8, 9, 4, 4, 10, 4, 10, 9},
            {8, 8, 10, 10, 8, 8, 10, 10, 3, 3, 3, 3, 3, 3, 9, 10, 10, 10, 6, 8, 8},
            {9, 9, 9, 9, 9, 3, 3, 3, 3, 3, 10, 9, 9, 3, 3, 3, 8, 10, 9, 9, 0},
            {10, 9, 3, 3, 3, 3, 3, 10, 9, 10, 10, 10, 10, 10, 10, 3, 3, 3, 0, 0, 0},
            {0, 3, 3, 0, 0, 10, 10, 10, 0, 0, 9, 10, 10, 10, 10, 10, 2, 3, 3, 0, 0},
            {3, 3, 0, 10, 7, 6, 10, 9, 8, 8, 10, 10, 10, 10, 10, 10, 8, 0, 0, 0, 0}
    };

    public void initialize() throws MissingTileTypeException {
        map = new Tile[21][21];
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                TileType type = TileType.getType(initialState[y][x]);
                map[y][x] = tileFactory.createTile(x, y, WIDTH, HEIGHT, type);
            }
        }

        this.recentlyChangedTiles = new LinkedList<>();

        File fileFLL = new File("src\\main\\resources\\fuzzylogic\\Metamorphosis.fll");

        try {
            this.engine = new FllImporter().fromFile(fileFLL);

            StringBuilder status = new StringBuilder();
            if (!engine.isReady(status))
                throw new RuntimeException("[engine error] engine is not ready:n" + status);

            InputVariable sampledOre = engine.getInputVariable("sampledOre");
            InputVariable drawnedWater = engine.getInputVariable("drawnedWater");

            OutputVariable metamorphosis = engine.getOutputVariable("metamorphosis");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //recuperation of the different engine variables
        InputVariable oreIn = engine.getInputVariable("sampledOre");
        InputVariable waterIn = engine.getInputVariable("drawnedWater");
        OutputVariable meta = engine.getOutputVariable("metamorphosis");

        double drawnedWater = 0.0f;
        double sampledOre = 0.0f;

        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (map[y][x].getExploitability().isExploitable()) {
                    double extractedResources = map[y][x].getExploitability().getMax() - map[y][x].getExploitability().getCurrent();
                    if (map[y][x].getType() == TileType.ORE) {
                        sampledOre += extractedResources;
                    } else if (map[y][x].getType() == TileType.WATER) {
                        drawnedWater += extractedResources;
                    }
                }
            }
        }

        oreIn.setValue(sampledOre);
        waterIn.setValue(drawnedWater/1000);
        engine.process();

        FuzzyLite.logger().info(String.format(
                "obstacle.input = %s -> steer.output = %s",
                Op.str(drawnedWater), Op.str(meta.getValue())));

        analyseFuzzyLogicOutPut(meta.getValue());

        this.recentlyChangedTiles.clear();
    }

    private void analyseFuzzyLogicOutPut(double metamorphosisPourcentage) {
        MetamorphosisType engineOutput = MetamorphosisType.getMetamorphosisType(metamorphosisPourcentage);
        List<Tile> toModify = new LinkedList<>();

        if (engineOutput == MetamorphosisType.IMPORTANT) {
            for (int y = 0; y < SIZE_Y; y++) {
                toModify.addAll(Arrays.asList(map[y]).subList(0, SIZE_X));
            }

        } else {
            for (Tile temp : this.recentlyChangedTiles) {
                for (Tile tile : this.getSurrounding(temp, engineOutput.metamorphosisArea)) {
                    if (!toModify.contains(tile)) {
                        toModify.add(tile);
                    }
                }
            }
        }

        for (Tile tile : toModify) {
            map[tile.getTileY()][tile.getTileX()] = this.tileFactory.createTile(tile.getTileX(), tile.getTileY(), WIDTH, HEIGHT, tile.nextTile());
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

    /**
     * Exploit a specifique tile.
     *
     * @param position The position of the Tile on the map.
     * @param amount The amount to exploit on the tile.
     */
    public void exploit(Pos position, int amount) {
        int x = position.getX();
        int y = position.getY();
        Tile tileTmp = this.map[y][x];
        try {
            tileTmp.exploit(amount);
        } catch (EmptyExploitableTileException e) {
            this.map[y][x] = tileFactory.createTile(x, y, WIDTH, HEIGHT, tileTmp.getAfterTotalExploit());
        }
        this.recentlyChangedTiles.add(tileTmp);
    }

    /**
     * A getter function to find the tile according to x and y.
     * @param x Abscissa axis.
     * @param y Ordinate axis.
     * @return The tile on the position (x,y).
     */
    public Tile getTile(int x, int y) {
        return this.map[y][x];
    }

    /**
     * A function to get the neighbors of a tile according to a given position.
     * @param position The position of the tile.
     * @param degree The number of neighbors we want to get.
     * @return A list of the tiles surrounding our tile according to the degree.
     */
    public List<Tile> getSurrounding(Pos position, int degree) {
        return getSurrounding(map[position.getY()][position.getX()], degree);
    }

    /**
     * A function to get the neighbors of a tile.
     * @param tile The tile we are looking at.
     * @param degree The number of neighbors we want to get. If it is degree 2, we get 8 neighbors of our tile and their
     *               neighbors.
     * @return A list of the tiles surrounding our tile according to the degree. Example : Degree = 1 ; We get the 8
     * neighbors.
     */
    public List<Tile> getSurrounding(Tile tile, int degree) {
        List<Tile> tilesAround = new LinkedList<>();

        for (int y = -degree ; y <= degree ; y++) {
            for (int x = -degree ; x <= degree; x++) {

                int newY = tile.getTileY()+y;
                int newX = tile.getTileX()+x;

                if (newY >= 0 && newX >= 0 && newX < this.SIZE_X && newY < this.SIZE_Y) {
                    tilesAround.add(map[newY][newX]);
                }
            }
        }
        return tilesAround;
    }
}
