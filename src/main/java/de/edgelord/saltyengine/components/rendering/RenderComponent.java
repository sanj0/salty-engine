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

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public abstract class RenderComponent extends Component {

    public RenderComponent(ComponentContainer parent, String name, String tag) {
        super(parent, name, tag);
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
