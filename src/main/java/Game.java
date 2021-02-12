import planet.Planet;

public class Game {

    private Planet planet;

    public Game(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
}

