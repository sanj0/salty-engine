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

import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.List;

import static java.lang.Math.toDegrees;

/**
 * A set of static methods to make general things easier.
 */
public class GeneralUtil {

    /**
     * The <code>Random</code> instance used for all utils that need one.
     */
    private static final SecureRandom random = new SecureRandom();

    /**
     * Returns the given value inside the given range.
     * So for the given value {@code v} and the range {@code min} to {@code max},
     * this method would return <p>
     * {@code v} if it lies between {@code min} ... {@code max} <br>
     * {@code min} if {@code v} is smaller than {@code min} <br>
     * and {@code max} if {@code v} is greater than {@code max}
     *
     * @param value a value
     * @param min   the smaller end of the range
     * @param max   the greater end of the range
     * @return the given value clamped to the given range
     */
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Returns the angle between the two given points and the origin (0 | 0).
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the angle between the given two points as degrees
     */
    public static float getAngle(Vector2f p1, Vector2f p2) {
        double arc = Math.atan2(p2.getY() - (p1.getY()), p2.getX() - (p1.getX()));
        return (float) toDegrees(arc) + 90f;
    }

    /**
     * Generates a random int between the given two.
     *
     * @param min the smallest number possible
     * @param max the biggest number possible
     * @return a new random int between the given bounds
     */
    public static int randomInt(float min, float max) {
        int r = random.nextInt((Math.round(max) - Math.round(min)) + 1);
        return Math.round(r + min);
    }

    /**
     * Returns a random object from the given list by requesting the next int from {@link #random} with
     * {@link List#size()} as the bound and returning the object with this index.
     *
     * @param list the {@link List} from which to return as random object
     * @return a random object from the given list
     */
    public static Object randomObjectFromList(List list) {
        return list.get(random.nextInt(list.size()));
    }

    /**
     * Returns a random {@link Dimensions}. The given floats are rounded to integers.
     *
     * @param minWidth  the min width
     * @param maxWidth  the max width
     * @param minHeight the min height
     * @param maxHeight the max height
     * @return a new random {@link Dimensions}.
     */
    public static Dimensions randomDimensions(float minWidth, float maxWidth, float minHeight, float maxHeight) {
        return new Dimensions(randomInt(minWidth, maxWidth), randomInt(minHeight, maxHeight));
    }

    /**
     * Returns a random {@link Vector2f}. The given floats are rounded to integers.
     *
     * @param minX the min x
     * @param maxX the max x
     * @param minY the min y
     * @param maxY the max y
     * @return a new random {@link Vector2f}.
     */
    public static Vector2f randomCoordinates(float minX, float maxX, float minY, float maxY) {
        return new Vector2f(randomInt(minX, maxX), randomInt(minY, maxY));
    }

    /**
     * @return the ip-address of this machine.
     * @throws UnknownHostException if the local host name could not
     *                              be resolved into an address.
     * @see InetAddress#getLocalHost()
     */
    public static String getIP() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
