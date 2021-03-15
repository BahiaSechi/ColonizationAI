package simulation.planet.tiles;

import simulation.planet.exploitability.Exploitability;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Tile extends ObservableTile {

    private int tileX, tileY, tileWidth, tileHeight;
    private TileType type;
    private Exploitability exploitability;
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
    }

    // TODO Implement exploit
    public void exploit() {}

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

    public TileType nextTile() {
        // Find a random number to find the next tile
        Random r = new Random();
        int low = 0;
        int high = 10000;
        int tmpResult = r.nextInt(high-low) + low;
        float result = tmpResult/100.0f;

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
