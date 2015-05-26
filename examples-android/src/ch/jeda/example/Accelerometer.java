package ch.jeda.example;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Accelerometer extends Program implements SensorListener {

    private View view;
    private Canvas background;
    private double cx;
    private double cy;

    @Override
    public void run() {
        view = new View();
        background = view.getBackground();
        background.setColor(Color.LIGHT_GREEN_200);
        background.setAlignment(Alignment.CENTER);
        background.setColor(Color.LIGHT_GREEN_900);
        cx = view.getWidthDp() / 2;
        cy = view.getHeightDp() / 2;
        if (Jeda.isSensorAvailable(SensorType.ACCELERATION)) {
            Jeda.enableSensor(SensorType.ACCELERATION);
        }
        else {
            background.drawText(cx, cy, "Kein Accelerometer vorhanden.");
        }

        view.addEventListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        background.setColor(Color.LIGHT_GREEN_200);
        background.fill();
        background.setColor(Color.LIGHT_GREEN_900);
        background.drawPolyline(cx, cy, cx + event.getX(), cy + event.getY());
    }
}
