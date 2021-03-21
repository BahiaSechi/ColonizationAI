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
 * @file MetamorphosisType.java
 * @version 1.0
 */

package simulation.planet.tiles;

/**
 * Describe the shape of the membership function graph for the metamorphosis.
 */
public enum MetamorphosisType {
    LIMITED(0,0,1,5, 1),
    SMALL(1,5,10,15, 2),
    MEDIUM(10,15,50,60, 3),
    LARGE(50,60,75,85, 5),
    IMPORTANT(75,85,100,100, -1);

    public int ascentStart, ascentEnd, descentStart, descentEnd, metamorphosisArea;

    /**
     * Constructor.
     *
     * @param ascentStart When the variable start its ascent.
     * @param ascentEnd When the variable has arrived at it's top.
     * @param descentStart When the variable has start to descent.
     * @param descentStop When the variable is shut off.
     * @param metamorphosisArea The zone around the tile that will be modified.
     */
    MetamorphosisType(int ascentStart, int ascentEnd, int descentStart, int descentStop, int metamorphosisArea) {
        this.ascentStart = ascentStart;
        this.ascentEnd = ascentEnd;
        this.descentStart = descentStart;
        this.descentEnd = descentStop;
        this.metamorphosisArea = metamorphosisArea;
    }

    public static MetamorphosisType getMetamorphosisType(double pourcent) {
        MetamorphosisType previous = null;

        for (MetamorphosisType mt : MetamorphosisType.values()) {
            if (pourcent >= mt.ascentStart && pourcent <= mt.descentStart) {

                // If the pourcent is top flat of the trapezoid
                if (pourcent >= mt.ascentEnd && pourcent <= mt.descentStart) return mt;

                boolean ascent = pourcent >= mt.ascentStart && pourcent <= mt.ascentEnd;
                double mean = (mt.ascentStart + mt.ascentEnd)/2.0f;

                // If the pourcent is in the ascent of the current value
                if (ascent && mean <= pourcent) return mt;

                // Last possibility
                return previous;
            }
            previous = mt;
        }

        return MetamorphosisType.LIMITED;
    }
}
