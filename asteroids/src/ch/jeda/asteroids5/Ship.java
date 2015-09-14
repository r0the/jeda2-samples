package ch.jeda.asteroids5;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Ship extends SpaceBody implements KeyDownListener, KeyUpListener {

    private static final double GUN_FIRE_DELAY = 0.2;
    private boolean leftEngine;
    private boolean rightEngine;
    private boolean mainEngine;
    private Image thrust;
    private Image steer;
    private double energy;
    private boolean shoot;
    private double gunCooldown;

    public Ship() {
        setImage(new Image("res:drawable/ship.png"), 2, 2);
        addShape(new Circle(0, 0, 1));
        thrust = new Image("res:drawable/thrust.png");
        steer = new Image("res:drawable/steer.png");
        energy = 100;
    }

    public double getEnergy() {
        return energy;
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
        else if (event.getKey() == Key.SPACE) {
            shoot = true;
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
        else if (event.getKey() == Key.SPACE) {
            shoot = false;
        }
    }

    @Override
    public void step(double dt) {
        super.step(dt);
        gunCooldown = gunCooldown - dt;
        if (mainEngine) {
            applyLocalForceDeg(20, 0);
        }

        if (leftEngine) {
            applyLocalForceDeg(10, 0, 0, -1);
        }

        if (rightEngine) {
            applyLocalForceDeg(10, 0, 0, 1);
        }

        if (shoot) {
            shoot();
        }
    }

    @Override
    protected void beginContact(Body other) {
        if (other instanceof Asteroid) {
            removeEnergy(5);
        }
    }

    private void shoot() {
        if (gunCooldown <= 0) {
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

    private void removeEnergy(double amount) {
        energy = energy - amount;
        if (energy <= 0) {
            getView().add(new Explosion(this));
            remove();
        }
    }
}
