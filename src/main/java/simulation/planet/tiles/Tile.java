package simulation.planet.tiles;

import simulation.planet.exploitability.Exploitability;

import java.util.LinkedList;
import java.util.List;

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
}
