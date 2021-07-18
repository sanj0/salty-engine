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

package de.edgelord.saltyengine.si.scene;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.si.SJFormatKeys;
import de.edgelord.saltyengine.si.SJGameObjectDeParser;
import de.edgelord.saltyengine.si.SJGameObjectParser;
import de.edgelord.saltyengine.si.SJSceneParser;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.DraggableSceneMouseInputHandler;
import de.edgelord.saltyengine.utils.ResizeCage;
import de.edgelord.sanjo.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static de.edgelord.saltyengine.si.SJSceneParser.parseAttribute;

public class DesignerScene extends Scene {
    private final Map<String, Map<String, Object>> defaults = new HashMap<>();
    private final Map<Character, String> hotkeys = new HashMap<>();
    private final SJGameObjectParser gameObjectParser;
    private final SJGameObjectDeParser deParser;
    private final File sceneFile;
    private List<GameObject> selectedGameObjects = new ArrayList<>();
    private Map<String, Object> selectedDefault = null;
    private ResizeCage resizeCage = null;

    public DesignerScene(final String configPath, final File scene, final SJGameObjectParser gameObjectParser, final SJGameObjectDeParser deParser) throws IOException {
        this.gameObjectParser = gameObjectParser;
        this.deParser = deParser;
        sceneFile = scene;

        loadExistingScene(scene);
        loadConfig(configPath);

        Input.addKeyboardInputHandler(new DesignerSceneKeyboardHandler(this));
        Input.addMouseInputHandler(new DesignerSceneMouseHandler(this));
        Input.addMouseInputHandler(new DraggableSceneMouseInputHandler());
    }

    private void loadConfig(final String configPath) throws IOException {
        final SJClass defaultsRoot = new SanjoParser().parse(SanjoFile.readLines(new InnerResource().getFileResource(configPath)));
        final Optional<SJClass> metaInf = defaultsRoot.getChild("meta-inf");
        final Optional<SJValue> defaultId = defaultsRoot.get(SJAddress.forString(">meta-inf?default-id"));
        metaInf.ifPresent(c -> defaultsRoot.getChildren().remove(c));
        for (final SJClass child : defaultsRoot.getChildren()) {
            final Map<String, Object> attributes = new HashMap<>();
            for (final SJValue value : child.getValues().values()) {
                if (value.getKey().equals("editor-hotkey")) {
                    hotkeys.put(value.string().charAt(0), child.getName());
                } else {
                    attributes.put(value.getKey(), parseAttribute(value));
                    attributes.put(SJSceneParser.ORIGINAL_VALUE_PREFIX + value.getKey(), value);
                }
            }
            attributes.put(SJFormatKeys.KEY_ID, child.getName());
            defaults.put(child.getName(), attributes);
        }
        defaultId.ifPresent(sjValue -> selectedDefault = defaults.get(sjValue.string()));
    }

    private void loadExistingScene(final File scene) throws IOException {
        if (scene.exists()) {
            final Scene loadedScene = new SJSceneParser(scene).parseScene(gameObjectParser);
            loadedScene.initialize();
            for (final GameObject g : loadedScene.getGameObjects()) {
                addGameObject(g);
            }
        } else {
            scene.createNewFile();
        }
    }

    @Override
    public void onFixedTick() {
        super.onFixedTick();
        if (Input.isInputLeft()) {
            Game.getCamera().moveX(-.02f);
        } else if (Input.isInputRight()) {
            Game.getCamera().moveY(.02f);
        }
        if (Input.isInputUp()) {
            Game.getCamera().moveY(-0.2f);
        } else if (Input.isInputDown()) {
            Game.getCamera().moveY(.02f);
        }
    }

