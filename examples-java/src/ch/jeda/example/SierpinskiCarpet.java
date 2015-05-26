package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class SierpinskiCarpet extends Program {

    private Canvas canvas;

    @Override
    public void run() {
        View view = new View();
        canvas = view.getBackground();
        double w = view.getWidthDp();
        double h = view.getHeightDp();

        double s = Math.min(h, w);

        double x = (w - s) / 2;
        double y = (h - s) / 2;
        canvas.setColor(Color.LIGHT_GREEN_50);
        canvas.fill();
        canvas.setColor(Color.LIGHT_GREEN_900);
        drawSquare(x, y, s);
        canvas.setColor(Color.LIGHT_GREEN_50);
        drawCarpet(x + s / 3, y + s / 3, s / 3);
    }

    void drawCarpet(double x, double y, double s) {
        this.drawSquare(x, y, s);

        x = x - s * 2 / 3;
        y = y - s * 2 / 3;

        if (s > 1) {
            drawCarpet(x, y, s / 3);
            drawCarpet(x + s, y, s / 3);
            drawCarpet(x + 2 * s, y, s / 3);
            drawCarpet(x, y + s, s / 3);
            drawCarpet(x + 2 * s, y + s, s / 3);
            drawCarpet(x, y + 2 * s, s / 3);
            drawCarpet(x + s, y + 2 * s, s / 3);
            drawCarpet(x + 2 * s, y + 2 * s, s / 3);
        }
    }

    void drawSquare(double x, double y, double s) {
        canvas.fillRectangle(x, y, s, s);
    }
}
