package ch.jeda.asteroids;

import ch.jeda.ui.Alignment;
import ch.jeda.ui.Canvas;
import ch.jeda.ui.Color;
import ch.jeda.ui.Element;

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
