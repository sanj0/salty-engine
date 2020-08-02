/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextRenderComponent extends Component<ComponentContainer> {

    private final List<String> lines = new ArrayList<>();
    private Font font = SaltySystem.defaultFont;
    private float leading = font.getSize() * 1.3f;
    private String text;
    private boolean recalculate = true;
    private String wordSeparator = " ";
    private Vector2f offset;
    private float lineLength;

    public TextRenderComponent(final ComponentContainer parent, final String name, final float lineLength, final Vector2f offset) {
        super(parent, name, Components.RENDER_COMPONENT);

        this.lineLength = lineLength;
        this.offset = offset;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(font);
        final FontMetrics fontMetrics = saltyGraphics.getFontMetrics();

        if (recalculate) {
            lines.clear();
            final String[] words = text.split(wordSeparator);
            float currentX = 0;
            StringBuilder lineBuilder = new StringBuilder();

            for (final String word : words) {
                final float length = fontMetrics.stringWidth(word + wordSeparator);

                if (currentX + length <= lineLength) {
                    lineBuilder.append(word).append(wordSeparator);
                    currentX += length;
                } else {
                    lines.add(lineBuilder.toString());
                    lineBuilder = new StringBuilder();
                    lineBuilder.append(word).append(wordSeparator);
                    currentX = length;
                }
            }

            lines.add(lineBuilder.toString());

            recalculate = false;
        }

        float currentY = -leading;
        for (final String line : lines) {
            saltyGraphics.drawText(line, getParent().getPosition().getX() + offset.getX(), currentY + getParent().getY() + offset.getY(), SaltyGraphics.TextAnchor.TOP_LEFT_CORNER);

            currentY += leading;
        }
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    public Font getFont() {
        return font;
    }

    public void setFont(final Font font) {
        this.font = font;
        leading = font.getSize() * 1.3f;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
        recalculate = true;
    }

    public String getWordSeparator() {
        return wordSeparator;
    }

    public void setWordSeparator(final String wordSeparator) {
        this.wordSeparator = wordSeparator;
    }

    public Vector2f getOffset() {
        return offset;
    }

    public void setOffset(final Vector2f offset) {
        this.offset = offset;
    }

    public float getLineLength() {
        return lineLength;
    }

    public void setLineLength(final float lineLength) {
        this.lineLength = lineLength;
    }
}
