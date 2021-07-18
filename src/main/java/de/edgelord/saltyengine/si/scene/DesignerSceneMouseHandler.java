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
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.MouseInputAdapter;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.ResizeCage;

import java.awt.event.MouseEvent;
import java.util.List;

public class DesignerSceneMouseHandler extends MouseInputAdapter {
    private Vector2f dragStart = null;
    private boolean draggingObject = false;
    private final DesignerScene owner;

    public DesignerSceneMouseHandler(final DesignerScene owner) {
        this.owner = owner;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        final ResizeCage resizeCage = owner.getResizeCage();
        if (resizeCage != null) {
            if (!draggingObject && resizeCage.clicked(Input.getCursor())) {
                return;
            }
        }
        draggingObject = true;
        for (final GameObject g : owner.getSelectedGameObjects()) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                final Vector2f mousePos = Input.getCursorPosition();
                g.getPosition().add(mousePos.subtracted(dragStart));
                resizeCage.update(g.getTransform());
                dragStart = mousePos;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final ResizeCage resizeCage = owner.getResizeCage();
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (resizeCage != null) {
                if (!draggingObject && resizeCage.clicked(Input.getCursor())) {
                    return;
                }
            }
            dragStart = Input.getCursorPosition();
            if (Input.getKeyboardInput().isControl()) {
                for (final GameObject g : owner.getGameObjects()) {
                    if (g.getTransform().contains(Input.getCursor())) {
                        if (owner.getSelectedGameObjects().contains(g)) {
                            owner.getSelectedGameObjects().remove(g);
                            if (resizeCage != null) {
                                owner.removeGameObject(resizeCage);
                                owner.setResizeCage(null);
                            }
                        } else {
                            owner.getSelectedGameObjects().add(g);
                        }
                        break;
                    }
                }
            } else {
                owner.getSelectedGameObjects().clear();
                if (resizeCage != null) {
                    owner.removeGameObject(resizeCage);
                    owner.setResizeCage(null);
                }
                final List<GameObject> gos = owner.getGameObjects();
                for (int i = gos.size() - 1; i > -1; i--) {
                    final GameObject g = gos.get(i);
                    if (g.getTransform().contains(Input.getCursor())) {
                        if (!owner.getSelectedGameObjects().remove(g)) {
                            owner.getSelectedGameObjects().add(g);
                            owner.setResizeCage(new ResizeCage(g, 20 / Game.getCamera().getScale(), ColorUtil.RED));
                            owner.addGameObject(owner.getResizeCage());
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        draggingObject = false;
    }
}
