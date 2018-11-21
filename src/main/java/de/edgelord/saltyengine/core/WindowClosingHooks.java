/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.core;

import java.util.ArrayList;
import java.util.List;

public class WindowClosingHooks {

    private static List<Runnable> hooks = new ArrayList<>();

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
