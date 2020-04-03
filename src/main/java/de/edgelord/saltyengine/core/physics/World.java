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

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The <code>World</code> is the virtual scenery in which
 * the physics-part of the engine takes place.
 *
 */
public class World {

    private static List<PhysicsObject> objects = Collections.synchronizedList(new ArrayList<>());

    public static void tick(long dt) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).tick(dt);
        }
    }

    public static int size() {
        return objects.size();
    }

    public static boolean isEmpty() {
        return objects.isEmpty();
    }

    public static boolean contains(Object o) {
        return objects.contains(o);
    }

    public static boolean add(PhysicsObject physicsObject) {
        return objects.add(physicsObject);
    }

    public static boolean remove(Object o) {
        return objects.remove(o);
    }

    public static void clear() {
        objects.clear();
    }

    public static PhysicsObject get(int index) {
        return objects.get(index);
    }

    public static PhysicsObject remove(int index) {
        return objects.remove(index);
    }

    public static boolean removeIf(Predicate<? super PhysicsObject> filter) {
        return objects.removeIf(filter);
    }

    public static void forEach(Consumer<? super PhysicsObject> action) {
        objects.forEach(action);
    }

    /**
     * Gets {@link #objects}.
     *
     * @return the value of {@link #objects}
     */
    public static List<PhysicsObject> getObjects() {
        return objects;
    }

    /**
     * Sets {@link #objects}.
     *
     * @param objects the new value of {@link #objects}
     */
    public static void setObjects(List<PhysicsObject> objects) {
        World.objects = objects;
    }
}
