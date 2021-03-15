package simulation.robots.tools;


import simulation.robots.Robot;

public class Extractor implements Tool {
    @Override
    public void use(Robot robot) {
        System.out.println("Extracteur en marche !");
    }
}
