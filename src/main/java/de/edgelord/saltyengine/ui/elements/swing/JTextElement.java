/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.ui.elements.swing;

import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Rotation;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import javax.swing.text.JTextComponent;
import java.awt.*;

public class JTextElement extends SwingUIElement {

    public JTextElement(final JTextComponent component, final Vector2f position, final float width, final float height) {
        super(component, position, width, height);
    }

    public JTextElement(final JTextComponent component, final Transform transform) {
        super(component, transform);
    }

    @Override
    public Font getFont() {
        return super.getFont();
    }

    @Override
    public void setFont(final Font font) {
        super.setFont(font);
    }

    @Override
    public Color getBackgroundColor() {
        return super.getBackgroundColor();
    }

    @Override
    public void setBackgroundColor(final Color backgroundColor) {
        super.setBackgroundColor(backgroundColor);
    }

    @Override
    public Color getForegroundColor() {
        return super.getForegroundColor();
    }

    @Override
    public void setForegroundColor(final Color foregroundColor) {
        super.setForegroundColor(foregroundColor);
    }

    @Override
    public void setTransform(final Transform transform) {
        super.setTransform(transform);
    }

    @Override
    public void centrePosition() {
        super.centrePosition();
    }

    @Override
    public void centreHorizontalPosition() {
        super.centreHorizontalPosition();
    }

    @Override
    public void centreVerticalPosition() {
        super.centreVerticalPosition();
    }

    @Override
    public void setRotation(final Rotation rotation) {

    }

    @Override
    public void setRotationDegrees(final float rotationDegrees) {

    }

    @Override
    public void setDimensions(final Dimensions dimensions) {

    }

    @Override
    public void setPosition(final Vector2f position) {

    }

    @Override
    public void setWidth(final float width) {

    }

    @Override
    public void setHeight(final float height) {

    }

    @Override
    public void setX(final float x) {

    }

    @Override
    public void setY(final float y) {

    }

    @Override
    public void positionByCentre(final Vector2f centre) {

    }

    @Override
    public void moveToFacedDirection(final float delta) {

    }

    @Override
    public void moveInAngle(final float degrees, final float delta) {

    }

    @Override
    public void basicMove(final float delta, final Directions.BasicDirection direction) {

    }

    @Override
    public void move(final float delta, final Directions.Direction direction) {

    }

    @Override
    public void moveY(final float delta) {

    }

    @Override
    public void moveX(final float delta) {

    }

    public JTextComponent getTextComponent() {
        return (JTextComponent) getComponent();
    }
}
