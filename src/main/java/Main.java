import planet.Planet;
import planet.tiles.Tile;
import planet.tiles.TileType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello !");

        // Tests tiles
        Tile tile = new Tile(5,8,7,5, TileType.WATER);
        System.out.println(tile.getTileX());
        System.out.println(tile.getType());

        Planet p = new Planet();
        p.intialize();
    }
}
