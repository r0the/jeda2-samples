package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Menu extends Program implements TickListener, ActionListener {

    private static final int CMD_CIRCLE = 1;
    private static final int CMD_RECTANGLE = 2;
    private View view;
    private double x;
    private double y;
    private int choice;

    @Override
    public void run() {
        view = new View();
        // Write initialization code here.
        view.addEventListener(this);
        view.add(new TextButton(100, 50, "Circle", CMD_CIRCLE));
        view.add(new TextButton(300, 50, "Rectangle", CMD_RECTANGLE));
        choice = 0;
        view.addEventListener(this);
    }

    public void moveCircle() {
        x = x + 1;
        view.getBackground().setColor(Color.RED);
        view.getBackground().fillCircle(x, y, 20);
    }

    public void moveRectangle() {
        y = y + 1;
        view.getBackground().setColor(Color.RED);
        view.getBackground().fillRectangle(x, y, 40, 30);
    }

    @Override
    public void onTick(TickEvent event) {
        view.getBackground().setColor(Color.WHITE);
        view.getBackground().fill();
        if (choice == 1) {
            moveCircle();
        }
        else if (choice == 2) {
            moveRectangle();
        }
    }

    @Override
    public void onAction(ActionEvent event) {
        if (event.getId() == CMD_CIRCLE) {
            choice = 1;
            x = 0;
            y = (int) view.getHeightDp() / 2;
            removeButtons();
        }
        if (event.getId() == CMD_RECTANGLE) {
            choice = 2;
            x = (int) view.getWidthDp() / 2;
            y = 0;
            removeButtons();
        }
    }

    void removeButtons() {
        for (TextButton button : view.getElements(TextButton.class)) {
            view.remove(button);
        }
    }
}
