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

package de.edgelord.saltyengine.core.physics;

import de.edgelord.saltyengine.core.interfaces.TransformedObject;

/**
 * A <code>PhysicsObject</code> is an object
 * within the virtual {@link World world}, that
 * is effected by other <code>PhysicsObject</code>s
 * within the <code>World</code>, effects other
 * <code>PhysicsObject</code>s in the <code>World</code>
 * and is effected by the <code>World</code> itself.
 */
public class PhysicsObject {

    private TransformedObject parent;
    private float mass;
    private MotionState motionState;
    private float surfaceFriction;
}
