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

package de.edgelord.saltyengine.core.interfaces;

/**
 * An interface to create an instant instance of {@link T}. It provides instant
 * instances with
 * <code>zero</code>, <code>max</code>,
 * <code>min</code> and <code>random</code>
 * overload.
 */
public interface InstantInstance<T> {

    /**
     * Returns a new instance of {@link T} with a zero overload, so all
     * necessary parameters to instantiate are overloaded with zero but not
     * <code>null</code>.
     *
     * @return a new instance of {@link T} with a zero-overload
     */
    T zero();

    /**
     * Returns a new instance of {@link T} with a max overload, so all necessary
     * parameters to instantiate are overloaded with the max value.
     *
     * @return a new instance of {@link T} with a max-overload
     */
    T max();

    /**
     * Returns a new instance of {@link T} with a min overload, so all necessary
     * parameters to instantiate are overloaded with the minimum value.
     *
     * @return a new instance of {@link T} with a min-overload
     */
    T min();

    /**
     * Returns a new instance of {@link T} with a random overload between the
     * given bounds, so all necessary parameters to instantiate are overloaded
     * with random values.
     *
     * @param max the maximum of the random number
     * @param min the minimum if the random number
     *
     * @return a new instance of {@link T} with a random-overload
     */
    T random(int min, int max);
}
