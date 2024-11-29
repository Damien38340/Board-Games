package org.example.model.cell;

public class Cell {

    private State state;

    public Cell() {
        this.state = State.EMPTY;
    }

    public State getState() {
        return this.state;
    }

    public String getRepresentation() {
        return this.state.getValue();
    }

    public boolean isOccupied() {
        return State.EMPTY.equals(this.state);
    }

    public boolean occupy(State state) {
        if (this.isOccupied()){
            return false;
        }
        this.state = state;
        return true;
    }

    public void clear(){
        this.state = State.EMPTY;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String toString() {
        return getRepresentation();
    }


}
