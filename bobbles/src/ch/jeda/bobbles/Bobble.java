package ch.jeda.bobbles;

import ch.jeda.Util;
import ch.jeda.ui.Color;

public class Bobble {

    private float x;
    private float y;
    private boolean single;
    private Color color;
    private Color linecolor;
    private double direction;
    private float speed;

    public Bobble(int index, float w, float h) {
        this.x = Util.randomInt((int) (w - 60)) + 30;
        this.y = Util.randomInt((int) (h - 60)) + 30;
        this.single = index == 0;
        if (this.single) {
            this.color = Color.GREEN_500;
            this.linecolor = Color.GREEN_800;
            speed = 1;
        } else {
            this.color = Color.BLUE_500;
            this.linecolor = Color.BLUE_800;
            speed = (float) Math.random();
        }

        this.direction = Math.random() * 2 * Math.PI;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isSingle() {
        return single;
    }

    public Color getColor() {
        return color;
    }

    public Color getLineColor() {
        return linecolor;
    }

    public float getSpeed() {
        return speed;
    }

    public void newPos(float w, float h) {
        this.x = Util.randomInt((int) (w - 60)) + 30;
        this.y = Util.randomInt((int) (h - 60)) + 30;
        this.direction = Math.random() * 2 * Math.PI;
        this.speed = (float) (this.speed * 1.1);

    }

    public void move() {
        this.x = (float) (this.x + Math.cos(this.direction) * this.speed);
        this.y = (float) (this.y + Math.sin(this.direction) * this.speed);

    }

    public void resetSpeed() {
        if (this.single) {
            speed = 1;
        } else {
            speed = (float) Math.random();
        }
    }

    public void restart(float w, float h) {
        this.newPos(w, h);
        this.resetSpeed();
    }
}
