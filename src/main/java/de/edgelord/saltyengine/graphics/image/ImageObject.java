/*
 * Copyright 2021 Malte Dostal
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

package de.edgelord.saltyengine.graphics.image;

import de.edgelord.saltyengine.collision.collider.GhostCollider;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;

public class ImageObject extends GameObject {

    public static final String TAG = "image";
    private final SaltyImage img;
    private final String imgPath;

    public ImageObject(final Transform t, final SaltyImage img, final String imgPath) {
        super(t, TAG);
        this.img = img;
        this.imgPath = imgPath;

        setCollider(new GhostCollider());
    }

    @Override
    public void initialize() {
        // nothing to initialize
    }

    @Override
    public void onCollision(final CollisionEvent event) {
        // nothing to do on collision
    }

    @Override
    public void onFixedTick() {
        // nohting to do on fixed ticks!
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(img, this);
    }

    /**
     * Gets {@link #img}.
     *
     * @return the value of {@link #img}
     */
    public SaltyImage getImg() {
        return img;
    }

    /**
     * Gets {@link #imgPath}.
     *
     * @return the value of {@link #imgPath}
     */
    public String getImgPath() {
        return imgPath;
    }
}
