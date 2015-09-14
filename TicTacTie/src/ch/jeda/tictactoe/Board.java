package ch.jeda.tictactoe;

import ch.jeda.ui.Canvas;
import ch.jeda.ui.Color;

public class Board {

    private int width;
    private int height;
    private int[][] fields;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.fields = new int[width][height];
    }

    public void draw(final Canvas canvas) {
        int y = 0;
        while (y < height) {
            int x = 0;
            while (x < width) {
                drawField(x, y, canvas);
                x = x + 1;
            }
            y = y + 1;
        }
    }

    private void drawField(int x, int y, Canvas canvas) {
        canvas.setColor(Color.BLACK);
        canvas.drawRectangle(x, y, 1, 1);
    }
}
