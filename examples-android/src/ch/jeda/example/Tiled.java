package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.geometry.*;
import ch.jeda.physics.*;
import ch.jeda.tiled.TiledMap;
import ch.jeda.ui.*;

public class Tiled extends Program {

    private PhysicsView view;

    @Override
    public void run() {
        view = new PhysicsView(700, 700, ViewFeature.USER_SCALE, ViewFeature.USER_SCROLL);
        view.add(new Box(view));
        view.setDebugging(true);
        TiledMap map = new TiledMap("res:raw/tiled.tmx");
        map.addTo(view);
        view.addEventListener(this);
    }
}
