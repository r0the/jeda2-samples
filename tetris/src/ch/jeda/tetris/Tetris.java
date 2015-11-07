package ch.jeda.tetris;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Tetris extends Program implements TickListener, KeyDownListener, KeyUpListener {

    private Window window;
    private Stone stone;
    private Ground ground;
    private int dropTimeout;
    private boolean drop;
    private int score;

    @Override
    public void run() {
        window = new Window();
        stone = new Stone(1, 0, Util.randomInt(7));
        ground = new Ground();
        window.addEventListener(this);
        dropTimeout = 0;
        score = 0;
//        new Music("res:soundtrack.mp3").play();
    }

    @Override
    public void onTick(TickEvent event) {
        window.setColor(Color.BLACK);
        window.fill();
        window.setColor(Color.WHITE);
        window.fillRectangle(0, 0, ground.getWidthPx(), ground.getHeightPx());
        score = score + ground.checkFullLines();
        stone.draw(window);
        ground.draw(window);
        dropTimeout = dropTimeout + 1;
        if (dropTimeout >= 10 || drop) {
            boolean success = stone.move(ground, 0, 1);
            if (!success) {
                stone.addToGround(ground);
                stone = new Stone(5, 0, Util.randomInt(7));
            }

            dropTimeout = 0;
        }

        window.setFontSize(50);
        window.drawText(ground.getWidthPx() + 10, 10, "" + score);
    }

    @Override
    public void onKeyDown(KeyEvent event) {
        if (event.getKey() == Key.LEFT) {
            stone.move(ground, -1, 0);
        }
        else if (event.getKey() == Key.RIGHT) {
            stone.move(ground, 1, 0);
        }
        else if (event.getKey() == Key.UP) {
            stone.rotate(ground);
        }
        else if (event.getKey() == Key.DOWN) {
            drop = true;
        }
    }

    @Override
    public void onKeyUp(KeyEvent event) {
        if (event.getKey() == Key.DOWN) {
            drop = false;
        }
    }
}
