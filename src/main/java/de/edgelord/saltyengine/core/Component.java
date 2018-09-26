package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.*;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.util.List;

public abstract class Component<T extends ComponentParent> implements Drawable, FixedTickRoutine, InitializeAble, CollideAble {

    private T parent;
    private String tag;
    private String name;

    private boolean enabled = true;

    public Component(T parent, String name, String tag) {
        this.parent = parent;
        this.name = name;
        this.tag = tag;
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public abstract void onFixedTick();

    @Override
    public abstract void onCollision(CollisionEvent e);

    /*
     * You won't need that method often for a component
     */
    @Override
    public void initialize() {

    }

    /*
     * You won't need that method often for a component
     */
    @Override
    public void onCollisionDetectionFinish(List<CollisionEvent> collisions) {

    }

    public void remove() {
        getParent().removeComponent(getName());
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }
}
