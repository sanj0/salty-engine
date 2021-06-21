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

package de.edgelord.saltyengine.si;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.KeyboardInputAdapter;
import de.edgelord.saltyengine.input.MouseInputAdapter;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.DraggableSceneMouseInputHandler;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;
import de.edgelord.sanjo.SanjoFile;
import de.edgelord.sanjo.SanjoParser;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static de.edgelord.saltyengine.si.SJSceneParser.parseAttribute;

public class DesignerScene extends Scene {
    private final Map<String, Map<String, Object>> defaults = new HashMap<>();
    private final SJGameObjectParser gameObjectParser;
    private final SJGameObjectDeParser deParser;
    private List<GameObject> selectedGameObjects = new ArrayList<>();
    private Map<String, Object> selectedDefault = null;

    public DesignerScene(final String configPath, final File scene, final SJGameObjectParser gameObjectParser, final SJGameObjectDeParser deParser) throws IOException {
        this.gameObjectParser = gameObjectParser;
        this.deParser = deParser;
        if (scene.exists()) {
            getGameObjects().addAll(new SJSceneParser(scene).parseScene(gameObjectParser).getGameObjects());
        } else {
            scene.createNewFile();
        }

        final SJClass defaultsRoot = new SanjoParser().parse(SanjoFile.readLines(new InnerResource().getFileResource(configPath)));
        for (final SJClass child : defaultsRoot.getChildren()) {
            final Map<String, Object> attributes = new HashMap<>();
            for (final SJValue value : child.getValues().values()) {
                attributes.put(value.getKey(), parseAttribute(value));
            }
            attributes.put(SJFormatKeys.KEY_ID, child.getName());
            defaults.put(child.getName(), attributes);
        }

        Input.addKeyboardInputHandler(new KeyboardInputAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                final float sizeDelta = 2;
                switch (e.getKeyChar()) {
                    case 'n':
                        final Map<String, Object> attributes = new HashMap<>();
                        attributes.putAll(selectedDefault);
                        final Vector2f pos = attributes.containsKey(SJFormatKeys.KEY_SIZE) ?
                                Input.getCursorPosition().subtracted(((Dimensions) attributes.get(SJFormatKeys.KEY_SIZE)).toVector2f().divided(new Vector2f(2, 2))) : new Vector2f(Input.getCursorPosition());
                        attributes.put(SJFormatKeys.KEY_POSITION, pos);
                        final GameObject gameObject = gameObjectParser.parseGameObject(attributes);
                        addGameObject(gameObject);
                        System.out.println("created new " + gameObject.getTag() + " at " + gameObject.getPosition());
                        break;
                    case 'x':
                        for (final GameObject g : selectedGameObjects) {
                            removeGameObject(g);
                        }
                        selectedGameObjects.clear();
                        break;
                    case '+':
                        if (e.isShiftDown()) {
                            selectedGameObjects.forEach(g -> {
                                g.setHeight(g.getHeight() + sizeDelta);
                                g.setY(g.getY() - sizeDelta / 2f);
                            });
                        } else {
                            selectedGameObjects.forEach(g -> {
                                g.setWidth(g.getWidth() + sizeDelta);
                                g.setX(g.getX() - sizeDelta / 2f);
                            });
                        }
                        break;
                    case '-':
                        if (e.isShiftDown()) {
                            selectedGameObjects.forEach(g -> {
                                g.setHeight(g.getHeight() - sizeDelta);
                                g.setY(g.getY() + sizeDelta / 2f);
                            });
                        } else {
                            selectedGameObjects.forEach(g -> {
                                g.setWidth(g.getWidth() - sizeDelta);
                                g.setX(g.getX() + sizeDelta / 2f);
                            });
                        }
                        break;
                }
            }
        });

        Input.addMouseInputHandler(new MouseInputAdapter() {
            Vector2f dragStart = null;
            @Override
            public void mouseDragged(MouseEvent e) {
                for (final GameObject g : selectedGameObjects) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        final Vector2f mousePos = Input.getCursorPosition();
                        g.getPosition().add(mousePos.subtracted(dragStart));
                        dragStart = mousePos;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    dragStart = Input.getCursorPosition();
                    if (Input.getKeyboardInput().isControl()) {
                        for (final GameObject g : getGameObjects()) {
                            if (g.getTransform().contains(Input.getCursor())) {
                                if (selectedGameObjects.contains(g)) {
                                    selectedGameObjects.remove(g);
                                } else {
                                    selectedGameObjects.add(g);
                                }
                                break;
                            }
                        }
                    } else {
                        selectedGameObjects.clear();
                        for (final GameObject g : getGameObjects()) {
                            if (g.getTransform().contains(Input.getCursor())) {
                                if (selectedGameObjects.contains(g)) {
                                    selectedGameObjects.remove(g);
                                } else {
                                    selectedGameObjects.add(g);
                                }
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });
        Input.addMouseInputHandler(new DraggableSceneMouseInputHandler());
    }

    @Override
    public void initialize() {
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
                    case "write":
                        if (components.length == 2) {
                            final SJClass root = SJClass.defaultClass();
                            for (final GameObject g : getGameObjects()) {
                                root.addChild(deParser.deparse(g));
                            }
                            System.out.println(root.write());
                        }
                        break;
                }
            } while (true);
        });
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        super.draw(saltyGraphics);
        saltyGraphics.setTransform(Game.getCamera().getAffineTransform());
        saltyGraphics.setColor(ColorUtil.ORANGE_RED);
        final float[] dash1 = { 2f, 0f, 2f };
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
}
