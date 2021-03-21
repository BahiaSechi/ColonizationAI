package simulation.robots.tools;


import simulation.planet.tiles.Tile;
import simulation.planet.tiles.TileType;
import simulation.robots.OperatorRobot;
import simulation.robots.Pos;
import simulation.robots.Robot;

public class Extractor extends Tool {
    @Override
    public void use(Robot robot) {
        Pos pos = robot.getState().getPos();
        Tile tile = planet.getTile(pos.getX(), pos.getY());

        if (tile.getType() == TileType.ORE) {
            OperatorRobot operator = robot.getController().getOperator();
            int exploit_amount = 10;
            planet.exploit(pos, exploit_amount);
            operator.setOreStock(operator.getOreStock() + exploit_amount);
        }
    }
}
