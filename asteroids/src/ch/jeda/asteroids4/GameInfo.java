package ch.jeda.asteroids4;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class GameInfo extends Element {

    private Ship ship;

    public GameInfo(Ship ship) {
        setDrawOrder(2);
        this.ship = ship;
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.setAlignment(Alignment.TOP_LEFT);
        canvas.setColor(Color.GREEN_900);
        canvas.fillRectangle(0, 0, 300, 20);
        canvas.setColor(Color.GREEN_500);
        double energy = ship.getEnergy();
        if (energy > 0) {
            canvas.fillRectangle(0, 0, energy * 3, 20);
        }
    }
}
