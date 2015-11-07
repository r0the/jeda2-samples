package ch.jeda.platformer;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.tiled.*;
import ch.jeda.physics.*;
import ch.jeda.ui.*;

public class Platformer extends Program {

    private PhysicsView view;

    @Override
    public void run() {
        view = new PhysicsView(ViewFeature.USER_SCALE);
        view.setDebugging(true);
        TiledMap map = new TiledMap("res:level-1.tmx");
        map.addTo(view);
    }
}
