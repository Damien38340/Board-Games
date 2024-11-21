package org.example.boardgames;

import org.example.cell.Cell;
import org.example.views.View;

public abstract class BoardGame {
    protected int size;
    protected Cell[][] cells;
    View view;
}
