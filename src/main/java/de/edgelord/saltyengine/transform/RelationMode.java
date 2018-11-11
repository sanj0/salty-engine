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

package de.edgelord.saltyengine.transform;

/**
 * An enum to describe how two or more {@link Transform}s are positioned relatively to each other.
 * Basically there should be one super-transform which every other Transforms follow.
 * An implementation of those modes can be used/seen in
 */
public enum RelationMode {

    /**
     * All {@link Transform}s related with this should lie centre on centre
     */
    CENTRE,

    /**
     * The x position of the centre should be the same for all {@link Transform}s
     */
    CENTRE_X,

    /**
     * The y position of the centre should be the same for all {@link Transform}s
     */
    CENTRE_Y,

    /**
     * The left edge of all of the {@link Transform}s should have the same x position.
     */
    LEFT_EDGE,

    /**
     * The right edge of all of the {@link Transform}s should have the same x position.
     */
    RIGHT_EDGE,

    /**
     * The top edge of all of the {@link Transform}s should have the same y position.
     */
    TOP_EDGE,

    /**
     * The bottom edge of all of the {@link Transform}s should have the same y position.
     */
    BOTTOM_EDGE,

    /**
     * The top left corner of all of the {@link Transform}s should match.
     */
    TOP_LEFT_CORNER,

    /**
     * The top right corner of all of the {@link Transform}s should match.
     */
    TOP_RIGHT_CORNER,

    /**
     * The bottom left corner of all of the {@link Transform}s should match.
     */
    BOTTOM_LEFT_CORNER,

    /**
     * The bottom right corner of all of the {@link Transform}s should match.
     */
    BOTTOM_RIGHT_CORNER,
}
