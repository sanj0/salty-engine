/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.graphics.sprite;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;

import java.util.LinkedList;
import java.util.List;

public class SpritesheetAnimation {

    private List<Frame> frames;
    private int currentFrame = 0;

    public SpritesheetAnimation(final List<Frame> frames) {
        this.frames = frames;
    }

    public SpritesheetAnimation() {
        this.frames = new LinkedList<>();
    }

    public void drawCurrentFrame(final TransformedObject parentObject, final SaltyGraphics saltyGraphics) {
        frames.get(currentFrame).draw(saltyGraphics, parentObject.getPosition(), parentObject.getWidth(), parentObject.getHeight());
    }

    public void restart() {
        currentFrame = 0;
    }

    public void nextFrame() {
        if (getFrames().size() - 1 <= currentFrame) {
            currentFrame = 0;
            return;
        }

        currentFrame++;
    }

    public void addFrame(final Frame frame) {
        this.getFrames().add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(final List<Frame> frames) {
        this.frames = frames;
    }

    /**
     * Gets {@link #currentFrame}.
     *
     * @return the value of {@link #currentFrame}
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Sets {@link #currentFrame}.
     *
     * @param currentFrame the new value of {@link #currentFrame}
     */
    public void setCurrentFrame(final int currentFrame) {
        this.currentFrame = currentFrame;
    }
}
