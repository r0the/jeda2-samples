package ch.jeda.tictactoe;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class TicTacToe extends Program implements PointerDownListener {

    private Board board;
    private View view;

    @Override
    public void run() {
        board = new Board(3, 3);
        view = new View();
    }

    @Override
    public void onPointerDown(PointerEvent event) {
        event.getWorldX()
    }
}
