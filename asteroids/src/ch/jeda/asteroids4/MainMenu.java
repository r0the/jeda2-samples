package ch.jeda.asteroids4;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class MainMenu extends Element implements KeyDownListener {

    private Game4 game;
    private boolean gameOver;

    public MainMenu(Game4 game) {
        setDrawOrder(3);
        this.game = game;
    }

    public void setGameOver() {
        gameOver = true;
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.setColor(Color.WHITE);
        canvas.setTextSize(100);
        canvas.setAlignment(Alignment.CENTER);
        double x = canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2;
        if (gameOver) {
            canvas.drawText(x, y, "GAME OVER");
        }
        canvas.setTextSize(30);
        canvas.drawText(x, y - 100, "Press 'N' to start new game.");
    }

    @Override
    public void onKeyDown(KeyEvent event) {
        if (event.getKey() == Key.N) {
            game.startGame();
        }
    }
}
