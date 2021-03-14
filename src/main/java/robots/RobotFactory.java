package robots;

import robots.moves.MoveToOre;
import robots.moves.MoveToWater;
import robots.states.Exploring;
import sensors.lv223.OreSensor;
import sensors.lv223.WaterSensor;

public class RobotFactory {
    public static Robot createExtractorRobot(RobotController controller, int id) {
        return new Robot(id, new MoveToOre(), controller, new Exploring(), 0.5, new OreSensor());
    }

    public static Robot createPipelinerRobot(RobotController controller, int id) {
        return new Robot(id, new MoveToWater(), controller, new Exploring(), 0.5, new WaterSensor());
    }
}
