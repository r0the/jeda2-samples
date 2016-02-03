package ch.jeda.balls;

import ch.jeda.Util;
import ch.jeda.ui.Color;

public class Ball {

    private double ax;
    private double vx;
    private double sx;
    private double ay;
    private double vy;
    private double sy;
    private Color c;
    private int r;

    public Ball(double w, double h) {
        c = new Color(Util.randomInt(256), Util.randomInt(256), Util.randomInt(256));
        sx = Util.randomInt((int) (w - 10)) - w / 2 + 5;
        sy = Util.randomInt((int) (h - 10)) - h / 2 + 5;
        r = Util.randomInt(5) + 2;

    }

    public void updateVars(double gravity_X, double gravity_Y, double w, double h) {
        if (w / 2 + sx - r <= 0 && vx < 0) {
            vx = -(Math.random() * 2 / 5 + 0.6) * vx;
            sx = -w / 2 + r;
        }
        if (w / 2 + sx + r >= w && vx > 0) {
            vx = -(Math.random() * 2 / 5 + 0.6) * vx;
            sx = w / 2 - r;
        }
        if (h / 2 + sy - r <= 0 && vy < 0) {
            vy = -(Math.random() * 2 / 5 + 0.6) * vy;
            sy = -h / 2 + r;
        }
        if (h / 2 + sy + r >= h && vy > 0) {
            vy = -(Math.random() * 2 / 5 + 0.6) * vy;
            sy = h / 2 - r;
        }

        ax = gravity_X;
        vx = vx + ax / 5;
        sx = sx + vx / 5;
        ay = gravity_Y;
        vy = vy + ay / 5;
        sy = sy + vy / 5;
    }

    public Color getColor() {
        return c;
    }

    public int getRadius() {
        return r;
    }

    public double get_sx() {
        return sx;
    }

    public double get_sy() {
        return sy;
    }
}
