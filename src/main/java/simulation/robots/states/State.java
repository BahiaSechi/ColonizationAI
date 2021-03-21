package simulation.robots.states;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import simulation.robots.Pos;
import simulation.robots.Robot;

import java.util.Optional;

public abstract class State {
    @Getter(onMethod_ = {@Synchronized})
    @Setter(onMethod_ = {@Synchronized})
    private Pos pos;

    public State(Pos pos) {
        this.pos = pos;
    }

    public State(State state) {
        this.pos = state.getPos();
    }

    public abstract Pair<Integer, Optional<State>> nextMove(Robot robot);

    public abstract State nextState();
}
