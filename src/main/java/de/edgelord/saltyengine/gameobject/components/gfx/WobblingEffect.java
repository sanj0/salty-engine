/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.gfx;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
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

    public WobblingEffect(GameObject parent, String name) {
        super(parent, name);
    }

    public WobblingEffect(GameObject parent, String name, int maxHeightDelta, int maxWidthDelta, int minHeightDelta, int minWidthDelta) {
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
            getParent().moveY(((float) (currentHeightDelta / 2)) * (-1));

            getParent().setWidth(getParent().getWidth() + currentWidthDelta);
            getParent().moveX(((float) (currentWidthDelta / 2)) * (-1));

            ticks = 0;
        } else {
            ticks++;
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // This effect actually only uses the ON_FIXED_TICK, because it only needs to change some values
    }

    @Override
    public void onCollision(CollisionEvent e) {

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
