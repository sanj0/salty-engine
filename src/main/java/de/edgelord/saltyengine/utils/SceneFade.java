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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

public class SceneFade {
    public static void crossFade(final Scene next, final int duration) {
        final SaltyImage firstScene = Game.getHost().getScreenshot();
        next.addDrawingRoutine(new FadeDrawer(firstScene, duration));
        SceneManager.setCurrentScene(next);
    }

    public static void fadeFromColor(final Scene next, final Color color, final int duration) {
        final Scene intermediate = new Scene() {
            @Override
            public void initialize() {
                addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.LAST) {
                    @Override
                    public void draw(final SaltyGraphics saltyGraphics) {
                        saltyGraphics.setColor(color);
                        saltyGraphics.drawRect(Vector2f.zero(), Game.getGameDimensions());
                    }
                });
            }
        };
        crossFade(intermediate, duration / 2);
        Game.executeLater(() -> crossFade(next, duration * 2), duration / 2);
    }

    private static class FadeDrawer extends DrawingRoutine {
        private final SaltyImage firstScene;
        private final int duration;
        private int ticks = 0;

        public FadeDrawer(final SaltyImage firstScene, final int duration) {
            super(DrawingPosition.LAST);
            this.firstScene = firstScene;
            this.duration = duration;
        }

        @Override
        public void draw(final SaltyGraphics g) {
            final AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - (float) ticks / duration);
            g.setComposite(ac);
            g.drawImage(firstScene, Vector2f.zero());
            ticks++;
            if (ticks >= duration) {
                SceneManager.getCurrentScene().removeDrawingRoutine(this);
            }
        }
    }
}
