package ch.jeda.asteroids5;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class GameInfo extends Element {

    private Ship ship;

    public GameInfo(Ship ship) {
        setDrawOrder(1000);
        this.ship = ship;
        setDrawOrder(2);
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.setAlignment(Alignment.TOP_LEFT);
        canvas.setColor(Color.GREEN_900);
        canvas.fillRectangle(0, 0, 300, 20);
        canvas.setColor(Color.GREEN_500);
        canvas.fillRectangle(0, 0, ship.getEnergy() * 3, 20);
    }
}
