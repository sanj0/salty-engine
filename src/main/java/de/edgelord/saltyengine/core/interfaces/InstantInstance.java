/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.core.interfaces;

/**
 * An interface to create an instant instance of {@link T}.
 * It provides instant instances with
 * <code>zero</code>, <code>max</code>, <code>min</code> and <code>random</code>
 * overload.
 */
public interface InstantInstance<T> {

    /**
     * Returns a new instance of {@link T} with a zero overload, so all necessary parameters
     * to instantiate are overloaded with zero but not <code>null</code>.
     *
     * @return a new instance of {@link T} with a zero-overload
     */
    T zero();

    /**
     * Returns a new instance of {@link T} with a max overload, so all necessary parameters
     * to instantiate are overloaded with the max value.
     *
     * @return a new instance of {@link T} with a max-overload
     */
    T max();

    /**
     * Returns a new instance of {@link T} with a min overload, so all necessary parameters
     * to instantiate are overloaded with the minimum value.
     *
     * @return a new instance of {@link T} with a min-overload
     */
    T min();

    /**
     * Returns a new instance of {@link T} with a random overload between the given bounds,
     * so all necessary parameters to instantiate are overloaded with random values.
     *
     * @return a new instance of {@link T} with a random-overload
     */
    T random(int min, int max);
}
