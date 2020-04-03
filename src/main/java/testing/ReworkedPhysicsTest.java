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

package testing;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.physics.PhysicsObject;
import de.edgelord.saltyengine.core.physics.World;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.SaltySystem;

public class ReworkedPhysicsTest extends Game {

    public static void main(String[] args) {
        init(GameConfig.config(1920, 1080, "Reworked Phsics Engine Test", 5));
        start();

        SceneManager.setCurrentScene(new Scene() {
            @Override
            public void onFixedTick() {
                World.tick(SaltySystem.fixedTickMillis);
            }
        });

        PhysicsObject object = new PhysicsObject(new EmptyGameObject(0, 0, 100, 100, "po"));
        World.add(object);
        SceneManager.getCurrentScene().addDrawingRoutine(new PhysicsObjectVisualizer(object));
    }

    static class PhysicsObjectVisualizer extends DrawingRoutine {

        private PhysicsObject physicsObject;

        public PhysicsObjectVisualizer(PhysicsObject physicsObject) {
            super(DrawingPosition.AFTER_GAMEOBJECTS);

            this.physicsObject = physicsObject;
        }

        @Override
        public void draw(SaltyGraphics saltyGraphics) {
            saltyGraphics.drawRect(physicsObject.getParent());
        }
    }
}
