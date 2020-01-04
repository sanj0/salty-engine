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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.scene.Scene;

/**
 * The <code>GameListener</code> is an interface to
 * process a variety of events that the game is throwing.
 * <p>
 * A <code>GameListener</code> can be added to the game
 * via the {@link Game} class.
 */
public interface GameListener {

    /**
     * Calls when the Game exits.
     */
    void onClose();

    /**
     * Calls when the game starts.
     * This event is fired by the {@link GameStarter}
     * after a {@link Game#start()} method is called.
     */
    void onStart();

    /**
     * Calls when a new {@link Scene} is set
     * as the current one using
     * {@link de.edgelord.saltyengine.scene.SceneManager#setCurrentScene(Scene)}.
     *
     * @param scene the new active <code>Scene</code>
     */
    void onSceneStart(Scene scene);

    /**
     * Calls when a {@link GameObject} collides with another <code>GameObject</code>.
     *
     * @param subject the first collision partner
     * @param e the event of the collision, including the other collision partner
     */
    void onCollision(GameObject subject, CollisionEvent e);

    /**
     * Calls when the game has finished rendering,
     * which in this case means that all {@link GameObject}s and
     * {@link de.edgelord.saltyengine.gameobject.DrawingRoutine}s are
     * rendered, but the {@link de.edgelord.saltyengine.effect.light.LightSystem}
     * is still to be rendered. <p>
     * This method is called in the render process directly before the {@link de.edgelord.saltyengine.effect.light.LightSystem}
     * is being drawn.
     *
     * @param saltyGraphics the <code>Graphics</code> that the game is being rendered with
     */
    void onGameRenderFinish(SaltyGraphics saltyGraphics);

    /**
     * Calls when the Game has finished rendering,
     * with the {@link de.edgelord.saltyengine.effect.light.LightSystem}
     * included, but the UI still to be rendered after this event.
     *
     * @param saltyGraphics the graphics the game is being rendered with
     */
    void onPostLightRenderFinish(SaltyGraphics saltyGraphics);

    /**
     * Calls when the game has completely finished rendering,
     * which includes the {@link de.edgelord.saltyengine.effect.light.LightSystem}
     * and {@link de.edgelord.saltyengine.ui.UISystem}.
     * A default implementation would just return the image like so:
     * <pre>{@code
     * public SaltyImage onRenderFinish(SaltyImage image) {
     *     return image;
     * }
     * }</pre>
     *
     *
     * @param image the final image
     * @return the image that is actually being drawn to the screen
     */
    SaltyImage onRenderFinish(SaltyImage image);
}
