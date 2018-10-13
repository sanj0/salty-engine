package de.edgelord.saltyengine.scene;

import testing.dummys.DummyScene;

import java.util.HashMap;

/**
 * This class manages all {@link Scene}s needed by a game. The collection of Scenes is
 * stored in the {@link HashMap} {@link #scenes}. Every {@link String} name in this map stands
 * for the {@link Class} object of a class extending {@link Scene}.
 * To add a {@link Scene} to the collection, call {@link #addScene(String, Scene)} or {@link #addScene(String, Class)}.
 * Example usage:
 *
 * {@code SceneManager.addScene("startMenu", new StartMenu());}
 * {@code SceneManager.addScene("starMenu", StarMenu.class)}
 *
 * <p>
 *
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
