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

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Time;

@Deprecated
public abstract class TextBox extends TextElement {

    private float cursor = 1;
    private float speed = 0.020f;
    private Vector2f textOffset;

    public TextBox(String text, Vector2f position, float width, float height, Vector2f textOffset) {
        this(text, new Transform(position, new Dimensions(width, height)), textOffset);
    }

    public TextBox(String text, Transform transform, Vector2f textOffset) {
        super(text, transform, TEXT_BOX);

        this.textOffset = textOffset;
    }

    public void drawText(SaltyGraphics graphics) {
        graphics.setColor(getForegroundColor());
        String currentText;
        if (cursor >= getText().length()) {
            currentText = getText();
        } else {
            currentText = getText().substring(0, Math.round(cursor));
        }
        graphics.drawText(currentText, getX() + textOffset.getX(), getY() + textOffset.getY());
        cursor += (float) Time.getDeltaTime() * speed;
    }

    public abstract void drawBackground(SaltyGraphics graphics);

    @Override
    public final void draw(SaltyGraphics saltyGraphics) {
        prepareGraphics(saltyGraphics);
        drawBackground(saltyGraphics);
        drawText(saltyGraphics);
    }

    public void start() {
        cursor = 1;
    }

    public void start(String text) {
        setText(text);
        start();
    }
}
