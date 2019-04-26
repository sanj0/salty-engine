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

package de.edgelord.saltyengine.example.tdexample;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TDTrack extends DrawingRoutine {

    private static List<Vector2f> points = new ArrayList<>();

    static {
        points.add(new Vector2f(0, 0));
        points.add(new Vector2f(500, 0));
        points.add(new Vector2f(500, 500));
    }

    public TDTrack() {
        super(DrawingPosition.BEFORE_GAMEOBJECTS);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(Color.BLACK);

        saltyGraphics.drawRect(0, 0, 500, 50);
        saltyGraphics.drawRect(500, 0, 50, 500);
    }

    /**
     * Gets {@link #points}.
     *
     * @return the value of {@link #points}
     */
    public static List<Vector2f> getPoints() {
        return points;
    }
}
