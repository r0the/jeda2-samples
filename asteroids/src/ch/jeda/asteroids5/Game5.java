package ch.jeda.asteroids5;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Game5 extends Program {

    private PhysicsView view;
    private MainMenu mainMenu;
    private Ship ship;
    private GameInfo gameInfo;

    @Override
    public void run() {
        view = new PhysicsView();
        Canvas bg = view.getBackground();
        bg.drawImage(0, 0, bg.getWidth(), bg.getHeight(), new Image("res:drawable/space.jpg"));

        view.setGravity(0, 0);
        addAsteroids(10);
        mainMenu = new MainMenu(this);
        ship = new Ship(this);
        gameInfo = new GameInfo(ship);
        gameInfo.setPosition(10, view.getHeightDp() - 10);
        view.add(gameInfo, mainMenu);
    }

    public void addAsteroids(int count) {
        while (count > 0) {
            double x = 3 + Math.random() * (view.getWidthM() - 6);
            double y = 3 + Math.random() * (view.getHeightM() - 6);
            double vx = -2 + Math.random() * 4;
            double vy = -2 + Math.random() * 4;
            double r = 0.5 + Math.random();
            Body asteroid = new Asteroid(r);
            asteroid.setPosition(x, y);
            asteroid.setVelocity(vx, vy);
            view.add(asteroid);
            count = count - 1;
        }
    }

    public void gameOver() {
        view.remove(ship);
        view.add(mainMenu);
        mainMenu.setGameOver();
    }

    public void startGame() {
        for (Asteroid asteroid : view.getElements(Asteroid.class)) {
            asteroid.remove();
        }

        view.remove(mainMenu);
        ship.setPosition(view.getCenterX(), view.getCenterY());
        ship.init();
        view.add(ship);
        addAsteroids(10);
    }
}
