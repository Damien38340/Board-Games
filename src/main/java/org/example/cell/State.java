package org.example.cell;

public enum State {

    EMPTY("   "),
    X(" 🟩 "),
    O(" 🟥 ");

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
