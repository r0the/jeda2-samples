package ch.jeda.asteroids5;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Shot extends SpaceBody {

    private double age;

    public Shot() {
        setType(BodyType.DYNAMIC);
        setRotationFixed(true);
        Image image = new Image("res:drawable/laser.png");
        setImage(image, 1, 0.2);
        setDensity(0.01);
        addShape(new Rectangle(-0.5, -0.1, 1, 0.2));
        age = 0;
    }

    @Override
    protected void beginContact(Body other) {
        if (other instanceof Asteroid) {
            getView().remove(this);
        }
    }

    @Override
    protected void step(double dt) {
        super.step(dt);
        age = age + dt;
        if (age > 2.0) {
            remove();
        }
    }
}
