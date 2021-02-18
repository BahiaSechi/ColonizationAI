package simulation;

import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        // Tests tiles
        Tile tile = new Tile(5,8,7,5, TileType.WATER, null, null);
        System.out.println(tile.getTileX());
        System.out.println(tile.getType());

    }
}
