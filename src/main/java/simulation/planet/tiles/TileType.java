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
