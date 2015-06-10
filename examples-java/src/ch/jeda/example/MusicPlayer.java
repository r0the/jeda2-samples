package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class MusicPlayer extends Program implements ActionListener {

    private static final int ACTION_PLAY = 0;
    private static final int ACTION_PAUSE = 1;
    private static final int ACTION_STOP = 2;
    private View view;
    private Music music;

    @Override
    public void run() {
        view = new View(300, 200);
        music = new Music("res:raw/awesomeness.mp3");
        view.add(new ActionButton(40, 40, Icon.PLAY, ACTION_PLAY));
        view.add(new ActionButton(120, 40, Icon.PAUSE, ACTION_PAUSE));
        view.add(new ActionButton(200, 40, Icon.STOP, ACTION_STOP));
        view.addEventListener(this);
    }

    @Override
    public void onAction(ActionEvent event) {
        if (event.getId() == ACTION_PLAY) {
            music.play();
        }
        else if (event.getId() == ACTION_PAUSE) {
            music.pause();
        }
        else if (event.getId() == ACTION_STOP) {
            music.stop();
        }
    }
}
