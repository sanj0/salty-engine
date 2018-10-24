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

import java.lang.reflect.InvocationTargetException;
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
 * To reload the current {@link Scene}, use {@link #reloadCurrentScene(Object...)}.
 *
 * <p>
 * To remove a {@link Scene} from the collection, use {@link #removeScene(String)}
 */
public class SceneManager {

    private static Scene currentScene = new DummyScene();
    private static String currentSceneName = null;

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

    /**
     * Creates a new instance of the {@link Class} from the map {@link #scenes} whose key is the given String.
     * All arguments needed by one constructor of the scene may be given to this method in the right order, separated
     * with a comma. <br>
     * Example:
     * If you have a class named "MyScene" extending {@link Scene}, that has the following constructor:
     * <pre>
     * {@code
     * public MyScene(Long level, Integer difficulty, GameObject parent) {
     *     // [...]
     * }
     * }
     * </pre>
     * You would set this Scene to the current by calling the following two lines:
     * <pre>
     *
     * {@code
     * SceneManager.addScene("my scene", MyScene.class);
     * SceneManager.setCurrentScene("my scene", 13L, 2, new DummyGameObject(new Vector2f(1, 1)));
     * }
     * </pre>
     * This would set the current scene to a new instance of <code>MyScene.class</code> using the constructor with
     * the arguments <code>13L, 2, new DummyGameObject(new Vector2f(1, 1))</code>.
     * It is very important to write fully classified class names in the constructor, so,
     * <code>Integer</code> instead of <code>int</code> and <code>Long</code> instead of <code>long</code>.
     *
     * NOTE: the "throws" descriptions of this method are quoted from {@link java.lang.reflect.Constructor#newInstance(Object...)}
     *
     *
     * @param name the name of the class to instantiate and set to {@link #currentScene}
     * @param args the args for the desired constructor for the Scene
     *
     *
     * @throws IllegalAccessException    if the constructor
     *              is enforcing Java language access control and the underlying
     *              constructor is inaccessible.
     * @throws IllegalArgumentException  if the number of actual
     *              and formal parameters differ; if an unwrapping
     *              conversion for primitive arguments fails; or if,
     *              after possible unwrapping, a parameter value
     *              cannot be converted to the corresponding formal
     *              parameter type by a method invocation conversion; if
     *              this constructor pertains to an enum type.
     * @throws InstantiationException    if the class that declares the
     *              underlying constructor represents an abstract class.
     * @throws InvocationTargetException if the underlying constructor
     *              throws an exception.
     * @throws ExceptionInInitializerError if the initialization provoked
     *              by this method fails.
     */
    public static void setCurrentScene(String name, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class[] argTypes = new Class[args.length];

        for (int i = 0; i < argTypes.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        currentScene = scenes.getOrDefault(name, currentScene.getClass()).getConstructor(argTypes).newInstance(args);
        currentSceneName = name;
    }

    /**
     * Reloads the current {@link Scene} by calling {@link #setCurrentScene(String, Object...)}
     * with the name of the current active Scene.
     *
     * NOTE: the "throws" descriptions of this method are quoted from {@link java.lang.reflect.Constructor#newInstance(Object...)}
     *
     * @param args the args for the desired constructor to instantiate the Scene
     *
     * @throws IllegalAccessException    if the constructor
     *              is enforcing Java language access control and the underlying
     *              constructor is inaccessible.
     * @throws IllegalArgumentException  if the number of actual
     *              and formal parameters differ; if an unwrapping
     *              conversion for primitive arguments fails; or if,
     *              after possible unwrapping, a parameter value
     *              cannot be converted to the corresponding formal
     *              parameter type by a method invocation conversion; if
     *              this constructor pertains to an enum type.
     * @throws InstantiationException    if the class that declares the
     *              underlying constructor represents an abstract class.
     * @throws InvocationTargetException if the underlying constructor
     *              throws an exception.
     * @throws ExceptionInInitializerError if the initialization provoked
     *              by this method fails.
     */
    public static void reloadCurrentScene(Object... args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (currentSceneName == null) {
            System.err.println("Can't reload a scene before a scene was loaded!");
        } else {
            setCurrentScene(currentSceneName, args);
        }
    }

    public static void removeScene(String name) {
        scenes.remove(name);
    }
}
