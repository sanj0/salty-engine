/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.core.stereotypes;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;

import java.util.List;

public abstract class ComponentParent implements TransformedObject {

    private String tag;

    public ComponentParent(String tag) {
        this.tag = tag;
    }

    /**
     * Adds the given {@link Component}
     *
     * @param component the component to add
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
     * Removes the given {@link Component}
     * The implementation should be different from {@link #getComponent(String)}
     * due to better performance possible when directly removing a Component
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
     * @return the requested {@link Component}
     */
    public abstract Component getComponent(String identifier);

    /**
     * Calls the method {@link Component#onFixedTick()} for every {@link Component}.
     */
    public void doComponentOnFixedTick() {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.onFixedTick();
            }
        });
    }

    /**
     * Calls the method {@link Component#draw(SaltyGraphics)} for every component with the given {@link SaltyGraphics}
     *
     * @param graphics the graphics context to draw the components
     */
    public void doComponentDrawing(SaltyGraphics graphics) {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.draw(graphics);
            }
        });
    }

    /**
     * Places this object in the centre of the {@link de.edgelord.saltyengine.core.Host} {@link Game#host}
     */
    public void centrePosition() {
        setPosition(Game.getHost().getCentrePosition(getDimensions()));
    }

    /**
     * Centres this object horizontally in the {@link de.edgelord.saltyengine.core.Host} {@link Game#host}
     */
    public void centreHorizontalPosition() {
        setX(Game.getHost().getHorizontalCentrePosition(getWidth()));
    }

    /**
     * Centres this object vertically in the {@link de.edgelord.saltyengine.core.Host} {@link Game#host}
     */
    public void centreVerticalPosition() {
        setX(Game.getHost().getVerticalCentrePosition(getHeight()));
    }

    @Override
    public abstract Transform getTransform();

    @Override
    public abstract void setTransform(Transform transform);

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
