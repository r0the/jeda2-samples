package ch.jeda.asteroids4;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Asteroid extends SpaceBody {

    public Asteroid(double radius) {
        setDrawOrder(-2);
        setImage(new Image("res:drawable/asteroid.png"), 2 * radius, 2 * radius);
        this.addShape(new Ellipse(0, 0, radius, 0.8 * radius));
    }
}
