package testing;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.cosmetic.Animation;
import de.edgelord.sjgl.cosmetic.Spritesheet;
import de.edgelord.sjgl.display.DisplayManager;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.components.DrawHitboxComponent;
import de.edgelord.sjgl.gameobject.components.DrawPositionComponent;
import de.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BirdPlayer extends GameObject {

    private Animation animation;
    private Spritesheet spritesheet;
    private DisplayManager displayManager;
    private int ticksForAnim = 0;
    private int ticksForSound = 0;

    private AffineTransform rotation = new AffineTransform();
    private int rotationDegrees = 0;

    public BirdPlayer(BufferedImage spriteSheetImage, DisplayManager displayManager, Coordinates coordinates) {
        super(coordinates, 150, 101, "bird_player");

        this.displayManager = displayManager;

        animation = new Animation(this);
        spritesheet = new Spritesheet(spriteSheetImage, getWidth(), getHeight());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        this.getComponents().add(new DrawPositionComponent(this, "drawPositionDev"));
        this.getComponents().add(new DrawHitboxComponent(this, "drawHitboxDev"));
        setFriction(0f);
    }

    @Override
    public void initialize() {

        animation.nextFrame();

        System.out.println("INFO: Initialized " + this.getClass());
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        if (ticksForSound == 250) {
            if (getCoordinates().getY() >= displayManager.getHeight()) {

                getPosition().setY(0);
            }

            if (getCoordinates().getX() >= displayManager.getWidth()) {

                getPosition().setX(-getWidth());
            }

            if (getCoordinates().getY() <= -getHeight()) {

                getPosition().setY(displayManager.getHeight() + getHeight());
            }

            if (getCoordinates().getX() <= -getWidth()) {

                getPosition().setX(displayManager.getWidth() + getWidth());
            }
        }

        if (displayManager.isInputUp()){

            moveY(-0.75f);
            if (ticksForAnim == 75){
                animation.nextFrame();

            }

            if (ticksForSound == 250){
                Tester.getAudioSystem().play("bird_flap");
            }

            rotationDegrees = -90;
        }
        if (displayManager.isInputDown()){

            moveY(0.75f);
            if (ticksForAnim == 75){
                animation.nextFrame();
            }

            if (ticksForSound == 250){
                Tester.getAudioSystem().play("bird_flap");
            }
            rotationDegrees = 90;
        }
        if (displayManager.isInputLeft()){

            moveX(-0.75f);
            if (ticksForAnim == 75){
                animation.nextFrame();
            }

            if (ticksForSound == 250){
                Tester.getAudioSystem().play("bird_flap");
            }
            rotationDegrees = 180;
        }
        if (displayManager.isInputRight()){

            moveX(0.75f);
            if (ticksForAnim == 75){
                animation.nextFrame();
            }

            if (ticksForSound == 250){
                Tester.getAudioSystem().play("bird_flap");
            }
            rotationDegrees = 0;
        }

        if (ticksForAnim == 75) {

            ticksForAnim = 0;
        } else {
            ticksForAnim++;
        }

        if (ticksForSound == 250) {

            ticksForSound = 0;
        } else {
            ticksForSound++;
        }
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
