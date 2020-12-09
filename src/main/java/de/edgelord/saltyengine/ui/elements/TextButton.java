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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.animation.Keyframe;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.displaymanager.DisplayManager;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * A button that only consist of text without background. Said text animates to
 * being bigger when the mouse hovers of it. X and Y are the position of the
 * centre
 */
public abstract class TextButton extends Button {

    private KeyframeAnimation enterAnimation = new LinearKeyframeAnimation();
    private KeyframeAnimation exitAnimation = new LinearKeyframeAnimation();
    private Keyframe enterAnimationKeyframe = new Keyframe(20, 15);
    private Keyframe exitAnimationKeyframe = new Keyframe(5, -15);

    private Vector2f requestedPosition = new Vector2f(0, 0);
    private float requestedFontSize = 0;
    private float fontSize = 0;

    public TextButton(final String text, final Vector2f position) {
        super(text, position, 0, 0);
        requestedPosition = new Vector2f(position);
        requestedFontSize = getFont().getSize();
        fontSize = requestedFontSize;
        calculateSize();
    }

    public TextButton(final String text, final float x, final float y) {
        super(text, x, y, 0, 0);
        requestedPosition = new Vector2f(x, y);
        requestedFontSize = getFont().getSize();
        fontSize = requestedFontSize;
        calculateSize();
    }

    /**
     * Updates the size of the button.
     */
    public void updateSize() {
        calculateSize();
    }

    private void calculateSize() {
        if (Game.getHost() instanceof DisplayManager) {
            final FontMetrics fontMetrics = Game.getHostAsDisplayManager().getStage().getFontMetrics(getFont());
            setWidth(fontMetrics.stringWidth(getText()));
            setHeight(fontMetrics.getMaxAscent());
        } else {
            System.err.println("TextButton is only really supported with the DisplayManager");
            // eyeball the size lol
            // i have no idea if the below calculation
            // is even remotely accurate
            setWidth(getText().length() * getFont().getSize() / 1.5f);
            setHeight(getFont().getSize());
        }

        super.setPosition(requestedPosition.subtracted(new Vector2f(getWidth() / 2f, getHeight() / 2f)));
    }

    @Override
    public void onFixedTick() {
        super.onFixedTick();
        if (!enterAnimation.animationEnded()) {
            fontSize += enterAnimation.nextDelta();
        } else if (!exitAnimation.animationEnded()) {
            fontSize += exitAnimation.nextDelta();
        } else if (!mouseHoversOver()) {
            fontSize = requestedFontSize;
        }
        super.setFont(getFont().deriveFont(fontSize));
        calculateSize();
    }

    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {
        // do nothing as the button is only text
    }

    @Override
    public void mouseEntered(final Transform cursor) {
        fontSize = requestedFontSize;
        enterAnimation = new LinearKeyframeAnimation(enterAnimationKeyframe);
        enterAnimation.calculateAnimation();
    }

    @Override
    public void mouseExited(final Transform cursor) {
        exitAnimation = new LinearKeyframeAnimation(exitAnimationKeyframe);
        exitAnimation.calculateAnimation();
    }

    @Override
    public void setText(final String text) {
        super.setText(text);
        calculateSize();
    }

    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        calculateSize();
        requestedFontSize = font.getSize();
        fontSize = requestedFontSize;
    }

    @Override
    public void setX(final float x) {
        requestedPosition.setX(x);
    }

    @Override
    public void setY(final float y) {
        requestedPosition.setY(y);
    }

    @Override
    public void setPosition(final Vector2f position) {
        requestedPosition = position;
    }

    /**
     * Gets {@link #enterAnimationKeyframe}.
     *
     * @return the value of {@link #enterAnimationKeyframe}
     */
    public Keyframe getEnterAnimationKeyframe() {
        return enterAnimationKeyframe;
    }

    /**
     * Sets {@link #enterAnimationKeyframe}.
     *
     * @param enterAnimationKeyframe the new value of {@link #enterAnimationKeyframe}
     */
    public void setEnterAnimationKeyframe(final Keyframe enterAnimationKeyframe) {
        this.enterAnimationKeyframe = enterAnimationKeyframe;
    }

    /**
     * Gets {@link #exitAnimationKeyframe}.
     *
     * @return the value of {@link #exitAnimationKeyframe}
     */
    public Keyframe getExitAnimationKeyframe() {
        return exitAnimationKeyframe;
    }

    /**
     * Sets {@link #exitAnimationKeyframe}.
     *
     * @param exitAnimationKeyframe the new value of {@link #exitAnimationKeyframe}
     */
    public void setExitAnimationKeyframe(final Keyframe exitAnimationKeyframe) {
        this.exitAnimationKeyframe = exitAnimationKeyframe;
    }
}
