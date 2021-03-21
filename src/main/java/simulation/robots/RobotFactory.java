package simulation.robots;

import simulation.robots.moves.MoveToOre;
import simulation.robots.moves.MoveToWater;
import simulation.robots.states.Exploring;
import simulation.sensors.lv223.OreSensor;
import simulation.sensors.lv223.WaterSensor;

public class RobotFactory {
    public static Robot createExtractorRobot(RobotController controller, int id, Pos pos) {
        return new Robot(id, new MoveToOre(), controller, new Exploring(pos), 0.5f, new OreSensor(), RobotType.EXTRACTOR);
    }

    public static Robot createPipelinerRobot(RobotController controller, int id, Pos pos) {
        return new Robot(id, new MoveToWater(), controller, new Exploring(pos), 0.5f, new WaterSensor(), RobotType.PIPELINER);
    }
}
