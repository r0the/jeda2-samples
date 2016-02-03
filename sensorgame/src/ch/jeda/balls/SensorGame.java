package ch.jeda.balls;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class SensorGame extends Program implements TickListener, SensorListener, PointerDownListener, PointerUpListener {

    private View view;
    private Canvas bg;
    private double w;
    private double h;
    private double gravity_X;
    private double gravity_Y;

    private int n = 64;
    private int n_Max = 128;
    private Ball ball[] = new Ball[n_Max];

    private int yPointer;

    @Override
    public void run() {
        view = new View();
        bg = view.getBackground();
        w = bg.getWidth();
        h = bg.getHeight();
        Jeda.enableSensor(SensorType.GRAVITY);

        for (int i = 0; i < n_Max; i++) {
            ball[i] = new Ball(w, h);
        }

        view.addEventListener(this);
    }

    public void onTick(TickEvent te) {
        bg.setColor(Color.WHITE);
        bg.fill();

        for (int i = 0; i < n; i++) {
            ball[i].updateVars(gravity_X, gravity_Y, w, h);
            bg.setColor(ball[i].getColor());
            bg.fillCircle(w / 2 + ball[i].get_sx(), h / 2 + ball[i].get_sy(), ball[i].getRadius());
        }

    }

    public void onSensorChanged(SensorEvent se) {
        if (se.getSensorType() == SensorType.GRAVITY) {
            gravity_X = se.getX();
            gravity_Y = se.getY();
        }
    }

    public void onPointerDown(PointerEvent pe) {
        yPointer = (int) pe.getViewY();
    }

    public void onPointerUp(PointerEvent pe) {
        n = (int) (n + (pe.getViewY() - yPointer) / 20);
        if (n > 128) {
            n = 128;
        }
    }
}
