package ch.jeda.asteroids;

import ch.jeda.geometry.Rectangle;
import ch.jeda.physics.Body;
import ch.jeda.physics.BodyType;
import ch.jeda.ui.Image;

public class Shot extends SpaceBody {

    private double age;

    public Shot() {
        setType(BodyType.DYNAMIC);
        setRotationFixed(true);
        Image image = new Image("res:drawable/laser.png");
        setImage(image, 1, 0.1);
        setDensity(0.01);
        addShape(new Rectangle(-0.5, -0.05, 1, 0.1));
        setDrawOrder(100);
        age = 0;
    }

    @Override
    protected void beginContact(Body other) {
        //getView().remove(this);
    }

    @Override
    protected void step(double dt) {
        super.step(dt);
        age = age + dt;
        if (age > 2.0) {
            getView().remove(this);
        }
    }
}
