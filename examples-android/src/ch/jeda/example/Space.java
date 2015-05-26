package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.Circle;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Space extends Program {

    @Override
    public void run() {
        PhysicsView view = new PhysicsView();
        view.setGravity(0, 0);
        view.getBackground().setAlignment(Alignment.BOTTOM_LEFT);
        view.getBackground().drawImage(0, 0, new Image("res:drawable/space.jpg"));
        Body ship = new Ship();
        ship.setPosition(view.getWidthM() / 2, view.getHeightM() / 2);
        view.add(ship);
    }
}

class Ship extends Body implements KeyDownListener, KeyUpListener {

    private static final int RADIUS = 1;
    private boolean rotateLeft;
    private boolean rotateRight;
    private boolean thrustOn;
    private Image thrust;
    private Image steer;

    public Ship() {
        setImage(new Image("res:drawable/ship.png"), 2, 2);
        thrust = new Image("res:drawable/thrust.png");
        steer = new Image("res:drawable/steer.png");
        setType(BodyType.DYNAMIC);
        addShape(new Circle(0, 0, RADIUS));
    }

    @Override
    protected void drawDecoration(Canvas canvas) {
        canvas.setAlignment(Alignment.CENTER);
        if (thrustOn) {
            canvas.drawImage(-1.5, 0, thrust);
        }

        if (rotateLeft) {
            canvas.drawImage(-0.3, -1, steer);
        }

        if (rotateRight) {
            canvas.drawImage(-0.3, 1, steer);
        }
    }

    @Override
    public void onKeyDown(KeyEvent event) {
        switch (event.getKey()) {
            case UP:
                thrustOn = true;
                break;
            case LEFT:
                rotateLeft = true;
                break;
            case RIGHT:
                rotateRight = true;
                break;
        }
    }

    @Override
    public void onKeyUp(KeyEvent event) {
        switch (event.getKey()) {
            case UP:
                thrustOn = false;
                break;
            case LEFT:
                rotateLeft = false;
                break;
            case RIGHT:
                rotateRight = false;
                break;
        }
    }

    @Override
    public void step(double dt) {
        if (thrustOn) {
            applyForce(20);
        }

        if (rotateLeft) {
            applyTorque(2);
        }

        if (rotateRight) {
            applyTorque(-2);
        }

        // Space wraps around at the edges of the view
        if (getX() < -RADIUS) {
            setPosition(getView().getWidthM() + RADIUS - 1, getY());
        }

        if (getY() < -RADIUS) {
            setPosition(getX(), getView().getHeightM() + RADIUS - 1);
        }

        if (getX() >= getView().getWidthM() + RADIUS) {
            setPosition(-RADIUS, getY());
        }

        if (getY() >= getView().getHeightM() + RADIUS) {
            setPosition(getX(), -RADIUS);
        }
    }
}
