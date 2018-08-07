package de.edgelord.sjgl.gameobject;

import de.edgelord.sjgl.core.event.CollisionEvent;

import java.awt.*;

/**
 * GameObjectComponents are a way of modifying what happens every fixed tick to a GameObject, or how to draw a GameObject
 * The methods which can be used for that are #onFixedTick() #draw(Graphics2D) and
 * #onCollision(GameObject) (for example used for Physics)
 */
public abstract class GameObjectComponent {

    private GameObject parent;
    private String name;
    private boolean enabled = true;

    public GameObjectComponent(GameObject parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public GameObject getParent() {
        return parent;
    }

    public abstract void onFixedTick();

    public abstract void draw(Graphics2D graphics);

    public abstract void onCollision(CollisionEvent e);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enable(){
        enabled = true;
    }

    public void disable(){
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
