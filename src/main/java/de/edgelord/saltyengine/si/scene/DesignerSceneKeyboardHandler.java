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
import de.edgelord.saltyengine.input.KeyboardInputAdapter;
import de.edgelord.saltyengine.si.SJFormatKeys;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class DesignerSceneKeyboardHandler extends KeyboardInputAdapter {

    private final DesignerScene owner;

    public DesignerSceneKeyboardHandler(final DesignerScene owner) {
        this.owner = owner;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'n':
                final Map<String, Object> attributes = new HashMap<>(owner.getSelectedDefault());
                final Vector2f pos = new Vector2f(Input.getCursorPosition());
                attributes.put(SJFormatKeys.KEY_POSITION, pos);
                final GameObject gameObject = owner.getGameObjectParser().parseGameObject(attributes);
                gameObject.getTransform().positionByCentre(Input.getCursorPosition());
                owner.addGameObject(gameObject);
                break;
            case 'x':
                for (final GameObject g : owner.getSelectedGameObjects()) {
                    owner.removeGameObject(g);
                }
                owner.getSelectedGameObjects().clear();
                break;
            case '+':
                Game.getCamera().setScale(Game.getCamera().getScale() + .02f);
                break;
            case '-':
                Game.getCamera().setScale(Game.getCamera().getScale() - .02f);
                break;
            case 'r':
                Game.getCamera().setPosition(Vector2f.zero());
                Game.getCamera().setScale(1.0f);
        }
    }
}
