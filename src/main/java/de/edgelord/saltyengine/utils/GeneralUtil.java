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

import de.edgelord.saltyengine.effect.geom.TriangleShape;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;

import java.awt.*;
import java.awt.geom.Area;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

/**
 * A set of static method to make general thing easier.
 */
public class GeneralUtil {

    /**
     * The <code>Random</code> instance used for all utils that need one.
     */
    private static Random random = new Random();

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
     * Returns a random {@link Coordinates2f}. The given floats are rounded to integers.
     *
     * @param minX the min x
     * @param maxX the max x
     * @param minY the min y
     * @param maxY the max y
     * @return a new random {@link Coordinates2f}.
     */
    public static Coordinates2f randomCoordinates(float minX, float maxX, float minY, float maxY) {
        return new Coordinates2f(randomInt(minX, maxX), randomInt(minY, maxY));
    }

    public static boolean checkPolygonIntersection(Polygon a, Polygon b) {
        Area areaA = new Area(a);
        Area areaB = new Area(b);

        areaA.intersect(areaB);

        return !areaA.isEmpty();
    }

    public static boolean checkTriangleIntersection(TriangleShape a, TriangleShape b) {
        for (int x = 0; x < 2; x++) {
            TriangleShape triangle = (x == 0) ? a : b;

            for (int i1 = 0; i1 < 3; i1++) {
                int i2 = (i1 + 1) % 3;
                Coordinates2f p1 = triangle.getPoint(i1);
                Coordinates2f p2 = triangle.getPoint(i2);
                Coordinates2f normal = new Coordinates2f(p2.getY() - p1.getY(), p1.getX() - p2.getX());
                double minA = Double.POSITIVE_INFINITY;
                double maxA = Double.NEGATIVE_INFINITY;
                for (Coordinates2f p : a.getPoints()) {
                    double projected = normal.getX() * p.getX() + normal.getY() * p.getY();
                    if (projected < minA) {
                        minA = projected;
                    }
                    if (projected > maxA) {
                        maxA = projected;
                    }
                }
                double minB = Double.POSITIVE_INFINITY;
                double maxB = Double.NEGATIVE_INFINITY;
                for (Coordinates2f p : b.getPoints()) {
                    double projected = normal.getX() * p.getX() + normal.getY() * p.getY();
                    if (projected < minB) {
                        minB = projected;
                    }
                    if (projected > maxB) {
                        maxB = projected;
                    }
                }
                if (maxA < minB || maxB < minA)
                    return false;
            }
        }
        return true;
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
