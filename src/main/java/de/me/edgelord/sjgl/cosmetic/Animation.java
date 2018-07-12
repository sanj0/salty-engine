/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.cosmetic;

import de.me.edgelord.sjgl.gameobject.GameObject;

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

    public void drawCurrentFrame(Graphics2D graphics) {

        frames.get(currentFrame).draw(graphics, parentGameObject.getCoordinates(), parentGameObject.getWidth(), parentGameObject.getHeight());
    }

    public void drawCurrentFrame(Graphics2D graphics, AffineTransform transform) {

        frames.get(currentFrame).draw(graphics, transform);
    }

    public void resetFrameNumber() {

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

    public void addNewFrame(String relativePath) {

    }

    @Deprecated
    public void loop(Graphics2D graphics) {

        if (getFrames().size() == currentFrameNumber) {

            restartAnimation(graphics);
        } else {

            draw(graphics);
        }
    }

    @Deprecated
    public void restartAnimation(Graphics2D graphics) {

        if (currentFrameNumber != 0) {

            currentFrameNumber = 0;
        }

        draw(graphics);
    }

    @Deprecated
    public void draw(Graphics2D graphics) {

        if (getFrames().size() != currentFrameNumber) {

            getFrames().get(currentFrameNumber).draw(graphics, parentGameObject.getCoordinates(), parentGameObject.getWidth(), parentGameObject.getHeight());

            currentFrameNumber++;
        } else {

            return;
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
