/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.cosmetic.ColorUtil;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Button extends UIElement {

    private String text;
    private int textWidth, textHeight;
    private Color backgroundColor = Color.black;
    private Color hoverColor = ColorUtil.changeBrightness(backgroundColor, -0.15f);
    private Color clickColor = ColorUtil.changeBrightness(backgroundColor, 0.15f);
    private Color currentBackgroundColor = backgroundColor;
    private Color foregroundColor = Color.white;
    private int arc = 15;

    public Button(String text, Vector2f position, int width, int height) {
        super(position, width, height, BUTTON);

        this.text = text;
    }

    public Button(String text, Transform transform, String tag) {
        this(text, transform.getPosition(), transform.getWidthAsInt(), transform.getHeightAsInt());
    }

    public Button(String text, float x, float y, int width, int height) {
        this(text, new Vector2f(x, y), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (!isSuppressClipping()) {
            saltyGraphics.setClip(getTransform());
        }
        drawButton(saltyGraphics);
        drawText(saltyGraphics);
        if (!isSuppressClipping()) {
            saltyGraphics.resetClip();
        }
    }

    public void drawText(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(getFont());

        textWidth = saltyGraphics.getFontMetrics().stringWidth(text);
        textHeight = saltyGraphics.getFontMetrics().getAscent();

        saltyGraphics.setColor(foregroundColor);

        saltyGraphics.drawText(text, getX() + ((getWidth() - textWidth) / 2), getY() + ((getHeight() + textHeight) / 2));
    }

    public void drawButton(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(currentBackgroundColor);

        saltyGraphics.drawRoundRect(getX(), getY(), getWidth(), getHeight(), arc);
    }

    public boolean touchesButton(MouseEvent e) {
        return (e.getX() > getCoordinates().getX() && e.getX() < getCoordinates().getX() + getWidth()) && (e.getY() > getCoordinates().getY() && e.getY() < getCoordinates().getY() + getHeight());
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public void onFixedTick() {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (touchesButton(e)) {

            currentBackgroundColor = hoverColor;
        } else {
            currentBackgroundColor = backgroundColor;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (touchesButton(e)) {
            currentBackgroundColor = clickColor;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        currentBackgroundColor = backgroundColor;

        if (touchesButton(e)) {
            onClick(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.currentBackgroundColor = backgroundColor;
        this.hoverColor = ColorUtil.changeBrightness(backgroundColor, -0.15f);
        this.clickColor = ColorUtil.changeBrightness(backgroundColor, 0.15f);
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }
}
