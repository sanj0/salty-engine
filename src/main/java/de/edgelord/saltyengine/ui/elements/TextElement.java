/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
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
