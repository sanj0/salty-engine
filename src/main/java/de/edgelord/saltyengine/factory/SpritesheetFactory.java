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

package de.edgelord.saltyengine.factory;

import de.edgelord.saltyengine.effect.Spritesheet;
import de.edgelord.saltyengine.resource.Resource;

public class SpritesheetFactory extends Factory {

    public SpritesheetFactory(final Resource resource) {
        super(resource);
    }

    public SpritesheetFactory(final ImageFactory imageFactory) {
        super(imageFactory.getResource());
    }

    public Spritesheet getSpritesheet(final String relativePath, final int spriteWidth, final int spriteHeight) {

        return new Spritesheet(getResource().getImageResource(relativePath), spriteWidth, spriteHeight);
    }
}
