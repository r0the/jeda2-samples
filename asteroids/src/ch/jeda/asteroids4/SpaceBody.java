package ch.jeda.asteroids4;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class SpaceBody extends Body {

    @Override
    protected void step(double dt) {
        final double width = getWidth();
        final double height = getHeight();
        // Space wraps around at the edges of the view
        if (getX() < -width) {
            setPosition(getView().getWidthM() + width / 2, getY());
        }

        if (getY() < -height) {
            setPosition(getX(), getView().getHeightM() + height / 2);
        }

        if (getX() >= getView().getWidthM() + width) {
            setPosition(-width / 2, getY());
        }

        if (getY() >= getView().getHeightM() + height) {
            setPosition(getX(), -height / 2);
        }
    }
}
