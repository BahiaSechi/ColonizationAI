package robots.moves;

import robots.Pos;

import java.util.List;

public class Immobile extends MoveStrategy {
    @Override
    public List<Pos> findNewPath() {
        return new List.of();
    }
}
