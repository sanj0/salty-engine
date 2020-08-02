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

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class RoundRectRender extends PrimitiveRenderComponent {

    private float arc;

    /**
     * The default super constructor for gameObjectComponent, which takes in the
     * parent GameObject and the name, used as an id, for fishing specific
     * Components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the
     *               Coordinate info from
     * @param name   the id-name for this Component
     * @param arc    the diameter of the circle at the corner
     */
    public RoundRectRender(final ComponentContainer parent, final String name, final Color color, final float arc) {
        super(parent, name, color);

        this.arc = arc;
    }

    public float getArc() {
        return arc;
    }

    public void setArc(final float arc) {
        this.arc = arc;
    }

    @Override
    public void updateImageData() {
        setPrimitiveDraw(saltyGraphics -> {
            if (isFill()) {
                saltyGraphics.drawRoundRect(0, 0, getParent().getWidth(), getParent().getHeight(), getArc());
            } else {
                saltyGraphics.drawRoundRect(getLineWidth() / 2f, getLineWidth() / 2f, getParent().getWidth(), getParent().getHeight(), getArc());
            }
        });
    }
}