    @Override
    public void initialize() {
        System.out.println("welcome to the salty engine level editor! Try \"objects\" for a list of available objects that can be placed using 'n'");
        CompletableFuture.runAsync(() -> {
            String input;
            final Scanner stdin = new Scanner(System.in);
            do {
                input = stdin.nextLine();
                final String[] components = input.split(" ");
                switch (components[0]) {
                    case "select":
                        selectedDefault = defaults.get(components[1]);
                        System.out.println("selected default " + components[1]);
                        break;
                    case "info":
                        System.out.println(selectedDefault.toString());
                        break;
                    case "set":
                        if (components.length != 3) {
                            System.err.println("set syntax: set key value");
                        } else if (!selectedGameObjects.isEmpty()) {
                            final GameObject go = selectedGameObjects.get(0);
                            removeGameObject(go);
                            final SJClass deparsedData = deParser.deparse(go);
                            deparsedData.addValue(components[1], components[2]);
                            final GameObject newGo = gameObjectParser.parseGameObject(SJSceneParser.readAttributes(deparsedData));
                            addGameObject(newGo);
                            selectedGameObjects.clear();
                            selectedGameObjects.add(newGo);
                        }
                        break;
                    case "write":
                        System.out.print("writing scene...");
                        final SJClass root = SJClass.defaultClass();
                        final SJClass sceneClass = new SJClass("scene");
                        sceneClass.addValue("gravity", getGravity());
                        sceneClass.addValue("camera-position", Game.getCamera().getX() + ", " + Game.getCamera().getY());
                        sceneClass.addValue("camera-scale", Game.getCamera().getScale());
                        root.addChild(sceneClass);
                        for (final GameObject g : getGameObjects()) {
                            if (g != resizeCage) {
                                final SJClass clazz = deParser.deparse(g);
                                if (!clazz.getValues().isEmpty()) {
                                    root.addChild(deParser.deparse(g));
                                }
                            }
                        }
                        if (components.length == 1) {
                            try {
                                Files.write(sceneFile.toPath(), Arrays.asList(root.write().split("\n")));
                            } catch (final IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                Files.write(new File(components[1]).toPath(), Arrays.asList(root.write().split("\n")));
                            } catch (final IOException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(" done!");
                        break;
                    case "list":
                        int i = 0;
                        for (final GameObject g : getGameObjects()) {
                            System.out.println(i + " := " + g.getTag() + " at " + SJGameObjectDeParser.deparseTransform(g.getTransform()));
                            i++;
                        }
                        break;
                    case "objects":
                        System.out.println("available objects (selected is marked):");
                        for (final String id : defaults.keySet()) {
                            System.out.print(defaults.get(id) == selectedDefault ? "*" : " ");
                            System.out.println("   " + id);
                        }
                        break;
                    case "remove":
                        if (components.length != 2) {
                            System.err.println("remove syntax: remove index");
                        } else {
                            removeGameObject(getGameObjects().get(Integer.parseInt(components[1])));
                        }
                }
            } while (true);
        });
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        super.draw(saltyGraphics);
        saltyGraphics.setTransform(Game.getCamera().getAffineTransform());
        saltyGraphics.setColor(ColorUtil.ORANGE_RED);
        final float[] dash1 = {2f, 0f, 2f};
        saltyGraphics.setStroke(new BasicStroke(1,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND,
                1.0f,
                dash1,
                2f));
        for (final GameObject g : selectedGameObjects) {
            saltyGraphics.outlineRect(g);
        }
    }

    /**
     * Gets {@link #defaults}.
     *
     * @return the value of {@link #defaults}
     */
    public Map<String, Map<String, Object>> getDefaults() {
        return defaults;
    }

    /**
     * Gets {@link #gameObjectParser}.
     *
     * @return the value of {@link #gameObjectParser}
     */
    public SJGameObjectParser getGameObjectParser() {
        return gameObjectParser;
    }

    /**
     * Gets {@link #deParser}.
     *
     * @return the value of {@link #deParser}
     */
    public SJGameObjectDeParser getDeParser() {
        return deParser;
    }

    /**
     * Gets {@link #selectedGameObjects}.
     *
     * @return the value of {@link #selectedGameObjects}
     */
    public List<GameObject> getSelectedGameObjects() {
        return selectedGameObjects;
    }

    /**
     * Sets {@link #selectedGameObjects}.
     *
     * @param selectedGameObjects the new value of {@link #selectedGameObjects}
     */
    public void setSelectedGameObjects(final List<GameObject> selectedGameObjects) {
        this.selectedGameObjects = selectedGameObjects;
    }

    /**
     * Gets {@link #selectedDefault}.
     *
     * @return the value of {@link #selectedDefault}
     */
    public Map<String, Object> getSelectedDefault() {
        return selectedDefault;
    }

    /**
     * Sets {@link #selectedDefault}.
     *
     * @param selectedDefault the new value of {@link #selectedDefault}
     */
    public void setSelectedDefault(final Map<String, Object> selectedDefault) {
        this.selectedDefault = selectedDefault;
    }

    /**
     * Gets {@link #resizeCage}.
     *
     * @return the value of {@link #resizeCage}
     */
    public ResizeCage getResizeCage() {
        return resizeCage;
    }

    /**
     * Sets {@link #resizeCage}.
     *
     * @param resizeCage the new value of {@link #resizeCage}
     */
    public void setResizeCage(final ResizeCage resizeCage) {
        this.resizeCage = resizeCage;
    }

    /**
     * Gets {@link #sceneFile}.
     *
     * @return the value of {@link #sceneFile}
     */
    public File getSceneFile() {
        return sceneFile;
    }

    /**
     * Gets {@link #hotkeys}.
     *
     * @return the value of {@link #hotkeys}
     */
    public Map<Character, String> getHotkeys() {
        return hotkeys;
    }
}
