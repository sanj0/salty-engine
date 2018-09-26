package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.CollideAble;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.InitializeAble;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public abstract class Component<T> implements Drawable, FixedTickRoutine, InitializeAble, CollideAble {

    private T parent;
    private String tag;

    public Component(T parent, String tag) {
        this.parent = parent;
        this.tag = tag;
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public abstract void onFixedTick();

    @Override
    public abstract void onCollision(CollisionEvent e);

    @Override
    public abstract void initialize();

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
}
