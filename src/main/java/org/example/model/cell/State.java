package org.example.model.cell;

/**
 * 3 different states in the game, we compare the states of the cells
 * to check winning conditions
 */
public enum State {

    EMPTY("   "),
    X("\u001B[35m X  \u001B[0m"),
    O("\u001B[36m O  \u001B[0m");

    private final String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
