package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Snow extends Program implements TickListener {

    static final int FLAKE_COUNT = 400;
    Canvas canvas;
    Image bild;
    double[] x;
    double[] y;
    double[] r;

    @Override
    public void run() {
        View view = new View(ViewFeature.ORIENTATION_LANDSCAPE);
        canvas = view.getBackground();
        // load image
        bild = new Image("res:drawable/background.jpg");
        //bild = bild.scale(view.getWidth(), view.getHeight());
        // initialize arrays
        x = new double[FLAKE_COUNT];
        y = new double[FLAKE_COUNT];
        r = new double[FLAKE_COUNT];
        // set coordinates of the snowflakes
        int i = 0;
        while (i < FLAKE_COUNT) {
            x[i] = Math.random() * canvas.getWidth();
            y[i] = Math.random() * canvas.getHeight();
            r[i] = 1 + Math.random() * 7;
            ++i;
        }

        // Dies muss die letzte Anweisung in run() sein.
        view.addEventListener(this);
    }

    @Override
    public void onTick(TickEvent event) {
        // draw background
        canvas.drawImage(0, 0, bild);
        // draw snowflakes
        canvas.setColor(Color.WHITE);
        int i = 0;
        while (i < FLAKE_COUNT) {
            canvas.fillCircle(x[i], y[i], r[i]);

            // animate snowflakes
            y[i] = y[i] - r[i];
            // set snowflakes that reach the bottom back to the top
            if (y[i] < 0) {
                x[i] = Math.random() * canvas.getWidth();
                y[i] = canvas.getHeight();
            }

            ++i;
        }
    }
}
