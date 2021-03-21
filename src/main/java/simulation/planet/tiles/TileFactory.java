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
 * @date February 2021
 * @file TileFactory.java
 * @version 1.0
 */

package simulation.planet.tiles;

import simulation.planet.exploitability.Exploitability;
import simulation.planet.exploitability.LimitedExploit;
import simulation.planet.exploitability.NotExploitable;

import java.util.LinkedList;
import java.util.List;

public class TileFactory {
    public TileFactory() {

    }

    /**
     * Standard method to create a specific type of tile.
     *
     * @param x The horizontal coordinate of the tile.
     * @param y The vertical coordinate of the tile.
     * @param tileWidth The width of the tile.
     * @param tileHeight The height of the tile.
     * @param tileType The type of the tile.
     * @return The specified type of tile with its attributes.
     */
    public Tile createTile(int x, int y, int tileWidth, int tileHeight, TileType tileType) {
        Exploitability exploitability = new NotExploitable();
        List<Metamorphosis> metamorphosis = new LinkedList<>();
        TileType afterTotalExploit = null;

        // Exploitability
        switch (tileType) {
            case WATER:
                exploitability = new LimitedExploit(200000);
                break;
            case ORE:
            case FOREST:
            case FOOD:
            case MEADOW_DRY:
            case MEADOW_NORMAL:
            case MEADOW_GREASY:
                exploitability = new LimitedExploit(100);
                break;
        }

        // Metamorphosis
        switch (tileType) {
            case ORE:
                metamorphosis.add(new Metamorphosis(TileType.BEDROCK, 5.0f));
                break;
            case FOREST:
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_DRY, 20.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_NORMAL, 30.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_GREASY, 40.0f));
                metamorphosis.add(new Metamorphosis(TileType.DESERT, 9.0f));
                break;
            case FOOD:
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_GREASY, 50.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_NORMAL, 30.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_DRY, 10.0f));
                metamorphosis.add(new Metamorphosis(TileType.FOREST, 10.0f));
                break;
            case MEADOW_DRY:
                metamorphosis.add(new Metamorphosis(TileType.DESERT, 80.0f));
                metamorphosis.add(new Metamorphosis(TileType.FOOD, 19.0f));
                break;
            case MEADOW_NORMAL:
                metamorphosis.add(new Metamorphosis(TileType.FOOD, 30.0f));
                metamorphosis.add(new Metamorphosis(TileType.DESERT, 10.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_DRY, 60.0f));
                break;
            case MEADOW_GREASY:
                metamorphosis.add(new Metamorphosis(TileType.DESERT, 5.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_NORMAL, 40.0f));
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_DRY, 30.0f));
                metamorphosis.add(new Metamorphosis(TileType.FOOD, 25.0f));
                break;
            case DESERT:
                metamorphosis.add(new Metamorphosis(TileType.MEADOW_DRY, 65.0f));
                break;
            case OBSTACLE:
                metamorphosis.add(new Metamorphosis(TileType.ORE, 0.01f));
                metamorphosis.add(new Metamorphosis(TileType.BEDROCK, 0.01f));
                break;
            case BEDROCK:
                metamorphosis.add(new Metamorphosis(TileType.ORE, 0.01f));
                metamorphosis.add(new Metamorphosis(TileType.OBSTACLE, 0.5f));
                break;
        }

        // Final Form
        switch (tileType) {
            case WATER:
                afterTotalExploit = TileType.DESERT;
                break;
            case ORE:
                afterTotalExploit = TileType.BEDROCK;
                break;
        }

        return new Tile(x, y, tileWidth, tileHeight, tileType, exploitability, metamorphosis, afterTotalExploit);
    }

}
