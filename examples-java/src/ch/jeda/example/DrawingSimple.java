package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class DrawingSimple extends Program implements PointerMovedListener {

    private View view;
    private int radius = 10;

    @Override
    public void run() {
        view = new View();
        view.addEventListener(this);
    }

    @Override
    public void onPointerMoved(PointerEvent event) {
        view.getBackground().fillCircle(event.getViewX(), event.getViewY(), radius);
    }
}
