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

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

public abstract class TextElement extends UIElement {

    private String text;

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.centered;
    private VerticalAlignment verticalAlignment = VerticalAlignment.centered;

    /**
     * The horizontal alignments for the text within the bounds of the label.
     * An implementation of TextElement should take this into account.
     * The default is {@link HorizontalAlignment#centered}
     */
    public enum HorizontalAlignment {
        right,
        left,
        centered
    }

    /**
     * The vertical alignments for the text within the bounds of the label.
     * An implementation of TextElement should take this into account.
     * The default is {@link VerticalAlignment#centered}
     */
    public enum VerticalAlignment {
        top,
        bottom,
        centered
    }

    public TextElement(String text, Vector2f position, float width, float height, String tag) {
        this(text, new Transform(position, new Dimensions(width, height)), tag);
    }

    public TextElement(String text, Transform transform, String tag) {
        super(transform.getPosition(), transform.getWidth(), transform.getHeight(), tag);

        this.text = text;
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }
}
