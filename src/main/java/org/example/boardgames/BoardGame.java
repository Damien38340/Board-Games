package org.example.boardgames;

import org.example.player.Cell;
import org.example.views.View;

public abstract class BoardGame {
    protected int size;
    protected Cell[][] cells;
    View view;
}
