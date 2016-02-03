package ch.jeda.project;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

public class Executer extends Program implements TickListener, PointerMovedListener {

    //Zeichne die Spur von Parametrisierbaren Kurven und Flächen auf Android Gerät
    View view;
    Canvas lol;

    Color colortorus;
    Color colorhelix;
    Color colorcurve;

    Button Torus;
    boolean torus;

    Button Helix;
    boolean hellix;

    Button Curve;
    boolean curve;

    double w;
    double h;

    double x;
    double y;
    double z;

    double xp;
    double yp;

    double t;
    double u;
    double v;

    double r[] = new double[10];
    double a;
    double b;
    double c;

    int helix = 0;
    int tk = 1;
    int tg = 2;
    int kurve = 3;

    @Override
    public void run() {
        view = new View();
        lol = view.getBackground();
        lol.setColor(Color.BLACK);
        lol.fill();

        w = lol.getWidth();
        h = lol.getHeight();

        Torus = new Button(w / 4, h - h / 10, 30, "Torus");
        Helix = new Button(w / 2, h - h / 10, 30, "Helix");
        Curve = new Button(3 * w / 4, h - h / 10, 30, "Curve");

        colortorus = new Color(255, 0, 0);
        colorhelix = new Color(0, 0, 255);
        colorcurve = new Color(0, 255, 0);

        t = 0;
        r[helix] = 100;
        r[tk] = 20;
        r[tg] = 100;
        r[kurve] = 50;

        a = w / 2;
        b = h / 2;
        c = r[helix] / 10;
        y = 0;

        Jeda.enableSensor(SensorType.GRAVITY);
        Jeda.setTickFrequency(120);

        view.addEventListener(this);
    }

    public void onPointerMoved(PointerEvent pe) {  //Prüfe welcher Knopf gedrückt wird. setzte alle Parameter auf null
        double xs = pe.getViewX();
        double ys = pe.getViewY();
        torus = Torus.clicked(xs, ys);
        hellix = Helix.clicked(xs, ys);
        curve = Curve.clicked(xs, ys);
        u = 0;
        v = 0;
        t = 0;

        lol.setColor(Color.BLACK);
        lol.fill();
    }

    public void onTick(TickEvent te) { //zeichne jene spur, deren knopf der letzgedrückte ist
        this.drawButton(Torus);
        this.drawButton(Helix);
        this.drawButton(Curve);

        if (torus) {
            int i = 0;
            while (i < 6) {
                this.drawTorus();
                ++i;
            }
        }
        if (hellix) {
            int i = 0;
            while (i < 6) {
                this.drawHelix();
                ++i;
            }
        }
        if (curve) {
            int i = 0;
            while (i < 6) {
                this.drawCurve();
                ++i;
            }
        }

    }

    public void drawButton(Button b) { //zeichne einen knopf
        lol.setColor(Color.WHITE);
        lol.fillCircle(b.x, b.y, b.r);
        lol.drawText(b.x - b.r, b.y - 2 * b.r, b.name);
    }

    public void drawTorus() { //parametrisierung des donuts

        x = Math.cos(0.1 * u) * (r[tk] * Math.cos(v) + r[tg]) + a;
        y = r[tk] * Math.sin(v) + b;
        z = Math.sin(0.1 * u) * (r[tk] * Math.cos(v) + r[tg]);

        xp = x + z / 6;
        yp = y - z / 6;

        colortorus = new Color(255, 0, 0);

        if (torus) {
            lol.setColor(colortorus);
            lol.fillCircle(xp, yp, 1);
        }
        v = v + 0.1;
        if (v > 6.28) {
            v = 0;
            u = u + 0.5;
        }

    }

    public void drawHelix() { //parametrisierung der helix
        x = r[helix] * Math.cos(t) + a;
        y = c * t + h / 10;
        z = r[helix] * Math.sin(t);

        xp = x + z / 6;
        yp = y - z / 6;

        colorhelix = new Color(0, 0, 255);
        lol.setColor(colorhelix);
        lol.fillCircle(xp, yp, 1);

        t = t + 0.01;
    }

    public void drawCurve() {
        x = r[kurve] * Math.cos(0.5 * t) + Math.sin(0.1 * t) + a;
        y = r[kurve] * Math.cos(0.3 * t) + b;
        z = 10 * r[kurve] * Math.sin(0.1 * t) + Math.cos(0.3 * t);

        xp = x + z / 6;
        yp = y - z / 6;

        colorcurve = new Color(0, 255, 0);
        lol.setColor(colorcurve);
        lol.fillCircle(xp, yp, 1);

        t = t + 0.01;
    }
}
