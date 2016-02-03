package ch.jeda.bobbles;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Bobbles extends Program implements PointerMovedListener, TickListener {

    private View view;
    private Canvas bg;
    private float w;
    private float h;
    private State state;
    private Bobble b[];
    private int score;
    private boolean restartable;
    private int restart;

    @Override

    public void run() {
        view = new View();
        bg = view.getBackground();
        state = new State(0);

        w = bg.getWidth();
        h = bg.getHeight();
        b = new Bobble[9];

        for (int i = 0; i < b.length; i++) {
            b[i] = new Bobble(i, w, h);
        }

        bg.setAlignment(Alignment.CENTER);
        bg.drawText(w / 2, h - 50, "Oli-Games presents");
        bg.setTextSize(50);
        bg.drawText(w / 2, h / 2, "Bobbles");
        bg.setTextSize(16);
        bg.drawText(w / 2, 50, "Touch anywhere to start!");

        restartable = false;

        view.addEventListener(this);
    }

    public void onPointerMoved(PointerEvent pe) {
        if (state.isStarting()) {
            state.run();
            score = 0;
        }
        else if (state.isRunning()) {
            if (pe.getViewX() > b[0].getX() - 30 &&
                pe.getViewX() < b[0].getX() + 30 &&
                pe.getViewY() > b[0].getY() - 30 &&
                pe.getViewY() < b[0].getY() + 30) {
                for (int i = 0; i < b.length; i++) {
                    b[i].newPos(w, h);

                }

                score = (int) (score + b[0].getSpeed());
            }

        }
        else if (state.hasEnded()) {
            if (restartable) {
                score = 0;
                for (int i = 0; i < b.length; i++) {
                    b[i].restart(w, h);
                }

                state.run();
                restartable = false;
            }
        }
    }

    public void onTick(TickEvent te) {
        if (state.isRunning()) {
            bg.setColor(Color.WHITE);
            bg.fill();
            for (int i = 0; i < b.length; i++) {
                bg.setColor(b[i].getColor());
                bg.fillCircle(b[i].getX(), b[i].getY(), 30);
                bg.setColor(b[i].getLineColor());
                bg.drawCircle(b[i].getX(), b[i].getY(), 30);
                b[i].move();
            }

            if (b[0].getX() > w || b[0].getX() < 0 || b[0].getY() > h || b[0].getY() < 0) {
                state.end();
                restart = 0;
            }

            bg.setColor(Color.BLACK);
            bg.drawText(w / 2, 30, score + " Points");

        }
        else if (state.hasEnded()) {
            bg.setColor(Color.WHITE);
            bg.fill();
            bg.setColor(Color.BLACK);
            bg.drawText(w / 2, h - 50, "Your Score:");
            bg.setTextSize(50);
            bg.drawText(w / 2, h / 2, "" + score);
            bg.setTextSize(16);

            if (restart < 100) {
                restart++;
            }
            else {
                restartable = true;
                bg.drawText(w / 2, 50, "Touch anywhere to restart start!");
            }
        }
    }
}
