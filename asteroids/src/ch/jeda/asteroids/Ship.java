package ch.jeda.asteroids;

import ch.jeda.event.KeyDownListener;
import ch.jeda.event.KeyEvent;
import ch.jeda.event.KeyUpListener;
import ch.jeda.geometry.Circle;
import ch.jeda.physics.Body;
import ch.jeda.physics.BodyType;
import ch.jeda.ui.Alignment;
import ch.jeda.ui.Canvas;
import ch.jeda.ui.Image;

public class Ship extends SpaceBody implements KeyDownListener, KeyUpListener {

    private static final double GUN_FIRE_DELAY = 0.2;
    private boolean shoot;
    private boolean rotateLeft;
    private boolean rotateRight;
    private boolean thrustOn;
    private double gunCooldown;
    private Image thrust;
    private Image steer;
    private double energy;

    public Ship() {
        setImage(new Image("res:drawable/ship.png"), 2, 2);
        thrust = new Image("res:drawable/thrust.png");
        steer = new Image("res:drawable/steer.png");
        setType(BodyType.DYNAMIC);
        addShape(new Circle(0, 0, 1));
        energy = 100;
    }

    public double getEnergy() {
        return energy;
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
            case SPACE:
                shoot = true;
                break;
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
            case SPACE:
                shoot = false;
                break;
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
        super.step(dt);
        if (thrustOn) {
            applyLocalForceDeg(20, 0);
        }

        if (rotateLeft) {
            applyLocalForceDeg(10, 0, 0, -1);
        }

        if (rotateRight) {
            applyLocalForceDeg(10, 0, 0, 1);
        }
        if (gunCooldown > 0) {
            gunCooldown = gunCooldown - dt;
        }

        if (gunCooldown <= 0 && shoot) {
            double x = getX() + Math.cos(getAngleRad()) * 1.5;
            double y = getY() + Math.sin(getAngleRad()) * 1.5;
            Shot bullet = new Shot();
            bullet.setPosition(x, y);
            bullet.setVelocity(5 * Math.cos(getAngleRad()), 5 * Math.sin(getAngleRad()));
            bullet.setAngleRad(getAngleRad());
            getView().add(bullet);
            gunCooldown = GUN_FIRE_DELAY;
        }
    }

    @Override
    protected void beginContact(Body other) {
        energy = energy - 5;
    }
}
