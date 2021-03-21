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
 * @date February 2021
 * @file Game.java
 * @version 1.0
 */

package simulation;

import lombok.Getter;
import simulation.planet.Planet;
import simulation.planet.exception.MissingTileTypeException;
import simulation.robots.Pos;
import simulation.robots.RobotController;
import simulation.robots.RobotFactory;

public class Game {

    @Getter
    private Planet          planet;
    @Getter
    private RobotController robotController;

    /**
     *
     */
    public Game() {

        planet = new Planet();
        robotController = new RobotController(new Pos(planet.getSIZE_X()/2, planet.getSIZE_Y()/2), planet);

        try {
            planet.initialize();
        } catch (MissingTileTypeException e) {
            e.printStackTrace();
        }

        int aId = 0;
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));
        robotController.addRobot(aId, RobotFactory.createExtractorRobot(robotController, aId++, new Pos(0, 0), planet));

    }

    /**
     *
     */
    public void runDay() {
        // RUN THE ROBOTS
        robotController.runDay();

        // RUN THE PLANET
        planet.update();
    }
}

