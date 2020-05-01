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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class Container extends UIElement {

    private List<UIElement> childElements = new CopyOnWriteArrayList<>();

    public Container(final Vector2f position, final float width, final float height) {
        super(position, width, height, UIElement.CONTAINER);
    }

    public Container(final Transform transform) {
        this(transform.getPosition(), transform.getWidth(), transform.getHeight());
    }

    public Container(final float x, final float y, final float width, final float height) {
        this(new Vector2f(x, y), width, height);
    }

    public void add(final UIElement element) {
        getChildElements().add(element);
    }

    public void remove(final UIElement element) {
        getChildElements().removeIf(uiElement -> uiElement == element);
        element.setSuppressClipping(false);
    }

    public void suppressAllClipping() {
        for (final UIElement element : getChildElements()) {
            element.setSuppressClipping(true);
        }
    }

    public List<UIElement> getChildElements() {
        return childElements;
    }

    public void setChildElements(final List<UIElement> childElements) {
        this.childElements = childElements;
    }
}
