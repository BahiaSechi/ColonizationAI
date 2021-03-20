package simulation;

import simulation.planet.Planet;
import simulation.planet.exception.MissingTileTypeException;
import simulation.robots.Pos;
import simulation.robots.RobotController;
import simulation.robots.RobotFactory;

public class Game {

    private Planet planet;

    public Game() {

        planet = new Planet();

        try {
            planet.initialize();
        } catch (MissingTileTypeException e) {
            e.printStackTrace();
        }

        RobotController controller = new RobotController(new Pos(12, 12));
        int aId = 1;
        controller.addRobot(aId, RobotFactory.createExtractorRobot(controller, aId++, new Pos(0, 0)));
        controller.addRobot(aId, RobotFactory.createExtractorRobot(controller, aId++, new Pos(0, 0)));
        controller.addRobot(aId, RobotFactory.createPipelinerRobot(controller, aId++, new Pos(0, 0)));
    }

    public Planet getPlanet() { return planet; }
}

