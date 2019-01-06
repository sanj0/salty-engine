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

package de.edgelord.saltyengine.core.graphics;

import de.edgelord.saltyengine.components.gfx.GFXComponent;
import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GFXController extends ComponentContainer {

    private List<Component> components = Collections.synchronizedList(new ArrayList<>());
    private Transform transform = new Transform(new Coordinates2f(0, 0), new Dimensions(0, 0));

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
    }

    public static GFXController getDefaultGFXController() {
        return Game.getDefaultGFXController();
    }
}
