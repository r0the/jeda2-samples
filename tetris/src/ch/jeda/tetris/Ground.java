package ch.jeda.tetris;

import ch.jeda.ui.Image;
import ch.jeda.ui.Window;

/**
 * Diese Klasse repräsentiert das Spielfeld. Sie stellt das Spielfeld als zweidimensionales Array von Bildern dar. Jedes
 * Feld des Spielfeldes entspricht einem Element des Arrays. Ein null-Wert bedeutet, dass das Feld frei ist.
 */
public class Ground {

    private int blockSize = 32;
    static final int WIDTH = 10;
    static final int HEIGHT = 18;
    private Image[][] fields;

    /**
     * Erstellt ein neues, leeres Spielfeld.
     */
    public Ground() {
        fields = new Image[WIDTH][HEIGHT];
    }

    /**
     * Liefert die Höhe des Spielfeldes in Pixel zurück.
     *
     * @return die Höhe des Spielfeldes in Pixel
     */
    public int getHeightPx() {
        return HEIGHT * blockSize;
    }

    /**
     * Liefert die Breite des Spielfeldes in Pixel zurück.
     *
     * @return die Breite des Spielfeldes in Pixel
     */
    public int getWidthPx() {
        return WIDTH * blockSize;
    }

    /**
     * Überprüft, ob ein bestimmtes Feld besetzt ist.
     *
     * @param x x-Koordinate des Feldes
     * @param y y-Koordinate des Feldes
     * @return
     */
    public boolean hasBlock(int x, int y) {
        return fields[x][y] != null;
    }

    /**
     * Überprüft, ob die angegebenen Koordinaten eine gültige Position für ein Feld sind.
     *
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @return
     */
    public boolean isValidField(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    /**
     * Setzt oder Löscht das Bild in einem bestimmten Feld.
     *
     * @param x x-Koordinate des Feldes
     * @param y y-Koordinate des Feldes
     * @param image Das Bild oder null
     */
    public void setField(int x, int y, Image image) {
        fields[x][y] = image;
    }

    /**
     * Liefert die Höhe des Spielfeldes in Feldern zurück.
     *
     * @return
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Zeichnet das Spielfeld
     *
     * @param window
     */
    public void draw(Window window) {
        int x = 0;
        while (x < WIDTH) {
            int y = 0;
            while (y < HEIGHT) {
                window.drawImage(x * blockSize, y * blockSize, fields[x][y]);
                y = y + 1;
            }

            x = x + 1;
        }
    }

    /**
     * Überprüft das Spielfeld auf volle Linien und löscht diese.
     */
    public int checkFullLines() {
        int y = 0;
        int score = 0;
        while (y < HEIGHT) {
            if (isLineFull(y)) {
                removeLine(y);
                score = score + 10;
            }

            y = y + 1;
        }

        return score;
    }

    /**
     * Entfernt eine Linie aus dem Spielfeld. Sämtliche Linien oberhalb werden nach unten geschoben.
     *
     * @param y die zu löschende Linie
     */
    private void removeLine(int y) {
        // Schiebe alle Linien oberhalb um eins nach unten
        while (y > 0) {
            copyLine(y - 1, y);
            y = y - 1;
        }

        // Lösche die oberste Linie
        int x = 0;
        while (x < WIDTH) {
            fields[x][y] = null;
            x = x + 1;
        }
    }

    /**
     * Kopiert eine Linie.
     *
     * @param yFrom
     * @param yTo
     */
    private void copyLine(int yFrom, int yTo) {
        int x = 0;
        while (x < WIDTH) {
            fields[x][yTo] = fields[x][yFrom];
            x = x + 1;
        }
    }

    /**
     * Überprüft, ob eine bestimmte Linie voll ist.
     *
     * @param y
     * @return
     */
    private boolean isLineFull(int y) {
        int x = 0;
        while (x < WIDTH) {
            if (fields[x][y] == null) {
                return false;
            }

            x = x + 1;
        }

        return true;
    }
}
