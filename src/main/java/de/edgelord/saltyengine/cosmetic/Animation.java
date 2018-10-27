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

package de.edgelord.saltyengine.cosmetic;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

public class Animation {

    private List<Frame> frames = new LinkedList<>();
    private int currentFrame = 0;

    private GameObject parentGameObject;

    private int currentFrameNumber = 0;

    public Animation(GameObject parentGameObject) {

        this.parentGameObject = parentGameObject;
    }

    public Animation(LinkedList<Frame> frames) {

        this.frames = frames;
    }

    public void drawCurrentFrame(SaltyGraphics saltyGraphics) {

        frames.get(currentFrame).draw(saltyGraphics, parentGameObject.getPosition(), parentGameObject.getWidth(), parentGameObject.getHeight());
    }

    public void drawCurrentFrame(Graphics2D graphics, AffineTransform transform) {

        frames.get(currentFrame).draw(graphics, transform);
    }

    public void resetFrameNumber() {
        currentFrame = 0;
    }

    public void nextFrame() {

        if (getFrames().size() - 1 <= currentFrame) {

            currentFrame = 0;
            return;
        }

        currentFrame++;
    }

    public void addFrame(Frame frame) {

        this.getFrames().add(frame);
    }

    @Deprecated
    public void loop(SaltyGraphics saltyGraphics) {

        if (getFrames().size() == currentFrameNumber) {

            restartAnimation(saltyGraphics);
        } else {

            draw(saltyGraphics);
        }
    }

    @Deprecated
    public void restartAnimation(SaltyGraphics saltyGraphics) {

        if (currentFrameNumber != 0) {

            currentFrameNumber = 0;
        }

        draw(saltyGraphics);
    }

    @Deprecated
    public void draw(SaltyGraphics saltyGraphics) {

        if (getFrames().size() != currentFrameNumber) {

            getFrames().get(currentFrameNumber).draw(saltyGraphics, parentGameObject.getPosition(), parentGameObject.getWidth(), parentGameObject.getHeight());

            currentFrameNumber++;
        } else {

        }
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }

    public void setCurrentFrameNumber(int currentFrameNumber) {
        this.currentFrameNumber = currentFrameNumber;
    }
}
