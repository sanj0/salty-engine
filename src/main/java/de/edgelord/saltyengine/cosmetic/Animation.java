/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
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
