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

public class JTextElement extends SwingUiElement {

    public JTextElement(JTextComponent component, Vector2f position, float width, float height) {
        super(component, position, width, height);
    }

    public JTextElement(JTextComponent component, Transform transform) {
        super(component, transform);
    }

    @Override
    public Font getFont() {
        return super.getFont();
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
    }

    @Override
    public Color getBackgroundColor() {
        return super.getBackgroundColor();
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        super.setBackgroundColor(backgroundColor);
    }

    @Override
    public Color getForegroundColor() {
        return super.getForegroundColor();
    }

    @Override
    public void setForegroundColor(Color foregroundColor) {
        super.setForegroundColor(foregroundColor);
    }

    @Override
    public void setTransform(Transform transform) {
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
    public void setRotation(Rotation rotation) {

    }

    @Override
    public void setRotationDegrees(float rotationDegrees) {

    }

    @Override
    public void setDimensions(Dimensions dimensions) {

    }

    @Override
    public void setPosition(Vector2f position) {

    }

    @Override
    public void setWidth(float width) {

    }

    @Override
    public void setHeight(float height) {

    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

    }

    @Override
    public void positionByCentre(Vector2f centre) {

    }

    @Override
    public void moveToFacedDirection(float delta) {

    }

    @Override
    public void moveInAngle(float degrees, float delta) {

    }

    @Override
    public void basicMove(float delta, Directions.BasicDirection direction) {

    }

    @Override
    public void move(float delta, Directions.Direction direction) {

    }

    @Override
    public void moveY(float delta) {

    }

    @Override
    public void moveX(float delta) {

    }

    public JTextComponent getTextComponent() {
        return (JTextComponent) getComponent();
    }
}
