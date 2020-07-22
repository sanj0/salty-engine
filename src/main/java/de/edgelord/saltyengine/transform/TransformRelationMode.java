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

package de.edgelord.saltyengine.transform;

/**
 * An enum to describe how two or more {@link
 * Transform}s are positioned relatively to each
 * other. Basically there should be one
 * super-transform which every other Transforms
 * follow. An implementation of those modes can be
 * used/seen in
 */
public enum TransformRelationMode {

    /**
     * All {@link Transform}s related with this
     * should lie centre on centre
     */
    CENTRE,

    /**
     * The x position of the centre should be the
     * same for all {@link Transform}s
     */
    CENTRE_X,

    /**
     * The y position of the centre should be the
     * same for all {@link Transform}s
     */
    CENTRE_Y,

    /**
     * The left edge of all of the {@link
     * Transform}s should have the same x
     * position.
     */
    LEFT_EDGE,

    /**
     * The right edge of all of the {@link
     * Transform}s should have the same x
     * position.
     */
    RIGHT_EDGE,

    /**
     * The top edge of all of the {@link
     * Transform}s should have the same y
     * position.
     */
    TOP_EDGE,

    /**
     * The bottom edge of all of the {@link
     * Transform}s should have the same y
     * position.
     */
    BOTTOM_EDGE,

    /**
     * The top left corner of all of the {@link
     * Transform}s should match.
     */
    TOP_LEFT_CORNER,

    /**
     * The top right corner of all of the {@link
     * Transform}s should match.
     */
    TOP_RIGHT_CORNER,

    /**
     * The bottom left corner of all of the {@link
     * Transform}s should match.
     */
    BOTTOM_LEFT_CORNER,

    /**
     * The bottom right corner of all of the
     * {@link Transform}s should match.
     */
    BOTTOM_RIGHT_CORNER,
}
