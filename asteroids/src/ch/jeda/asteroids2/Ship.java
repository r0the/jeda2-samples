package ch.jeda.asteroids2;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Ship extends Body implements KeyDownListener, KeyUpListener {

    private boolean leftEngine;
    private boolean rightEngine;
    private boolean mainEngine;
    private Image thrust;
    private Image steer;

    public Ship() {
        setImage(new Image("res:drawable/ship.png"), 2, 2);
        addShape(new Circle(0, 0, 1));
        thrust = new Image("res:drawable/thrust.png");
        steer = new Image("res:drawable/steer.png");
    }

    @Override
    protected void drawDecoration(Canvas canvas) {
        canvas.setAlignment(Alignment.CENTER);
        if (mainEngine) {
            canvas.drawImage(-1.5, 0, thrust);
        }

        if (leftEngine) {
            canvas.drawImage(-0.3, -1, steer);
        }

        if (rightEngine) {
            canvas.drawImage(-0.3, 1, steer);
        }
    }

    @Override
    public void onKeyDown(KeyEvent event) {
        if (event.getKey() == Key.UP) {
            mainEngine = true;
        }
        else if (event.getKey() == Key.LEFT) {
            leftEngine = true;
        }
        else if (event.getKey() == Key.RIGHT) {
            rightEngine = true;
        }
    }

    @Override
    public void onKeyUp(KeyEvent event) {
        if (event.getKey() == Key.UP) {
            mainEngine = false;
        }
        else if (event.getKey() == Key.LEFT) {
            leftEngine = false;
        }
        else if (event.getKey() == Key.RIGHT) {
            rightEngine = false;
        }
    }

    @Override
    public void step(double dt) {
        super.step(dt);
        if (mainEngine) {
            applyLocalForceDeg(20, 0);
        }

        if (leftEngine) {
            applyLocalForceDeg(10, 0, 0, -1);
        }

        if (rightEngine) {
            applyLocalForceDeg(10, 0, 0, 1);
        }
    }
}
