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

package de.edgelord.saltyengine.effect;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.input.*;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A textbox that displays text (e.g. dialog) in
 * front of a static background. The background is
 * a screenshot of the game at the exact moment
 * the constructor is invoked.
 * <p>
 * Newlines in the text are marked by {@code \n}
 * while new "pages" of the box are to be marked
 * by {@code \f}.
 * <p>
 * While this Scene is active, any input handlers
 * in {@link Input} are disabled.
 */
public class StaticBackgroundTextbox extends Scene {

    private final String text;
    private final Scene sceneToReturnTo;
    private final SaltyImage backgroundImage;
    private final String[] pages;
    private final List<KeyboardInputHandler> keyHandlers;
    private final List<MouseInputHandler> mouseHandlers;
    private final Transform transform;
    private Color backgroundColor;
    private Color textColor;
    private Font font;
    private float textSpeed = 0.05f;
    private int cornerArc = 15;
    private int textOffsetX = 25;
    private int textOffsetY = 25;
    private boolean initializeScene = false;
    private boolean centreTextX = false;
    private int centredX = -1;
    private String currentDisplayText = "";
    private int pageIndex = 0;
    private float cursor = 0;
    private KeyboardInputHandler keyboardHandler;
    private MouseInputHandler mouseHandler;

    /**
     * The constrcutor with all necessary
     * parameters. All other parameters can be
     * added with the method prefixed by {@code
     * with}.
     *
     * @param text            the text
     * @param transform       the transform of the
     *                        box around (below)
     *                        the text
     * @param sceneToReturnTo the Scene that is
     *                        being returned to
     *                        after the last page
     *                        of the textbox
     */
    public StaticBackgroundTextbox(final String text, final Transform transform, final Scene sceneToReturnTo) {
        this.text = text;
        this.transform = transform;
        this.sceneToReturnTo = sceneToReturnTo;

        keyHandlers = new ArrayList<>(Input.getKeyboardHandlers());
        Input.getKeyboardHandlers().clear();
        mouseHandlers = new ArrayList<>(Input.getMouseHandlers());
        Input.getMouseHandlers().clear();

        this.backgroundImage = Game.getHost().getScreenshot();
        pages = this.text.split("\f");
    }

    public StaticBackgroundTextbox withBackgroundColor(final Color color) {
        this.backgroundColor = color;
        return this;
    }

    public StaticBackgroundTextbox withTextColor(final Color color) {
        this.textColor = color;
        return this;
    }

    public StaticBackgroundTextbox withFont(final Font font) {
        this.font = font;
        return this;
    }

    public StaticBackgroundTextbox withTextSpeed(final float textSpeed) {
        this.textSpeed = textSpeed;
        return this;
    }

    public StaticBackgroundTextbox withTextOffsetX(final int textOffsetX) {
        this.textOffsetX = textOffsetX;
        return this;
    }

    public StaticBackgroundTextbox withTextOffsetY(final int textOffsetY) {
        this.textOffsetY = textOffsetY;
        return this;
    }

    public StaticBackgroundTextbox withCornerArc(final int cornerArc) {
        this.cornerArc = cornerArc;
        return this;
    }

    public StaticBackgroundTextbox centredTextX() {
        this.centreTextX = true;
        return this;
    }

    public StaticBackgroundTextbox initializeScene() {
        this.initializeScene = true;
        return this;
    }

    @Override
    public void initialize() {
        addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
            @Override
            public void draw(final SaltyGraphics saltyGraphics) {
                saltyGraphics.drawImage(backgroundImage, Vector2f.zero());
            }
        });

        addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(final SaltyGraphics saltyGraphics) {
                saltyGraphics.setColor(backgroundColor);
                saltyGraphics.drawRoundRect(transform, cornerArc);
                saltyGraphics.setColor(textColor);
                saltyGraphics.setFont(font);

                float x = transform.getX() + textOffsetX;
                if (centreTextX) {
                    if (centredX == -1) {
                        // get longest line of page
                        final String[] lines = pages[pageIndex].split("\n");
                        String longestLine = "";
                        for (final String l : lines) {
                            if (l.length() > longestLine.length()) {
                                longestLine = l;
                            }
                        }
                        centredX = Math.round(transform.getX() + (transform.getWidth() / 2f - (saltyGraphics.getFontMetrics().stringWidth(longestLine) / 2f)));
                    }

                    x = centredX;
                }
                saltyGraphics.drawMultilineText(currentDisplayText, x, transform.getY() + textOffsetY);
            }
        });

        keyboardHandler = new KeyboardInputAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    proceed();
                }
            }
        };

        mouseHandler = new MouseInputAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                proceed();
            }
        };

        Input.addKeyboardInputHandler(keyboardHandler);
        Input.addMouseInputHandler(mouseHandler);
    }

    @Override
    public void onFixedTick() {
        super.onFixedTick();

        if (cursor < pages[pageIndex].length()) {
            cursor += textSpeed;
        }
        currentDisplayText = pages[pageIndex].substring(0, Math.min(Math.round(cursor), pages[pageIndex].length()));
    }

    private void proceed() {
        // check if the last page was reached
        if (pageIndex >= pages.length - 1) {
            // last page was already reached
            if (Math.round(cursor) < pages[pageIndex].length()) {
                cursor = pages[pageIndex].length();
            } else {
                Input.getKeyboardHandlers().remove(keyboardHandler);
                Input.getMouseHandlers().remove(mouseHandler);
                Input.getKeyboardHandlers().addAll(keyHandlers);
                Input.getMouseHandlers().addAll(mouseHandlers);
                SceneManager.setCurrentScene(sceneToReturnTo, initializeScene);
            }
        } else {
            // last page was not already reached yet
            if (Math.round(cursor) < pages[pageIndex].length()) {
                cursor = pages[pageIndex].length();
            } else {
                pageIndex++;
                currentDisplayText = "";
                cursor = 0;
                centredX = -1;
            }
        }
    }
}
