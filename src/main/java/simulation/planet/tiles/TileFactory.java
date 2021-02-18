package simulation.planet.tiles;

import simulation.planet.exploitability.Exploitability;
import simulation.planet.exploitability.LimitedExploit;
import simulation.planet.exploitability.NotExploitable;

import java.util.LinkedList;
import java.util.List;

public class TileFactory {
    public TileFactory() {

    }

    public Tile createTile(int x, int y, int tileWidth, int tileHeight, TileType tiletype) {
        Exploitability exploitability = new NotExploitable();
        List<Metamorphosis> metamorphoses = new LinkedList<>();

        // Exploitability
        switch (tiletype) {
            case WATER:
                exploitability = new LimitedExploit(200000);
                break;
            case ORE:
            case FOREST:
            case FOOD:
            case MEADOW_DRY:
            case MEADOW_NORMAL:
            case MEADOW_GREASY:
                exploitability = new LimitedExploit(100);
                break;
        }

        // Metamorphosis
        switch (tiletype) {
            case ORE:
                metamorphoses.add(new Metamorphosis(TileType.BEDROCK, 5.0f));
                break;
            case FOREST:
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_DRY, 20.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_NORMAL, 30.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_GREASY, 40.0f));
                metamorphoses.add(new Metamorphosis(TileType.DESERT, 9.0f));
                break;
            case FOOD:
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_GREASY, 50.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_NORMAL, 30.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_DRY, 10.0f));
                metamorphoses.add(new Metamorphosis(TileType.FOREST, 10.0f));
                break;
            case MEADOW_DRY:
                metamorphoses.add(new Metamorphosis(TileType.DESERT, 80.0f));
                metamorphoses.add(new Metamorphosis(TileType.FOOD, 19.0f));
                break;
            case MEADOW_NORMAL:
                metamorphoses.add(new Metamorphosis(TileType.FOOD, 30.0f));
                metamorphoses.add(new Metamorphosis(TileType.DESERT, 10.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_DRY, 60.0f));
                break;
            case MEADOW_GREASY:
                metamorphoses.add(new Metamorphosis(TileType.DESERT, 5.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_NORMAL, 40.0f));
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_DRY, 30.0f));
                metamorphoses.add(new Metamorphosis(TileType.FOOD, 25.0f));
                break;
            case DESERT:
                metamorphoses.add(new Metamorphosis(TileType.MEADOW_DRY, 65.0f));
                break;
            case OBSTACLE:
                metamorphoses.add(new Metamorphosis(TileType.ORE, 0.01f));
                metamorphoses.add(new Metamorphosis(TileType.BEDROCK, 0.01f));
                break;
            case BEDROCK:
                metamorphoses.add(new Metamorphosis(TileType.ORE, 0.01f));
                metamorphoses.add(new Metamorphosis(TileType.OBSTACLE, 0.5f));
                break;
        }


        return new Tile(x, y, tileWidth, tileHeight, tiletype, exploitability, metamorphoses);
    }

}
