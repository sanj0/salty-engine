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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.factory.FontFactory;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.io.IOException;

public class SaltySystem {

    public static String versionTag = "0.14.10-SNAPSHOT";
    public static String version = "0.14.10";
    public static VersionMode versionMode = VersionMode.SNAPSHOT;

    public static long fixedTickMillis = 1;

    public static InnerResource defaultResource = new InnerResource();
    public static OuterResource defaultHiddenOuterResource = new OuterResource(true);
    public static OuterResource defaultOuterResource = new OuterResource(false);
    public static ImageFactory defaultImageFactory = new ImageFactory(defaultResource);
    public static FontFactory defaultFontFactory = new FontFactory(defaultResource);

    public static Font defaultFont;

    static {
        try {
            defaultFont = defaultFontFactory.getFont("res/fonts/OpenSans-Regular.ttf", 10f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the {@link Font} {@link #defaultFont} to all {@link UIElement}s in the
     * current {@link de.edgelord.saltyengine.scene.Scene}s {@link de.edgelord.saltyengine.ui.UISystem}
     */
    public static void updateDefaultFontGlobally() {
        updateFontGlobally(defaultFont);
    }

    public static void updateFontGlobally(Font font) {
        for (UIElement uiElement : SceneManager.getCurrentScene().getUI().getElements()) {
            uiElement.setFont(font);
        }
    }

    public enum VersionMode {
        SNAPSHOT,
        ALPHA,
        BETA,
        RELEASE
    }
}
