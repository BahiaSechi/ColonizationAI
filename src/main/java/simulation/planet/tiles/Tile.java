/**
 * Address :
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 * Note :
 * This file is owned by an ENSICAEN student.  No portion of this
 * document may be reproduced, copied  or revised without written
 * permission of the authors.
 *
 * @author PRUNIER Bastien <bastien.prunier@ecole.ensicaen.fr>
 * @author RABOTIN Mateo <mateo.rabotin@ecole.ensicaen.fr>
 * @author SECHI Bahia <bahia.sechi@ecole.ensicaen.fr>
 * @author SERVAT Clement <clement.servat@ecole.ensicaen.fr>
 *
 * @date February 2021
 * @file Tile.java
 * @version 1.0
 */

package simulation.planet.tiles;

import lombok.Data;
import simulation.planet.exploitability.Exploitability;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Data
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

    /**
     * Extract the specified resources.
     *
     * TODO Implement BETTER exploit
     *
     * @param amount The amount of resources to extract.
     * @return The amount left to extract if there not enough left of resources.
     */
    public int exploit(int amount) {
        if (this.exploitability.getMax() != 0) {
            int oldCurrent = this.exploitability.getCurrent();
            int newCurrent = oldCurrent - amount;
            this.exploitability.setCurrent(Math.max(newCurrent, 0));
            return oldCurrent<amount ? amount-oldCurrent : 0;
        }
        return amount;
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
