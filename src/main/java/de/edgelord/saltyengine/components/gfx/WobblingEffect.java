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

package de.edgelord.saltyengine.components.gfx;

import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.util.Random;

public class WobblingEffect extends GFXComponent {

    private int maxHeightDelta;
    private int maxWidthDelta;
    private int minHeightDelta;
    private int minWidthDelta;
    private int widthDeltaPerTick = 2;
    private int heightDeltaPerTick = 2;
    private int pause = 250;
    private int ticks = 0;

    private boolean initRun = true;
    private int heightDelta = 0;
    private int widthDelta = 0;
    private boolean shrinkingWidth = false;
    private boolean shrinkingHeight = false;

    private Mode mode = Mode.linear;

    public enum Mode {
        random,
        linear
    }

    Random random = new Random();

    public WobblingEffect(ComponentContainer parent, String name) {
        super(parent, name);
    }

    public WobblingEffect(ComponentContainer parent, String name, int maxHeightDelta, int maxWidthDelta, int minHeightDelta, int minWidthDelta) {
        super(parent, name);
        this.maxHeightDelta = maxHeightDelta;
        this.maxWidthDelta = maxWidthDelta;
        this.minHeightDelta = minHeightDelta;
        this.minWidthDelta = minWidthDelta;
    }

    public void init(final int maxHeightDelta, final int maxWidthDelta, final int minHeightDelta, final int minWidthDelta) {

        this.maxHeightDelta = maxHeightDelta;
        this.maxWidthDelta = maxWidthDelta;
        this.minHeightDelta = minHeightDelta;
        this.minWidthDelta = minWidthDelta;

        this.widthDelta = 0;
        this.heightDelta = 0;

        initRun = true;
    }

    @Override
    public void onFixedTick() {

        if (ticks == pause) {
            int currentHeightDelta = 0;
            int currentWidthDelta = 0;

            if (initRun && mode == Mode.linear) {
                currentHeightDelta = minHeightDelta;
                currentWidthDelta = minWidthDelta;

                initRun = false;
            }

            switch (mode) {

                case random:
                    currentHeightDelta = random.nextInt(maxHeightDelta - minHeightDelta) + minHeightDelta;
                    currentWidthDelta = random.nextInt(maxWidthDelta - minWidthDelta) + minWidthDelta;
                    break;
                case linear:
                    currentHeightDelta = heightDeltaPerTick;
                    currentWidthDelta = widthDeltaPerTick;
                    if (shrinkingWidth) {
                        widthDelta -= currentWidthDelta;
                        currentWidthDelta = (widthDeltaPerTick * (-1));
                    } else {
                        widthDelta += currentWidthDelta;
                        currentWidthDelta = widthDeltaPerTick;
                    }

                    if (shrinkingHeight) {
                        heightDelta -= currentHeightDelta;
                        currentHeightDelta = (heightDeltaPerTick * (-1));
                    } else {
                        heightDelta += currentHeightDelta;
                        currentHeightDelta = heightDeltaPerTick;
                    }

                    if (!shrinkingWidth && widthDelta >= maxWidthDelta) {
                        shrinkingWidth = true;
                    } else if (shrinkingWidth && widthDelta <= minWidthDelta) {
                        shrinkingWidth = false;
                    }

                    if (!shrinkingHeight && (heightDelta >= maxHeightDelta)) {
                        shrinkingHeight = true;
                    } else if (shrinkingHeight && (heightDelta <= minHeightDelta)) {
                        shrinkingHeight = false;
                    }
                    break;
            }

            getParent().setHeight(getParent().getHeight() + currentHeightDelta);
            getParent().setY(getParent().getY() + ((float) (currentHeightDelta / 2)) * (-1));

            getParent().setWidth(getParent().getWidth() + currentWidthDelta);
            getParent().setX(getParent().getX() + ((float) (currentWidthDelta / 2)) * (-1));

            ticks = 0;
        } else {
            ticks++;
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // This effect actually only uses the ON_FIXED_TICK, because it only needs to change some values
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getWidthDeltaPerTick() {
        return widthDeltaPerTick;
    }

    public void setWidthDeltaPerTick(int widthDeltaPerTick) {
        this.widthDeltaPerTick = widthDeltaPerTick;
    }

    public int getHeightDeltaPerTick() {
        return heightDeltaPerTick;
    }

    public void setHeightDeltaPerTick(int heightDeltaPerTick) {
        this.heightDeltaPerTick = heightDeltaPerTick;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
}
