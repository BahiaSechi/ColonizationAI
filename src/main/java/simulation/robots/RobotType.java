package simulation.robots;

import lombok.Getter;

/**
 *
 */
public enum RobotType {
    EXTRACTOR("/sprites/robot/ORE_ROBOT.png"),
    PIPELINER("/sprites/robot/WATER_ROBOT.png"),
    HARVESTER("/sprites/robot/MEADOW_ROBOT.png"),
    FARMER("/sprites/robot/FOOD_ROBOT.png"),
    OPERATOR("/sprites/robot/OPERATOR_ROBOT.png"),
    CUSTOM("/sprites/robot/OPERATOR_ROBOT.png");

    @Getter
    private final String              nameFile;

    RobotType(String nameFile) {
        this.nameFile = nameFile;
    }
}
