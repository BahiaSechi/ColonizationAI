package planet.tiles;

import planet.exception.MissingTileTypeException;

public enum TileType {
    WATER(0),
    BASE(1),
    ORE(2),
    BEDROCK(3),
    FOREST(4),
    DESERT(5),
    FOOD(6),
    OBSTACLE(7),
    MEADOW_DRY(8),
    MEADOW_NORMAL(9),
    MEADOW_GREASY(10);

    private int number;
    TileType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static TileType getType(int nb) throws MissingTileTypeException {
        int min = 0;
        int max = 0;
        for(TileType tt : TileType.values()) {
            if (tt.number == nb)
                return tt;
            max = tt.number;
        }
        throw new MissingTileTypeException("There is no TileType with a number of " + nb + " Please chose a case number between " + min + " and " + max + ".");
    }
}
