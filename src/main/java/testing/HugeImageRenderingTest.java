package testing;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HugeImageRenderingTest extends GameObject {

    private BufferedImage image;

    public HugeImageRenderingTest(BufferedImage image, int width, int height) {
        super(new Coordinates(0, 0), width, height);

        setFriction(200f);

        this.image = image;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCollision(GameObject other) {

        System.out.println("Something collided with the biogasplant");
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void onTick() {
    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(image, getCoordinates().getX(), getCoordinates().getY(), getWidth(), getHeight(), null);
    }
}
