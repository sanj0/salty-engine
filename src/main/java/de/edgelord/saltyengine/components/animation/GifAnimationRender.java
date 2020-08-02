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

package de.edgelord.saltyengine.components.animation;

import de.edgelord.saltyengine.components.rendering.RenderComponent;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.resource.Resource;

import java.awt.*;
import java.net.MalformedURLException;

/**
 * Used for easily rendering an animated gif.
 */
@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class GifAnimationRender extends RenderComponent {

    /**
     * The gif image
     */
    private final Image gif;

    /**
     * The only constructor taking in all the necessary parameters.
     *
     * @param parent      the parent (or rather container) of this component.
     * @param name        the id-name of this component
     * @param path        the path of the gif
     * @param imageSource where to get the gif from.
     *
     * @throws MalformedURLException when there is an error with loading the
     *                               gif
     */
    public GifAnimationRender(final ComponentContainer parent, final String name, final String path, final Resource imageSource) throws MalformedURLException {
        super(parent, name, Components.GIF_RENDER);

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.gif = toolkit.createImage(imageSource.pathToURL(path));
        toolkit.prepareImage(gif, -1, -1, Game.getHost().getImageObserver());
    }

    /**
     * Draws the animated gif with the file-specified frame rate at its parent's
     * {@link de.edgelord.saltyengine.transform.Transform}.
     *
     * @param saltyGraphics the {@link SaltyGraphics} to render to.
     */
    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.getGraphics2D().drawImage(gif, Math.round(getParent().getX()), Math.round(getParent().getY()), Math.round(getParent().getWidth()), Math.round(getParent().getHeight()), Game.getHost().getImageObserver());
    }
}
