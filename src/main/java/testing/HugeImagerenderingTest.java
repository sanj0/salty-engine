package testing;

import de.me.edgelord.sjgl.StaticVars.StaticSystem;
import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HugeImagerenderingTest extends GameObject {

    private BufferedImage image;

    public HugeImagerenderingTest() {
        super(new Coordinates(0, 0), 1920, 1080);
    }

    @Override
    public void initialize() {

        try {
            image = StaticSystem.getOuterResource().getImage("res/pictures/bg.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCollision(GameObject other) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }
}
