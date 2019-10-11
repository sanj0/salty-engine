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

import de.edgelord.saltyengine.core.interfaces.Drawable;

import java.awt.*;

public abstract class GlobalDrawJob implements Drawable {

    private int id;
    private Color color;
    private boolean visible = true;

    public GlobalDrawJob(int id) {
        this.id = id;
    }

    /**
     * Gets {@link #id}.
     *
     * @return the value of {@link #id}
     */
    public int getId() {
        return id;
    }

    /**
     * Sets {@link #id}.
     *
     * @param id the new value of {@link #id}
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets {@link #color}.
     *
     * @return the value of {@link #color}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets {@link #color}.
     *
     * @param color the new value of {@link #color}
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets {@link #visible}.
     *
     * @return the value of {@link #visible}
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets {@link #visible}.
     *
     * @param visible the new value of {@link #visible}
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
