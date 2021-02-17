package simulation.planet.tiles;

import simulation.planet.exploitability.Exploitability;
import simulation.planet.exploitability.LimitedExploit;
import simulation.planet.exploitability.NotExploitable;

public class TileFactory {
    public TileFactory() {

    }

    public Tile createTile(int x, int y, int tileWidth, int tileHeight, TileType tt) {
        Exploitability e = new NotExploitable();
        switch (tt) {
            case WATER:
                e = new LimitedExploit(200000);
                break;
            case ORE:
            case FOREST:
            case FOOD:
            case MEADOW_DRY:
            case MEADOW_NORMAL:
            case MEADOW_GREASY:
                e = new LimitedExploit(100);
                break;
        }

        return new Tile(x, y, tileWidth, tileHeight, tt, e);
    }

}
