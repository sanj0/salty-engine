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

package de.edgelord.saltyengine.components.gfx.scene;

import de.edgelord.saltyengine.components.gfx.GFXComponent;
import de.edgelord.saltyengine.core.Game;

/**
 * A {@link GFXComponent} that is designed for the use with the {@link Game#getDefaultGFXController() default GFXController}.
 */
public abstract class SceneGFXComponent extends GFXComponent {

    /**
     * The constructor. Adds this <code>Component</code> to the {@link Game#getDefaultGFXController() default GFXController}.
     *
     * @param name the id-name
     */
    public SceneGFXComponent(String name) {
        super(Game.getDefaultGFXController(), name);
        Game.getDefaultGFXController().addGFX(this);
    }
}
