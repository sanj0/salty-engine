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
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.core.physics.PhysicsBody;
import de.edgelord.saltyengine.core.physics.World;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Test the reworked physics engine away from
 * any real use case and out of the system.
 */
public class ReworkedPhysicsTest extends Game {

    public static void main(String[] args) {
        // initializes the game window, name and fixed ticks
        init(GameConfig.config(1920, 1080, "Reworked Physics Engine Test", 5));
        // starts the game without fps cap
        start();

        // creates a new PhysicsBody with a new EmptyGameObject as its parent
        PhysicsBody object = new PhysicsBody(new EmptyGameObject(0, 0, 100, 100, "physics-object"));
        // adds the created PhysicsBody to the World simulation
        World.add(object);
        // Creates a new Force object with a unit vector that points upwards
        // and an acceleration of 9.18, which should therefore completely counter
        // the default gravity, which points straight down and therefore in the
        // exact opposite direction, yet has the same acceleration.
        Force force = new Force(new Vector2f(0, 0), 0);
        // Adds the created Force to the PhysicsBody's MotionState
        object.getMotionState().add(force);

        // Sets the current scene to an anonymous
        // implementation that ticks the World instead of doing
        // GameObject updates and all the other
        SceneManager.setCurrentScene(new Scene() {
            @Override
            public void onFixedTick() {
                // ticks the physics World and therefore updates
                // all PhysicsBody within it.
                World.tick(SaltySystem.fixedTickMillis);
                //force.setDirection(Input.inputVector());
                force.setAcceleration(5, 2);
            }
        });

        // Adds a new PhysicsBodyVisualizer to the current Scene that visualizes the created PhysicsBody
        // as a gray box in user space
        SceneManager.getCurrentScene().addDrawingRoutine(new PhysicsBodyVisualizer(object));

        // !FIXME
        // Adds an anonymous implementation of a MouseInputHandler
        // to the corresponding list within the Input class
        // that toggles the gravity of the World on every mouseClicked event
        Input.addMouseInputHandler(new MouseInputHandler() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // toggles the gravity of the World
                World.setGravityEnabled(!World.isGravityEnabled());
            }

            @Override
            public void mouseExitedScreen(MouseEvent e) {
            }

            @Override
            public void mouseEnteredScreen(MouseEvent e) {
            }

            @Override
            public void mouseWheelMoved(MouseEvent e) {

            }
        });
    }

    /**
     * An inner class that implements DrawingRoutine
     * and whose only purpose is to visualize a given
     * {@link PhysicsBody} as a gray box.
     */
    static class PhysicsBodyVisualizer extends DrawingRoutine {

        /**
         * The {@link PhysicsBody} to visualize
         */
        private PhysicsBody physicsBody;

        /**
         * The constructor.
         *
         * @param physicsBody the {@link #physicsBody} to visualize
         */
        public PhysicsBodyVisualizer(PhysicsBody physicsBody) {
            super(DrawingPosition.AFTER_GAMEOBJECTS);

            this.physicsBody = physicsBody;
        }

        /**
         * Sets the color of the graphics to {@link Color#DARK_GRAY}
         * and draws a rectangle described by the parent of the
         * {@link #physicsBody}.
         *
         * @param saltyGraphics the graphics to draw to
         * @see PhysicsBody#getParent()
         * @see SaltyGraphics#setColor(Color)
         */
        @Override
        public void draw(SaltyGraphics saltyGraphics) {
            saltyGraphics.setColor(Color.DARK_GRAY);
            saltyGraphics.drawRect(physicsBody.getParent());
        }
    }
}
