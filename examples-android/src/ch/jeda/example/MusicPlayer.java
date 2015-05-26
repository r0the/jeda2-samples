package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class MusicPlayer extends Program implements ActionListener {

    private View view;
    private Music music;

    @Override
    public void run() {
        view = new View(300, 200);
        music = new Music("res:raw/awesomeness.mp3");
        view.add(new ActionButton(40, 40, Icon.PLAY));
        view.add(new ActionButton(120, 40, Icon.PAUSE));
        view.add(new ActionButton(200, 40, Icon.STOP));
        view.addEventListener(this);
    }

    @Override
    public void onAction(ActionEvent event) {
        if ("PLAY".equals(event.getName())) {
            music.play();
        }
        else if ("PAUSE".equals(event.getName())) {
            music.pause();
        }
        else if ("STOP".equals(event.getName())) {
            music.stop();
        }
    }
}
