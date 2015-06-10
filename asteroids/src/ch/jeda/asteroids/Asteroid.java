package ch.jeda.asteroids;

import ch.jeda.geometry.Ellipse;
import ch.jeda.physics.Body;
import ch.jeda.ui.Image;

public class Asteroid extends SpaceBody {

    private double radius;
    private double stability;

    public Asteroid(double radius) {
        this.radius = radius;
        this.stability = Math.PI * radius * radius;
        setImage(new Image("res:drawable/asteroid.png"), 2 * radius, 2 * radius);
        this.addShape(new Ellipse(0, 0, radius, 0.8 * radius).toPolygon(8));
    }

    public void hit() {
        stability = stability - 5;
        if (stability <= 0) {
            breakUp();
        }
    }

    private void breakUp() {
//        BREAK_SOUND.play();
//        game.addScore(size + 1);
//        if (size > 0) {
//            double vx = getVx() + Math.random() * 10;
//            double vy = getVy() + Math.random() * 10;
//            getView().add(new Asteroid(game, getX(), getY(), speed, dir + Math.PI / 4, size - 1));
//            getView().add(new Asteroid(game, getX(), getY(), speed, dir - Math.PI / 4, size - 1));
//        }
//
//        getWindow().remove(this);
    }
}
