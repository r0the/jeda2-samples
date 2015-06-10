package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Basketballs extends Program implements SensorListener, ActionListener {

    private static final int ACTION_UP = 0;
    private static final int ACTION_DOWN = 1;
    private static final int ACTION_LEFT = 2;
    private static final int ACTION_RIGHT = 3;
    private PhysicsView view;
    private Image basketball;

    @Override
    public void run() {
        view = new PhysicsView();
        view.add(new Box(view));
        basketball = new Image("res:drawable/basketball.png");

        int i = 0;
        while (i < 50) {
            addBall();
            ++i;
        }

        if (Jeda.isSensorAvailable(SensorType.GRAVITY)) {
            Jeda.enableSensor(SensorType.GRAVITY);
        }
        else {
            view.add(new ActionButton(40, view.getHeightDp() / 2, Icon.CHEVRON_LEFT, ACTION_LEFT));
            view.add(new ActionButton(view.getWidthDp() - 40, view.getHeightDp() / 2, Icon.CHEVRON_RIGHT, ACTION_RIGHT));
            view.add(new ActionButton(view.getWidthDp() / 2, view.getHeightDp() - 40, Icon.CHEVRON_UP, ACTION_UP));
            view.add(new ActionButton(view.getWidthDp() / 2, 40, Icon.CHEVRON_DOWN, ACTION_DOWN));
        }

        view.addEventListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        view.setGravity(event.getX(), event.getY());
    }

    private void addBall() {
        Body body = new Body();
        body.addShape(new Circle(0, 0, 0.5));
        body.setPosition(Math.random() * view.getWidthM(), Math.random() * view.getHeightM());
        body.setImage(basketball, 1, 1);
        view.add(body);
    }

    @Override
    public void onAction(ActionEvent event) {
        if (event.getId() == ACTION_LEFT) {
            view.setGravity(-9.81, 0);
        }
        else if (event.getId() == ACTION_RIGHT) {
            view.setGravity(9.81, 0);
        }
        else if (event.getId() == ACTION_UP) {
            view.setGravity(0, 9.81);
        }
        else if (event.getId() == ACTION_DOWN) {
            view.setGravity(0, -9.81);
        }
    }
}
