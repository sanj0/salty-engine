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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * Some static methods to place a {@link
 * de.edgelord.saltyengine.transform.Transform} or
 * {@link de.edgelord.saltyengine.core.interfaces.TransformedObject}
 * with either a decimal number from 0 to 1 or
 * with a percentage.
 * <p>
 * Example usage:
 *
 * <code>GameObject myGameObject = new
 * EmptyGameObject(Positions.forDecimal(0.5f,
 * 0.75f), Dimensions.one(), "myGO");</code>
 */
public class Positions {

    /**
     * Returns the x position in user-space for
     * the given decimal. 0f would be the position
     * 0 in user-space as well and 1f would be the
     * right edge of the game-width specified by
     * {@link Game#getGameWidth()}.
     *
     * @param decimalPos the position as a decimal
     *                   number
     *
     * @return the true position in user-space
     */
    public static float xForDecimal(final float decimalPos) {
        return Game.getGameWidth() * decimalPos;
    }

    /**
     * Returns the y position in user-space for
     * the given decimal. 0f would be the position
     * 0 in user-space as well and 1f would be the
     * right edge of the game-height specified by
     * {@link Game#getGameHeight()} ()}.
     *
     * @param decimalPos the position as a decimal
     *                   number
     *
     * @return the true position in user-space
     */
    public static float yForDecimal(final float decimalPos) {
        return Game.getGameHeight() * decimalPos;
    }

    /**
     * Returns a new {@link Vector2f} that
     * represents the given decimal position in
     * coordinates in user-space.
     *
     * @param xDecimal the x position as a decimal
     *                 number
     * @param yDecimal the y position as a decimal
     *                 number
     *
     * @return a new {@link Vector2f} representing
     * the given decimal position in user-space
     * @see #xForDecimal(float)
     * @see #yForDecimal(float)
     */
    public static Vector2f forDecimal(final float xDecimal, final float yDecimal) {
        return new Vector2f(xForDecimal(xDecimal), yForDecimal(yDecimal));
    }

    /**
     * Returns the x position in user-space for
     * the given percentage.
     *
     * @param xPercentage the percentage of the x
     *                    position
     *
     * @return the true x position in user-space
     * @see #xForDecimal(float)
     */
    public static float xForPercentage(final float xPercentage) {
        return xForDecimal(xPercentage / 100f);
    }

    /**
     * Returns the y position in user-space for
     * the given percentage.
     *
     * @param yPercentage the percentage of the x
     *                    position
     *
     * @return the true y position in user-space
     * @see #xForDecimal(float)
     */
    public static float yForPercentage(final float yPercentage) {
        return xForDecimal(yPercentage / 100f);
    }

    /**
     * Returns a new {@link Vector2f} that
     * represents the given percentage-position in
     * coordinates in user-space.
     *
     * @param xPercentage the x position as
     *                    percentage
     * @param yPercentage the y position as
     *                    percentage
     *
     * @return a new {@link Vector2f} representing
     * the given percentage-position in
     * user-space
     * @see #xForPercentage(float)
     * @see #yForPercentage(float)
     */
    public static Vector2f forPercentage(final float xPercentage, final float yPercentage) {
        return new Vector2f(xForPercentage(xPercentage), yForPercentage(yPercentage));
    }
}
