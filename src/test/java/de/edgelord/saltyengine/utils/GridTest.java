/*
 * Copyright 2019 Malte Dostal
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

import de.edgelord.saltyengine.transform.Transform;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    private Grid grid = new Grid(10, 10);

    @Test
    public void testTransform() {
        Transform testTransform = grid.getTransform(3, 6, 2, 5);

        assertEquals(new Transform(30, 60, 20, 50), testTransform);
    }

    @Test
    public void testZeroTransform() {
        Transform testTransform = grid.getTransform(0, 0, 0, 0);

        assertEquals(new Transform(0, 0, 0, 0), testTransform);
    }

    @Test
    public void testNegativeTransform() {
        Transform testTransform = grid.getTransform(-3, -6, -2, -5);

        assertEquals(new Transform(-30, -60, -20, -50), testTransform);
    }

    @Test
    public void testTileTransform() {
        Transform testTile = grid.getTile(3, 6);

        assertEquals(new Transform(30, 60, 10, 10), testTile);
    }
}
