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

import java.awt.*;
import java.awt.event.MouseEvent;

public class UninstallButton extends Button {

    public UninstallButton(final Vector2f position) {
        super("Uninstall", position, 100, 35);

        setBackgroundColor(ColorUtil.CRIMSON_RED);
        setForegroundColor(ColorUtil.BLACK);
        setFont(getFont().deriveFont(Font.BOLD, 15f));
        setArc(7);
    }

    public UninstallButton() {
        this(new Vector2f(10, 10));
    }

    @Override
    public void onClick(final MouseEvent e) {
        if (Game.getHost().isFullscreen()) {
            Game.getHost().toggleFullscreen();
        }
        if (Game.getHost().showConfirmDialog("This will delete all local game files (including save files of game states!)" +
                " but not the game itself and exist the game. Proceed?")) {
            SaltySystem.writePrivilege = false;
            SaltySystem.defaultHiddenOuterResource.delete();
            SaltySystem.defaultOuterResource.delete();
            Game.quit();
        }
    }
}
