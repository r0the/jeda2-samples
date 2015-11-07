package ch.jeda.platformer;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;
import java.util.Set;

public class Player extends Body implements KeyDownListener, KeyUpListener {

    private Key leftKey;
    private Key rightKey;
    private Key jumpKey;
    private boolean left;
    private boolean right;
    private boolean jump;
    private Sensor groundSensor;

    public Player() {
        leftKey = Key.LEFT;
        rightKey = Key.RIGHT;
        jumpKey = Key.SPACE;
        groundSensor = new Sensor(this, new Rectangle(-0.2, -0.7, 0.4, 0.4), "Boden");
    }

    @Override
    public void onKeyDown(KeyEvent event) {
        if (event.getKey() == leftKey) {
            left = true;
        }
        else if (event.getKey() == rightKey) {
            right = true;
        }
        else if (event.getKey() == jumpKey) {
            jump = true;
        }
    }

    @Override
    public void onKeyUp(KeyEvent event) {
        if (event.getKey() == leftKey) {
            left = false;
        }
        else if (event.getKey() == rightKey) {
            right = false;
        }
        else if (event.getKey() == jumpKey) {
            jump = false;
        }
    }

    @Override
    protected void step(double dt) {
        if (left) {
            applyForce(-2, 0);
        }
        if (right) {
            applyForce(2, 0);
        }
        if (jump && groundSensor.hasContact()) {
            applyForce(0, 15);
        }
    }
}
