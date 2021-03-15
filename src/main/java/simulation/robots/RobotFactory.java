package simulation.robots;

import simulation.robots.moves.MoveToOre;
import simulation.robots.moves.MoveToWater;
import simulation.robots.states.Exploring;
import simulation.sensors.lv223.OreSensor;
import simulation.sensors.lv223.WaterSensor;

public class RobotFactory {
    public static Robot createExtractorRobot(RobotController controller, int id) {
        return new Robot(id, new MoveToOre(), controller, new Exploring(), 0.5, new OreSensor());
    }

    public static Robot createPipelinerRobot(RobotController controller, int id) {
        return new Robot(id, new MoveToWater(), controller, new Exploring(), 0.5, new WaterSensor());
    }
}
