package ch.jeda.asteroids1;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Game1 extends Program {

    private PhysicsView view;

    @Override
    public void run() {
        view = new PhysicsView();
        Canvas bg = view.getBackground();
        bg.drawImage(0, 0, bg.getWidth(), bg.getHeight(), new Image("res:drawable/space.jpg"));

        view.setGravity(0, 0);
        view.add(new Box(view));
        addAsteroids(10);
    }

    public void addAsteroids(int count) {
        while (count > 0) {
            double x = 3 + Math.random() * (view.getWidthM() - 6);
            double y = 3 + Math.random() * (view.getHeightM() - 6);
            double vx = -2 + Math.random() * 4;
            double vy = -2 + Math.random() * 4;
            double r = 1 + Math.random() * 2;
            Body asteroid = new Asteroid(r);
            asteroid.setPosition(x, y);
            asteroid.setVelocity(vx, vy);
            view.add(asteroid);
            count = count - 1;
        }
    }
}
