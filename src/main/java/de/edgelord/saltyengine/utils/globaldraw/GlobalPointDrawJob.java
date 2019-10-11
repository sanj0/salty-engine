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
import de.edgelord.saltyengine.transform.Vector2f;

public class GlobalPointDrawJob extends GlobalDrawJob {

    private Vector2f point;

    public GlobalPointDrawJob(int id) {
        super(id);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(getColor());
        saltyGraphics.drawPoint(point, 10);
    }

    /**
     * Gets {@link #point}.
     *
     * @return the value of {@link #point}
     */
    public Vector2f getPoint() {
        return point;
    }

    /**
     * Sets {@link #point}.
     *
     * @param point the new value of {@link #point}
     */
    public void setPoint(Vector2f point) {
        this.point = point;
    }
}
