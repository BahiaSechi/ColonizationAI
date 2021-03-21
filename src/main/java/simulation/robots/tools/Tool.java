package simulation.robots.tools;

import simulation.planet.Planet;
import simulation.robots.Robot;

public abstract class Tool {
    Planet planet;

    public abstract void use(Robot robot);
}
