package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class DrawingApp extends Program implements PointerMovedListener, ActionListener {

    private static final int ACTION_CLEAR = 0;
    private static final int ACTION_SET_COLOR = 1;
    private View view;
    private Canvas canvas;
    private int radius = 10;

    @Override
    public void run() {
        view = new View();
        canvas = view.getBackground();
        view.add(new ActionButton(60, 60, Icon.DELETE, ACTION_CLEAR));
        addColorButton(60, 130, Color.LIGHT_GREEN_500);
        addColorButton(60, 200, Color.RED_500);
        addColorButton(60, 270, Color.BLACK);
        view.addEventListener(this);
    }

    @Override
    public void onAction(ActionEvent event) {
        if (event.getId() == ACTION_CLEAR) {
            Color oldColor = canvas.getColor();
            canvas.setColor(Color.WHITE);
            canvas.fill();
            canvas.setColor(oldColor);
        }
        else if (event.getId() == ACTION_SET_COLOR) {
            canvas.setColor(event.getWidget().getBackgroundColor());
        }
    }

    @Override
    public void onPointerMoved(PointerEvent event) {
        canvas.fillCircle(event.getViewX(), event.getViewY(), radius);
    }

    private void addColorButton(double x, double y, Color color) {
        ActionButton button = new ActionButton(x, y, Icon.BRUSH, ACTION_SET_COLOR);
        button.setBackgroundColor(color);
        view.add(button);
    }
}
