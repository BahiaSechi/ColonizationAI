package simulation.robots.tools;

import simulation.robots.Robot;

public class Gloves implements Tool {
    @Override
    public void use(Robot robot) {
        System.out.println("Gants opérationnel !");
    }
}
