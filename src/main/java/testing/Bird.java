package testing;

import de.me.edgelord.sjgl.cosmetic.Animation;
import de.me.edgelord.sjgl.cosmetic.Spritesheet;
import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.gameobjectComponent.DrawHitboxComponent;
import de.me.edgelord.sjgl.gameobjectComponent.DrawPositionComponent;
import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends GameObject {

    private Animation animation;
    private Spritesheet spritesheet;

    private int yPos;
    private int xPos;
    private final int windowWidth = 1200;
    private final int windowHeight = 909;
    private final int moveSpeed = 1;
    private int fixedTicks = 0;
    private int ticks = 0;

    public Bird(BufferedImage image, int xPos, int yPos) {
        super(new Coordinates(xPos * 150, yPos * 101), 150, 101);

        this.yPos = yPos;
        this.xPos = xPos;

        animation = new Animation(this);
        spritesheet = new Spritesheet(image, getWidth(), getHeight());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        this.getComponents().add(new DrawPositionComponent(this, "drawPositionDev"));
        this.getComponents().add(new DrawHitboxComponent(this, "drawHitboxDev"));
    }

    @Override
    public void initialize() {

        animation.nextFrame();

        System.out.println("INFO: Initialized " + this.getClass());
    }

    @Override
    public void onCollision(GameObject other) {

        System.out.println("A bird collided!");
    }

    @Override
    public void onFixedTick() {

        if (fixedTicks == 15) {

            getPhysics().addUnstackingForce(0.75f, Directions.BasicDirection.x);

            if (getCoordinates().getY() >= windowHeight) {

                getVector2f().setY(0);
            }

            if (getCoordinates().getX() >= windowWidth) {

                getVector2f().setX(-getWidth());
                // getVector2f().setY(getY() + getHeight());
            }

            fixedTicks = 0;
            return;
        }

        if (ticks == 75) {

            animation.nextFrame();

            ticks = 0;
            return;
        }

        ticks++;

        fixedTicks++;
    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        animation.drawCurrentFrame(graphics);
    }
}
