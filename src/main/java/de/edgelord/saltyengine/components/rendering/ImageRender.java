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
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.Components;

/**
 * A {@link de.edgelord.saltyengine.core.Component}
 * that draws an {@link #image} using its parent's
 * position and size.
 */
@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class ImageRender extends RenderComponent {

    // The image to render
    private SaltyImage image;

    /**
     * The default super constructor for
     * gameObjectComponent, which takes in the
     * parent GameObject and the name, used as an
     * id, for fishing specific Components out of
     * a list
     *
     * @param parent the parent of the Component,
     *               so where to take the e.g. the
     *               Coordinate info from
     * @param name   the id-name for this
     *               Component
     * @param image  the image to be drawn by this
     *               component
     *
     * @see de.edgelord.saltyengine.core.Component
     */
    public ImageRender(final ComponentContainer parent, final String name, final SaltyImage image) {
        super(parent, name, Components.RENDER_COMPONENT);

        this.image = image;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        // Draw the image
        saltyGraphics.drawImage(image, getParent());
    }

    /**
     * Gets {@link #image}.
     *
     * @return the value of {@link #image}
     */
    public SaltyImage getImage() {
        return image;
    }

    /**
     * Sets {@link #image}.
     *
     * @param image the new value of {@link
     *              #image}
     */
    public void setImage(final SaltyImage image) {
        this.image = image;
    }
}
