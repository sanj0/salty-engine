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

import java.util.Random;

/**
 * A set of static method to make general thing easier.
 */
public class GeneralUtil {

    private static Random random = new Random();

    /**
     * Generates a random int between the given two.
     *
     * @param min the smallest number possible
     * @param max the biggest number possible
     * @return a new random int between the given bounds
     */
    public static int randomInt(float min, float max) {
        int r = random.nextInt(Math.round(max) - Math.round(min));
        return Math.round(r + min);
    }
}
