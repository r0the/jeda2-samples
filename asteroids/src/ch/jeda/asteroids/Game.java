package ch.jeda.asteroids;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Game extends Program {

    private PhysicsView view;

    @Override
    public void run() {
        view = new PhysicsView(1024, 768);
        view.setGravity(0, 0);
        Canvas bg = view.getBackground();
        bg.drawImage(0, 0, bg.getWidth(), bg.getHeight(), new Image("res:drawable/space.jpg"));

        view.addEventListener(this);
        view.setDebugging(true);
        Ship ship = new Ship();
        ship.setPosition(view.getWidthM() / 2, view.getHeightM() / 2);
        GameInfo gameInfo = new GameInfo(ship);
        gameInfo.setPosition(10, view.getHeightDp() - 10);
        view.add(ship, gameInfo);
        addAsteroids(10);
    }

    private void addAsteroids(int count) {
        while (count > 0) {
            double x = Math.random() * view.getWidthM();
            double y = Math.random() * view.getHeightM();
            double vx = -2.5 + Math.random() * 5;
            double vy = -2.5 + Math.random() * 5;
            double r = 1 + Math.random() * 2;
            Body asteroid = new Asteroid(r);
            asteroid.setPosition(x, y);
            asteroid.setVelocity(vx, vy);
            view.add(asteroid);
            count = count - 1;
        }
    }
}
