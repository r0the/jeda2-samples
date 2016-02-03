package ch.jeda.project;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Button {

    double x;
    double y;
    double r;
    String name;

    Button(double x, double y, double r, String name) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.name = name;
    }

    public boolean clicked(double a, double b) {
        double dsquared = (a - x) * (a - x) + (b - y) * (b - y);
        return dsquared <= r * r;
    }
}
