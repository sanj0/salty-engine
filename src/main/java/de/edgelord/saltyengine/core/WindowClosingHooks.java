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

package de.edgelord.saltyengine.core;

import java.util.ArrayList;
import java.util.List;

public class WindowClosingHooks {

    private static final List<Runnable> hooks = new ArrayList<>();

    public static void runHooks() {
        hooks.forEach(Runnable::run);
    }

    /**
     * Adds a true shutdown hook which is executed when the JVM shuts down.
     *
     * @param runnable what to run when the JVM is shutting down
     */
    public static void addShutdownHook(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }

    public static int size() {
        return hooks.size();
    }

    public static boolean isEmpty() {
        return hooks.isEmpty();
    }

    public static boolean contains(Object o) {
        return hooks.contains(o);
    }

    public static boolean add(Runnable runnable) {
        return hooks.add(runnable);
    }

    public static boolean remove(Object o) {
        return hooks.remove(o);
    }

    public static void clear() {
        hooks.clear();
    }

    public static Runnable get(int index) {
        return hooks.get(index);
    }

    public static void add(int index, Runnable element) {
        hooks.add(index, element);
    }

    public static Runnable remove(int index) {
        return hooks.remove(index);
    }
}
