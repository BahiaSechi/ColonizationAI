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
 * @file TileType.java
 * @version 1.0
 */

package simulation.planet.tiles;

import simulation.planet.exception.MissingTileTypeException;

import java.util.List;

public enum TileType {
    WATER(0, "/sprites/world/WATER.png"),
    BASE(1, "/sprites/world/BASE.png"),
    ORE(2, "/sprites/world/ORE.png"),
    BEDROCK(3, "/sprites/world/BEDROCK.png"),
    FOREST(4, "/sprites/world/FOREST.png"),
    DESERT(5, "/sprites/world/DESERT.png"),
    FOOD(6, "/sprites/world/FOOD.png"),
    OBSTACLE(7, "/sprites/world/OBSTACLE.png"),
    MEADOW_DRY(8, "/sprites/world/MEADOW_DRY.png"),
    MEADOW_NORMAL(9, "/sprites/world/MEADOW_NORMAL.png"),
    MEADOW_GREASY(10, "/sprites/world/MEADOW_GREASY.png");

    private final int                 number;
    private final String              nameFile;
    private       List<Metamorphosis> metamorphoses;

    /**
     * @param number
     * @param nameFile
     */
    TileType(int number, String nameFile) {
        this.number = number;
        this.nameFile = nameFile;
    }

    public int getNumber() {
        return number;
    }

    public String getNameFile() {
        return nameFile;
    }

    /**
     * Find the type of the tile by a preset integer.
     *
     * @param nb The preset integer.
     * @return The researched type of tile.
     * @throws MissingTileTypeException If the number doesn't exit this exception is thrown.
     */
    public static TileType getType(int nb) throws MissingTileTypeException {
        int min = 0;
        int max = 0;
        for (TileType tt : TileType.values()) {
            if (tt.number == nb)
                return tt;
            max = tt.number;
        }
        throw new MissingTileTypeException("There is no TileType with a number of " + nb + " Please chose a case number between " + min + " and " + max + ".");
    }

    public void setMetamorphoses(List<Metamorphosis> metamorphoses) {
        this.metamorphoses = metamorphoses;
    }
}
