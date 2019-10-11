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

package de.edgelord.saltyengine.utils.globaldraw;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.scene.SceneManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalDraw extends DrawingRoutine {

    private static GlobalDraw theInstance = null;

    private static List<GlobalDrawJob> drawJobs = Collections.synchronizedList(new ArrayList<>());

    public GlobalDraw() {
        super(DrawingPosition.LAST);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        for (GlobalDrawJob drawJob : drawJobs) {
            drawJob.draw(saltyGraphics);
        }
    }

    /**
     *
     * @return <code>true</code> if {@link #theInstance the instance} was already made and <code>false</code> if not
     */
    public static boolean addToCurrentScene() {
        boolean returnState;

        if (theInstance == null) {
            theInstance = new GlobalDraw();
            returnState = false;
        } else {
            returnState = true;
        }

        SceneManager.getCurrentScene().addDrawingRoutine(theInstance);

        return returnState;
    }
}
