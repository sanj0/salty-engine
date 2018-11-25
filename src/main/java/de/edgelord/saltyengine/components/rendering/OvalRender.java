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

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;

/**
 * This component is used for simplifying the process of rendering an oval.
 * <p>
 * The only thing that is LEFT for the user to call is
 * <code>GameObject.addComponent(new OvalRender(this, "some_id_name"));</code>
 */
public class OvalRender extends PrimitivesRenderComponent {

    public OvalRender(final ComponentContainer parent, final String name) {
        super(parent, name);
    }

    @Override
    public void updateImageData() {
        setPrimitiveDraw(saltyGraphics -> {
            if (isFill()) {
                saltyGraphics.drawOval(0, 0, getParent().getWidth(), getParent().getHeight());
            } else {
                saltyGraphics.outlineOval(getLineWidth() / 2f, getLineWidth() / 2f, getParent().getWidth(), getParent().getHeight());
            }
        });
    }
}
