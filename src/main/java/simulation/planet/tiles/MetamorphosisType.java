package simulation.planet.tiles;

public enum MetamorphosisType {
    LIMITED(0,0,1,5),
    SMALL(1,5,10,15),
    MEDIUM(10,15,50,60),
    LARGE(50,60,75,85),
    IMPORTANT(75,85,100,100);

    public int ascentStart, ascentEnd, descentStart, descentEnd;

    MetamorphosisType(int ascentStart, int ascentEnd, int descentStart, int descentStop) {
        this.ascentStart = ascentStart;
        this.ascentEnd = ascentEnd;
        this.descentStart = descentStart;
        this.descentEnd = descentStop;
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

        return null;
    }
}
