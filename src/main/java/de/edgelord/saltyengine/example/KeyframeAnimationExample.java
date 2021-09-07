/*
 * Copyright 2021 Malte Dostal
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

package de.edgelord.saltyengine.example;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.TransitionFunction;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.MouseInputAdapter;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.event.MouseEvent;

import static de.edgelord.saltyengine.transform.TransformCreator.t;

/**
 * Showcases {@link de.edgelord.saltyengine.core.animation.KeyframeAnimation}s
 * and different transition functions
 */
public class KeyframeAnimationExample extends Game {

    private static boolean move = false;

    public static void main(String[] args) {
        init(GameConfig.config(1920, 1080, "Keyframe Animations!", 5));

        Input.addMouseInputHandler(new MouseInputAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                move = !move;
            }
        });
        start(60, new Scene() {
            @Override
            public void initialize() {
                addGameObject(new Point(100, TransitionFunction.easeInSine(), "ease in sine"));
                addGameObject(new Point(200, TransitionFunction.easeInSine().inverse(), "ease out sine"));
                addGameObject(new Point(300, TransitionFunction.easeInOutSine(), "ease inout sine"));
                addGameObject(new Point(400, TransitionFunction.easeInExpo(), "ease in expo"));
                addGameObject(new Point(500, TransitionFunction.easeInExpo().inverse(), "ease out sine"));
                addGameObject(new Point(600, TransitionFunction.easeInOutExpo(), "ease inout expo"));
                addGameObject(new Point(700, TransitionFunction.easeInElastic(), "ease in elastic"));
                addGameObject(new Point(800, TransitionFunction.easeInOutElastic(), "ease inout elasto"));
                addGameObject(new Point(900, TransitionFunction.easeOutBounce().inverse(), "ease in bounce"));
                addGameObject(new Point(1000, TransitionFunction.easeInOutBounce(), "ease inout bounce"));
            }
        });
    }

    private static class Point extends EmptyGameObject {

        private final KeyframeAnimation animation;
        private final String transitionName;

        public Point(final float y, final TransitionFunction transition, final String transitionName) {
            super(t(0, y, 1, 1), "point");

            this.transitionName = transitionName;
            animation = new KeyframeAnimation(transition);
            animation.appendFrame(0, 0);
            animation.appendFrame(500, Game.getGameWidth());
            animation.appendFrame(1000, 0);
            animation.setLoop(true);
        }

        @Override
        public void onFixedTick() {
            if (move) {
                setX(animation.nextValue());
            }
        }

        @Override
        public void draw(final SaltyGraphics saltyGraphics) {
            saltyGraphics.setColor(ColorUtil.GRAY);
            saltyGraphics.drawPoint(getPosition(), 35);
            saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(25f));
            saltyGraphics.drawText(transitionName, 100, getY() - 25, SaltyGraphics.TextAnchor.BOTTOM_LEFT_CORNER);
        }
    }
}
