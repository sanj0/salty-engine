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
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * This class is an abstract label-like implementation of the class {@link TextElement}
 * In general, these elements are text container with no writing feature
 * nor a caret showing up.
 */
public abstract class Label extends TextElement {

    public Label(String text, Vector2f position, float width, float height) {
        super(text, position, width, height, LABEL);
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);
}
