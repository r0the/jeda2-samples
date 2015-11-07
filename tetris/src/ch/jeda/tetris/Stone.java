package ch.jeda.tetris;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.ui.*;

/**
 * Diese Klasse repräsentiert den Tetris-Stein, welcher am herunterfallen ist.
 */
public class Stone {

    /**
     * Die aktuelle x-Koordinate des Steins.
     */
    private int x;
    /**
     * Die aktuelle y-Koordinate des Steins.
     */
    private int y;
    /**
     * Die relativen x-Koordinaten der einzelnen Blöcke, aus welchen der Stein besteht.
     */
    private int[] xs;
    /**
     * Die relativen y-Koordinaten der einzelnen Blöcke, aus welchen der Stein besteht.
     */
    private int[] ys;
    /**
     * Das Bild eines einzelnen Blocks des Steins.
     */
    private Image image;

    /**
     * Erstellt einen neuen Stein. Die Art des Steins wird durch type bestimmt.
     *
     * @param x
     * @param y
     * @param type
     */
    public Stone(int x, int y, int type) {
        this.x = x;
        this.y = y;
        if (type == 0) {
            image = new Image("res:square_red.png");
            xs = new int[]{-1, 0, 0, 1};
            ys = new int[]{0, 0, 1, 1};
        }
        else if (type == 1) {
            image = new Image("res:square_green.png");
            xs = new int[]{-1, 0, 0, 1};
            ys = new int[]{1, 1, 0, 0};
        }
        else if (type == 2) {
            image = new Image("res:square_blue.png");
            xs = new int[]{-1, 0, 1, 1};
            ys = new int[]{0, 0, 0, 1};
        }
        else if (type == 3) {
            image = new Image("res:square_yellow.png");
            xs = new int[]{-1, -1, 0, 1};
            ys = new int[]{1, 0, 0, 0};
        }
        else if (type == 4) {
            image = new Image("res:square_purple.png");
            xs = new int[]{0, 0, 1, 1};
            ys = new int[]{0, 1, 0, 1};
        }
        else if (type == 5) {
            image = new Image("res:square_grey.png");
            xs = new int[]{-1, 0, 1, 2};
            ys = new int[]{0, 0, 0, 0};
        }
        else if (type == 6) {
            image = new Image("res:square_grey.png");
            xs = new int[]{-1, 0, 0, 1};
            ys = new int[]{0, -1, 0, 0};
        }
    }

    /**
     * Zeichnet den Stein.
     *
     * @param window
     */
    public void draw(final Window window) {
        int size = image.getWidth();
        int i = 0;
        while (i < xs.length) {
            window.drawImage(size * (x + xs[i]), size * (y + ys[i]), image);
            i = i + 1;
        }
    }

    /**
     * Überprüft, ob der Stein mit einem besetzten Feld des Hintergrunds überlappt
     *
     * @param ground
     * @return
     */
    public boolean touchesGround(Ground ground) {
        // Ueberpruefe, ob der Stein zuunterst ist.
        int i = 0;
        while (i < xs.length) {
            if (y + ys[i] >= ground.getHeight()) {
                return true;
            }

            if (ground.hasBlock(x + xs[i], y + ys[i])) {
                return true;
            }

            i = i + 1;
        }

        return false;
    }

    /**
     * Überprüft, ob der Stein ausserhalb des Spielfeldes liegt.
     *
     * @param ground
     * @return
     */
    public boolean isOutside(Ground ground) {
        int i = 0;
        while (i < xs.length) {
            if (!ground.isValidField(x + xs[i], y + ys[i])) {
                return true;
            }

            i = i + 1;
        }

        return false;
    }

    /**
     * Füge die Blöcke des Spielfeldes in den Hintergrund ein.
     *
     * @param ground
     */
    public void addToGround(Ground ground) {
        int i = 0;
        while (i < xs.length) {
            ground.setField(x + xs[i], y + ys[i], image);
            i = i + 1;
        }
    }

    /**
     * Verschiebt den Stein falls möglich. Verschiebt den Stein und überprüft, ob er nun ausserhalb des Spielfeldes
     * liegt oder mit einem besetzten Feld überlappt. In dem Fall wird die Verschiebung rückgängig gemacht.
     *
     * @param ground
     * @param dx Verschiebung in horizontaler Richtung
     * @param dy Verschiebung in vertikaler Richtung
     * @return
     */
    public boolean move(Ground ground, int dx, int dy) {
        x = x + dx;
        y = y + dy;
        if (isOutside(ground) || touchesGround(ground)) {
            x = x - dx;
            y = y - dy;
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Dreht den Stein falls möglich. Dreht den Stein und überprüft, ob er nun ausserhalb des Spielfeldes liegt oder mit
     * einem besetzten Feld überlappt. In dem Fall wird die Drehung rückgängig gemacht.
     *
     * @param ground
     */
    public void rotate(Ground ground) {
        // Speichere die bisherigen Arrays unter einem neuen Namen.
        int[] oldxs = xs;
        int[] oldys = ys;
        // Lege neue Arrays für xs und ys an.
        xs = new int[4];
        ys = new int[4];
        int i = 0;
        // Drehe um 90 Grad
        while (i < xs.length) {
            xs[i] = -oldys[i];
            ys[i] = oldxs[i];
            i = i + 1;
        }

        // Überprüfe, ob nun was falsch ist.
        if (isOutside(ground) || touchesGround(ground)) {
            // Mache in dem Fall die Drehung rückgängig.
            xs = oldxs;
            ys = oldys;
        }
    }
}
