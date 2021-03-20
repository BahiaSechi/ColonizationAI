package simulation.robots.moves;

import simulation.robots.Pos;

import java.util.LinkedList;
import java.util.List;

public class Immobile extends MoveStrategy {
    @Override
    public List<Pos> findNewPath() {
        //return new List.of(null);
        return new LinkedList<>();
    }
}
