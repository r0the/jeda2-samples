package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class DrawingApp extends Program implements PointerMovedListener, ActionListener {

    private View view;
    private Canvas canvas;
    private int radius = 10;

    @Override
    public void run() {
        view = new View();
        canvas = view.getBackground();
        view.add(new ActionButton(60, 60, Icon.DELETE));
        addColorButton(60, 130, Color.LIGHT_GREEN_500);
        addColorButton(60, 200, Color.RED_500);
        addColorButton(60, 270, Color.BLACK);
        view.addEventListener(this);
    }

    @Override
    public void onAction(ActionEvent event) {
        if ("DELETE".equals(event.getName())) {
            Color oldColor = canvas.getColor();
            canvas.setColor(Color.WHITE);
            canvas.fill();
            canvas.setColor(oldColor);
        }
        else if ("BRUSH".equals(event.getName())) {
            canvas.setColor(event.getWidget().getBackgroundColor());
        }
    }

    @Override
    public void onPointerMoved(PointerEvent event) {
        canvas.fillCircle(event.getCanvasX(), event.getCanvasY(), radius);
    }

    private void addColorButton(double x, double y, Color color) {
        ActionButton button = new ActionButton(x, y, Icon.BRUSH);
        button.setBackgroundColor(color);
        view.add(button);
    }
}
