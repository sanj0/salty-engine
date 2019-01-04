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
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Transform;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class RoundedTextBox extends TextBox {

    private float arc = 15f;

    public RoundedTextBox(String text, Coordinates2f position, float width, float height, Coordinates2f textOffset) {
        super(text, position, width, height, textOffset);
    }

    public RoundedTextBox(String text, Transform transform, Coordinates2f textOffset) {
        super(text, transform, textOffset);
    }

    @Override
    public void drawBackground(SaltyGraphics graphics) {
        graphics.drawRoundRect(this, arc);
    }

    public float getArc() {
        return arc;
    }

    public void setArc(float arc) {
        this.arc = arc;
    }
}
