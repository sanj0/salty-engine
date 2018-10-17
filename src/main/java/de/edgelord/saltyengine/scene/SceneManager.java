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

package de.edgelord.saltyengine.scene;

import testing.dummys.DummyScene;

import java.util.HashMap;

/**
 * This class manages all {@link Scene}s needed by a game. The collection of Scenes is
 * stored in the {@link HashMap} {@link #scenes}. Every {@link String} name in this map stands
 * for the {@link Class} object of a class extending {@link Scene}.
 * To add a {@link Scene} to the collection, call {@link #addScene(String, Scene)} or {@link #addScene(String, Class)}.
 * Example usage:
 * <p>
 * {@code SceneManager.addScene("startMenu", new StartMenu());} <br>
 * {@code SceneManager.addScene("starMenu", StarMenu.class)}
 *
 * <p>
 * <p>
 * When ever you call {@code SceneManager.setCurrentScene("starMenu");} a new instance of {@code StartMenu} will be created
 * and set to {@link #currentScene}
 *
 * <p>
 * To reload the current {@link Scene}, use {@link #reloadCurrentScene()}.
 *
 * <p>
 * To remove a {@link Scene} from the collection, use {@link #removeScene(String)}
 *
 * <p>
 * NOTE: All {@link Scene}s in the collection {@link #scenes} may have an empty constructor.
 * Otherwise, an {@link InstantiationException} will be thrown. Also, any {@link Scene} in the collection may
 * have an empty and public constructor, otherwise an {@link IllegalAccessException} will be thrown!
 */
public class SceneManager {

    private static Scene currentScene = new DummyScene();

    private static HashMap<String, Class<? extends Scene>> scenes = new HashMap<>();

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static void addScene(String name, Class<? extends Scene> scene) {
        scenes.put(name, scene);
    }

    public static void addScene(String name, Scene scene) {
        addScene(name, scene.getClass());
    }

    public static void setCurrentScene(String name) throws IllegalAccessException, InstantiationException {
        SceneManager.currentScene = scenes.getOrDefault(name, currentScene.getClass()).newInstance();
    }

    public static void reloadCurrentScene() throws IllegalAccessException, InstantiationException {
        SceneManager.currentScene = currentScene.getClass().newInstance();
    }

    public static void removeScene(String name) {
        scenes.remove(name);
    }
}
