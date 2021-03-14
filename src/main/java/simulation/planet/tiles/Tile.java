package simulation.planet.tiles;

import simulation.planet.exploitability.Exploitability;

import java.util.*;

public class Tile extends ObservableTile {

    private int tileX, tileY, tileWidth, tileHeight;
    private TileType            type;
    private Exploitability      exploitability;
    private List<Metamorphosis> metamorphoses;

    // Constructor
    public Tile(int tileX, int tileY, int tileWidth, int tileHeight, TileType type,
                Exploitability exploitability, List<Metamorphosis> metamorphoses) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.type = type;
        this.exploitability = exploitability;
        this.metamorphoses = metamorphoses;
        this.metamorphoses.sort(Comparator.comparing(m -> m.percentage));
        // Sort the metamorphoses possibilities, this way we can directly draw a random number and compare it to the
        // percentages.
    }

    // TODO Implement exploit
    public void exploit() {
    }

    // Getters & Setters

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    /**
     * Method using the state pattern design. A Tile, when metamorphosing, can transform in certain type of tile with
     * certain percentage. This method is choosing the next type of cell.
     *
     * @return The chosen tileType
     */
    public TileType nextTile() {
        // Find a random number to find the next tile
        Random r = new Random();
        int low = 0;
        int high = 10000;
        int tmpResult = r.nextInt(high - low) + low;
        float result = tmpResult / 100.0f;

        TileType tileType = this.type;

        // the proportions are already sorted in the constructor
        for (Metamorphosis m : this.metamorphoses) {
            if (result < m.percentage) {
                tileType = m.tiletype;
                break;
            }
        }

        return tileType;
    }
}
