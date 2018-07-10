package de.me.edgelord.sjgl.gameobjectComponent;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Vector2f;
import de.me.edgelord.sjgl.utils.Directions;

import java.awt.*;

public class SimplePhysics extends Component {

    private float currentXForce = 0f;
    private float currentYForce = 0f;
    private float gravity;
    private float airFriction;
    private float currentGravityForce = 0f;

    public SimplePhysics(GameObject parent, float gravity, float airFriction) {
        super(parent);

        this.gravity = gravity;
        this.airFriction = airFriction;
    }

    private void calculateFriction() {

        for (GameObject gameObject : getParent().getTouchingGameObjects()) {

            currentXForce -= gameObject.getFriction();
            currentYForce -= gameObject.getFriction();
            currentGravityForce = gravity - gameObject.getFriction();
        }

        currentGravityForce = gravity - airFriction;
        currentXForce = currentXForce - airFriction;
        currentYForce = currentYForce - airFriction;

        if (currentYForce < 0f) {
            currentYForce = 0f;
        }

        if (currentXForce < 0f) {

            currentXForce = 0f;
        }

        if (currentGravityForce < 0f){

            currentGravityForce = 0f;
        }
    }

    @Override
    public void onFixedTick() {

        // Calculate Friction to all forces

        calculateFriction();

        // Do gravity stuff

        getParent().setVector2f(new Vector2f(getParent().getVector2f().getX(), getParent().getVector2f().getY() + currentGravityForce));


        // Add forces to GameObject

        getParent().setVector2f(new Vector2f(getParent().getVector2f().getX() + currentXForce, getParent().getVector2f().getY() + currentYForce));

    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(GameObject other) {

    }

    public void addForce(float force, Directions.BasicDirection direction) {

        if (direction == Directions.BasicDirection.x){

            currentXForce += force;
        } else {
            currentYForce += force;
        }
    }

    public void addUnstackingForce(float force, Directions.BasicDirection direction){

        if (direction == Directions.BasicDirection.x){

            currentXForce = force;
        } else {
            currentYForce = force;
        }
    }
}
