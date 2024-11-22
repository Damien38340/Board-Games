package org.example.cell;

public class Cell {

    State state;

    public Cell() {
        this.state = State.EMPTY;
    }

    public State getState() {
        return this.state;
    }

    public String getRepresentation() {
        return this.state.getValue();
    }

    public void setState(State state) {
        this.state = state;
    }

    public String toString() {
        return getRepresentation();
    }


}
