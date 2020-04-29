/*
 * Copyright 2018 Malte Dostal
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

package de.edgelord.saltyengine.scene;

import de.edgelord.saltyengine.core.Game;

import java.lang.reflect.InvocationTargetException;

/**
 * This class provides static stuff for
 * managing the {@link #currentScene current active Scene}.
 * <p>
 * Apart from the obvious getting/setting,
 * to reload the current {@link Scene},
 * use {@link #reloadCurrentScene(Object...)}.
 * Constructor args can be passed into that method via vararg.
 */
public class SceneManager {

    /**
     * The <code>Scene</code> that is currently being rendered and
     * processed.
     */
    private static Scene currentScene = new EmptyScene();

    /**
     * Gets {@link #currentScene}.
     *
     * @return the value of {@link #currentScene}
     */
    public static Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Sets {@link #currentScene} and then
     * {@link Scene#initialize() initializes} it.
     *
     * @param scene the new active <code>Scene</code>
     */
    public static void setCurrentScene(Scene scene) {
        setCurrentScene(scene, true);
    }

    /**
     * Sets {@link #currentScene}.
     *
     * @param scene      the new value of {@link #currentScene}
     * @param initialize whether the <code>Scene</code> should be
     *                   {@link Scene#initialize() initialized} by this method or not.
     */
    public static void setCurrentScene(Scene scene, boolean initialize) {
        currentScene = scene;

        if (initialize) {
            scene.initialize();
        }
        Game.forEachGameListener(gameListener -> gameListener.onSceneStart(currentScene));
    }

    /**
     * Creates a new instance of the the {@link #currentScene current scene}
     * with the given args and sets it as teh current <code>Scene</code>,
     * effectively reloading it.
     *
     * @param args the arguments for the constrcutort to instantiate
     *             a new <code>Scene</code> of the type of the
     *             {@link #currentScene current one}
     * @throws NoSuchMethodException when there is no constructor
     *                               the given args
     */
    public static void reloadCurrentScene(Object... args) throws NoSuchMethodException {

        if (currentScene == null) {
            throw new IllegalStateException("cannot reload the current scene if it is null");
        }

        Class[] argTypes = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        try {
            setCurrentScene(getCurrentScene().getClass().getConstructor(argTypes).newInstance(args));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
