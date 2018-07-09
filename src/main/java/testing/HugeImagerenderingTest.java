package testing;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HugeImagerenderingTest extends GameObject {

    private BufferedImage image;

    public HugeImagerenderingTest(BufferedImage image, int width, int height) {
        super(new Coordinates(0, 0), width, height);

        this.image = image;
    }

    @Override
    public void initialize() {
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
