package planet;

import planet.tiles.Observer;
import planet.tiles.Tile;

public class Planet implements Observer {

    public Tile[][] map;

    public void intialize() {

    }

    public Tile[][] getRecentChanges() {
        return this.map;
    }

    public void update() {

    }
}
