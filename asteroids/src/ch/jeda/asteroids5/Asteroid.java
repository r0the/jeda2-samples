package ch.jeda.asteroids5;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Asteroid extends SpaceBody {

    private double stability;

    public Asteroid(double radius) {
        setImage(new Image("res:drawable/asteroid.png"), 2 * radius, 2 * radius);
        this.addShape(new Ellipse(0, 0, radius, 0.8 * radius));
        stability = Math.PI * radius * radius;
    }

    @Override
    protected void beginContact(Body other) {
        if (other instanceof Shot) {
            hit(0.5);
        }
    }

    public void hit(double amount) {
        stability = stability - amount;
        if (stability <= 0) {
            remove();
        }
    }
}
