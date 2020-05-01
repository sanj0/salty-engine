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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.elements.Button;

import java.awt.event.MouseEvent;

public class UninstallButton extends Button {

    public UninstallButton(String text, Vector2f position, int width, int height) {
        super(text, position, width, height);
    }

    @Override
    public void onClick(MouseEvent e) {
        SaltySystem.writePrivilege = false;
        SaltySystem.defaultHiddenOuterResource.getFileResource(".").delete();
        SaltySystem.defaultOuterResource.getFileResource(".").delete();
        Game.quit();
    }
}
