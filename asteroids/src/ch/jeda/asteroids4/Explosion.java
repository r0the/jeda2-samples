package ch.jeda.asteroids4;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Explosion extends Body {

    private Image image;
    private double radius;
    private double maxRadius;
    private double growthPerSecond;

    public Explosion(Body body) {
        setPosition(body.getX(), body.getY());
        setVelocity(body.getVx(), body.getVy());
        radius = 0.1;
        maxRadius = 10;
        growthPerSecond = 30;
        image = new Image("res:drawable/explosion00.png");
        setImage(image, radius, radius);
    }

    @Override
    protected void step(double dt) {
        super.step(dt);
        radius = radius + growthPerSecond * dt;
        setImage(image, radius, radius);
        if (radius > maxRadius) {
            remove();
        }
    }
}
