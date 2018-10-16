/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package de.edgelord.saltyengine.graphics;

import de.edgelord.saltyengine.components.gfx.GFXComponent;
import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GFXController extends ComponentParent {

    private List<Component> components = Collections.synchronizedList(new ArrayList<>());
    private Transform transform = new Transform(new Vector2f(0, 0), new Dimensions(0, 0));

    public GFXController() {
        super("de.edgelord.saltyengine.gfxController");
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    public void addGFX(GFXComponent component) {
        components.add(component);
    }

    public GFXComponent getGFX(String name) {
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);

            if (component.getTag().equals(Components.GFX_COMPONENT)) {
                GFXComponent gfxComponent = (GFXComponent) component;

                if (gfxComponent.getName().equals(name)) {
                    return gfxComponent;
                }
            }
        }

        return null;
    }

    public void removeGFX(String name) {
        components.removeIf(component -> component.getName().equals(name));
    }

    public void removeGFX(GFXComponent component) {
        components.remove(component);
    }

    public List<Component> getGFX() {
        return components;
    }

    public void startAll() {
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);

            if (component.getTag().equals(Components.GFX_COMPONENT)) {
                GFXComponent gfxComponent = (GFXComponent) component;

                gfxComponent.startGFX();
            }
        }
    }

    public void endAll() {
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);

            if (component.getTag().equals(Components.GFX_COMPONENT)) {
                GFXComponent gfxComponent = (GFXComponent) component;

                gfxComponent.endGFX();
            }
        }
    }

    public void startGFX(String name) {
        getGFX(name).startGFX();
    }

    public void endGFX(String name) {
        getGFX(name).startGFX();
    }

    public void doGFXDrawing(SaltyGraphics saltyGraphics) {

        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);

            if (component.getTag().equals(Components.GFX_COMPONENT)) {
                GFXComponent gfxComponent = (GFXComponent) component;

                gfxComponent.draw(saltyGraphics);
            }
        }
    }

    public void doGFXFixedTick() {

        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);

            if (component.getTag().equals(Components.GFX_COMPONENT)) {
                GFXComponent gfxComponent = (GFXComponent) component;

                gfxComponent.onFixedTick();
            }
        }
    }

    @Override
    public void removeComponent(String identifier) {
        components.removeIf(component -> component.getName().equals(identifier));
    }

    @Override
    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public Component getComponent(String identifier) {
        for (Component component : components) {
            if (component.getName().equals(identifier)) {
                return component;
            }
        }

        return null;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        return;
    }

    public static GFXController getDefaultGFXController() {
        return Game.getDefaultGFXController();
    }
}
