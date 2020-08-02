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
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.event.MouseEvent;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class TexturedButton extends Button {

    private boolean drawText = true;
    private SaltyImage texture;

    public TexturedButton(final String text, final Vector2f position, final int width, final int height, final SaltyImage texture) {
        super(text, position, width, height);

        this.texture = texture;
    }

    @Override
    public void drawForeground(final SaltyGraphics saltyGraphics) {
        if (drawText) {
            super.drawForeground(saltyGraphics);
        }
    }

    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(texture, getX(), getY(), getWidth(), getHeight());
    }

    public abstract void onClick(MouseEvent e);

    public boolean isDrawText() {
        return drawText;
    }

    public void setDrawText(final boolean drawText) {
        this.drawText = drawText;
    }

    public SaltyImage getTexture() {
        return texture;
    }

    public void setTexture(final SaltyImage texture) {
        this.texture = texture;
    }
}
