package testing;

import de.me.edgelord.sjgl.camera.Camera;
import de.me.edgelord.sjgl.cosmetic.Animation;
import de.me.edgelord.sjgl.cosmetic.Spritesheet;
import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.gameobjectComponent.DrawHitboxComponent;
import de.me.edgelord.sjgl.gameobjectComponent.DrawPositionComponent;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BirdPlayer extends GameObject {

    private Animation animation;
    private Spritesheet spritesheet;
    private DisplayManager displayManager;
    private Camera camera;
    private int ticks = 0;

    private AffineTransform rotation = new AffineTransform();
    private int rotationDegrees = 0;

    public BirdPlayer(BufferedImage spriteSheetImage, DisplayManager displayManager, Camera camera, Coordinates coordinates) {
        super(coordinates, 150, 101);

        this.displayManager = displayManager;
        this.camera = camera;

        animation = new Animation(this);
        spritesheet = new Spritesheet(spriteSheetImage, getWidth(), getHeight());

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

    }

    @Override
    public void onFixedTick() {

        if (displayManager.isInputUp()){

            getVector2f().setY(getVector2f().getY() - 0.75f);
            if (ticks == 75){
                animation.nextFrame();
            }
            rotationDegrees = -90;
        }
        if (displayManager.isInputDown()){

            getVector2f().setY(getVector2f().getY() + 0.75f);
            if (ticks == 75){
                animation.nextFrame();
            }
            rotationDegrees = 90;
        }
        if (displayManager.isInputLeft()){

            getVector2f().setX(getVector2f().getX() - 0.75f);
            if (ticks == 75){
                animation.nextFrame();
            }
            rotationDegrees = 180;
        }
        if (displayManager.isInputRight()){

            getVector2f().setX(getVector2f().getX() + 0.75f);
            if (ticks == 75){
                animation.nextFrame();
            }
            rotationDegrees = 0;
        }

        if (ticks == 75) {

            ticks = 0;
            return;
        }

        ticks++;

    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        rotation.setToRotation(Math.toRadians(rotationDegrees));

        animation.drawCurrentFrame(graphics);
    }
}
