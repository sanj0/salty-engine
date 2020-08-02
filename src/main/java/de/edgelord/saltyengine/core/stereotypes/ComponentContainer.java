/*
 * Copyright 2018 Malte Dostal
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

package de.edgelord.saltyengine.core.stereotypes;

import de.edgelord.saltyengine.components.Component;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

import java.util.List;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class ComponentContainer implements TransformedObject {

    private String tag;
    private Transform transform;
    private Directions lockedDirections = new Directions();

    public ComponentContainer(final String tag) {
        this.tag = tag;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(final Transform transform) {
        this.transform = transform;
    }

    @Override
    public Directions getLockedDirections() {
        return lockedDirections;
    }

    @Override
    public void setLockedDirections(final Directions lockedDirections) {
        this.lockedDirections = lockedDirections;
    }

    /**
     * Adds the given {@link Component}
     *
     * @param component the {@link Component} to add
     */
    public abstract void addComponent(Component component);

    /**
     * Removes a {@link Component} by searching for the one with the given name
     * and remove that one
     *
     * @param identifier the identifier of the component to be removed
     */
    public abstract void removeComponent(String identifier);

    /**
     * Removes the given {@link Component}. <br> The implementation should be
     * different from {@link #getComponent(String)} due to better performance
     * possible when directly removing a Component
     *
     * @param component the {@link Component} to be removed
     */
    public abstract void removeComponent(Component component);

    /**
     * Returns the {@link List} of {@link Component}s
     *
     * @return the {@link List} of {@link Component}s
     */
    public abstract List<Component> getComponents();

    /**
     * Returns the {@link Component} with the given identifier
     *
     * @param identifier the identifier of the {@link Component} to be returned
     *
     * @return the requested {@link Component}
     */
    public abstract Component getComponent(String identifier);

    /**
     * Calls the method {@link Component#onFixedTick()} for every {@link
     * Component}.
     */
    public void doComponentOnFixedTick() {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.onFixedTick();
            }
        });
    }

    /**
     * Calls the method {@link Component#draw(SaltyGraphics)} for every
     * component with the given {@link SaltyGraphics}
     *
     * @param graphics the graphics context to draw the components
     */
    public void doComponentDrawing(final SaltyGraphics graphics) {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.draw(graphics.copy());
            }
        });
    }

    public void doComponentCursorEntersParent() {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.onCursorEntersParent();
            }
        });
    }

    public void doComponentCursorExitsParent() {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.onCursorExitsParent();
            }
        });
    }

    /**
     * Places this object in the centre of the {@link de.edgelord.saltyengine.core.Host}
     * {@link Game#getHost()}
     */
    public void centrePosition() {
        setPosition(Game.getHost().getCentrePosition(getDimensions()));
    }

    /**
     * Centres this object horizontally in the {@link de.edgelord.saltyengine.core.Host}
     * {@link Game#getHost()}
     */
    public void centreHorizontalPosition() {
        setX(Game.getHost().getHorizontalCentrePosition(getWidth()));
    }

    /**
     * Centres this object vertically in the {@link de.edgelord.saltyengine.core.Host}
     * {@link Game#getHost()}
     */
    public void centreVerticalPosition() {
        setX(Game.getHost().getVerticalCentrePosition(getHeight()));
    }

    public String getTag() {
        return tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }
}
