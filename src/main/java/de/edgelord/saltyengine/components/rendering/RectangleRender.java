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
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class RectangleRender extends PrimitiveRenderComponent {

    public RectangleRender(final ComponentContainer parent, final String name, final Color color) {
        super(parent, name, color);
    }

    public RectangleRender(final ComponentContainer parent, final String name) {
        this(parent, name, ColorUtil.BLACK);
    }

    @Override
    public void updateImageData() {
        setPrimitiveDraw(saltyGraphics -> {
            if (isFill()) {
                saltyGraphics.drawRect(0, 0, getParent().getWidth(), getParent().getHeight());
            } else {
                saltyGraphics.outlineRect(getLineWidth() / 2f, getLineWidth() / 2f, getParent().getWidth(), getParent().getHeight());
            }
        });
    }
}
