package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class HelloWorld extends Program {

    @Override
    public void run() {
        View view = new View();
        Canvas canvas = view.getBackground();
        canvas.drawCircle(300, 200, 100);
//        canvas.drawText(1, 1, "Hello World");
    }
}
