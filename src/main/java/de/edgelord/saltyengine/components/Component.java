/*
 * Copyright 2020 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.CollideAble;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.InitializeAble;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;

import java.util.List;

public abstract class Component<T extends ComponentContainer> implements Drawable, FixedTickRoutine, InitializeAble, CollideAble {

    private T parent;
    private String tag;
    private String name;

    private boolean enabled = true;

    public Component(final T parent, final String name, final String tag) {
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

    public void onCursorEntersParent() {

    }

    public void onCursorExitsParent() {

    }

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
    public void onCollisionDetectionFinish(final List<CollisionEvent> collisions) {

    }

    /**
     * Removes this <code>Component</code> from its <code>Container</code> by
     * calling {@link ComponentContainer#removeComponent(Component)}.
     */
    public void remove() {
        getParent().removeComponent(this);
    }

    public T getParent() {
        return parent;
    }

    public void setParent(final T parent) {
        this.parent = parent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }
}
