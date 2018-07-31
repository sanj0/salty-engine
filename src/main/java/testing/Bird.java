package testing;

import de.edgelord.sjgl.core.physics.SimpleForce;
import de.edgelord.sjgl.cosmetic.Animation;
import de.edgelord.sjgl.cosmetic.Spritesheet;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.components.DrawHitboxComponent;
import de.edgelord.sjgl.gameobject.components.DrawPositionComponent;
import de.edgelord.sjgl.gameobject.components.rendering.AnimationRender;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.utils.Directions;

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
        super(new Coordinates(xPos * 150, yPos * 101), 150, 101, "bird");

        this.yPos = yPos;
        this.xPos = xPos;

        animation = new Animation(this);
        spritesheet = new Spritesheet(image, getWidth(), getHeight());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        this.addComponent(new DrawPositionComponent(this, "dev_DrawPosition"));
        this.addComponent(new DrawHitboxComponent(this, "dev_DrawHitbox"));
        this.addComponent(new AnimationRender(this, "standard_animationRender", animation, 90));
        setFriction(0f);
    }

    @Override
    public void initialize() {

        animation.nextFrame();
        getPhysics().addForce(new SimpleForce(this, "constant_forwardForce", 0.25f, Directions.Direction.right));

        System.out.println("INFO: Initialized " + this.getClass());
    }

    @Override
    public void onCollision(GameObject other) {

    }

    @Override
    public void onFixedTick() {

        if (fixedTicks == 15) {

            if (getCoordinates().getY() >= windowHeight) {

                getPosition().setY(0);
            }

            if (getCoordinates().getX() >= windowWidth) {

                getPosition().setX(-getWidth());
                // getPosition().setY(getY() + getHeight());
            }

            fixedTicks = 0;
            return;
        }

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
