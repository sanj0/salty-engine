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

import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * A utility class to place objects in a grid.
 * <p>
 * Based on a {@link #tileSize tile size} and an {@link #origin}, you may obtain
 * a {@link de.edgelord.saltyengine.transform.Transform} according to a given
 * grid position and width and height using {@link #getTransform(int, int, int,
 * int)} or {@link #getTransform(Coordinates, Dimensions)}.
 * <p>
 * You may also obtain the <code>Transform</code> of a single tile at a given
 * position using {@link #getTile(int, int)}.
 */
public class Grid {

    /**
     * The size of one tile in the grid.
     */
    private Dimensions tileSize;

    /**
     * The origin point of the grid.
     */
    private Vector2f origin;

    /**
     * The base constructor.
     *
     * @param tileSize the {@link #tileSize size of one tile}
     * @param origin   the {@link #origin origin of the grid}
     */
    public Grid(final Dimensions tileSize, final Vector2f origin) {
        this.tileSize = tileSize;
        this.origin = origin;
    }

    /**
     * A constructor.
     *
     * @param tileWidth  the width of one tile
     * @param tileHeight the height of one tile
     * @param originX    the x position of the origin of the grid
     * @param originY    the y position of the origin of the grid
     */
    public Grid(final float tileWidth, final float tileHeight, final float originX, final float originY) {
        this(new Dimensions(tileWidth, tileHeight), new Vector2f(originX, originY));
    }

    /**
     * Constructs a new instance with the given {@link #tileSize tile size} and
     * an {@link #origin} at {@link Vector2f#zero()}.
     *
     * @param tileSize the {@link #tileSize size of one tile}
     */
    public Grid(final Dimensions tileSize) {
        this(tileSize, Vector2f.zero());
    }

    /**
     * Constructs a new instance with the given {@link #tileSize tile size} and
     * an {@link #origin} at {@link Vector2f#zero()}.
     *
     * @param tileWidth  the width of one tile
     * @param tileHeight the height of one tile
     */
    public Grid(final float tileWidth, final float tileHeight) {
        this(new Dimensions(tileWidth, tileHeight));
    }

    /**
     * Returns a {@link Transform} that resembles the given parameters with this
     * grid as the system of coordinates.
     *
     * @param position   the coordinates of the tile that is giving the
     *                   returned
     *                   <code>Transform</code> its position
     * @param dimensions the <code>Dimensions</code> of the returned
     *                   <code>Transform</code>
     *                   in tiles
     *
     * @return a {@link Transform} that resembles the given parameters with the
     * grid as the system of coordinates
     */
    public Transform getTransform(final Coordinates position, final Dimensions dimensions) {

        final float gridWidth = tileSize.getWidth();
        final float gridHeight = tileSize.getHeight();

        final float x = (position.getX() * gridWidth) + origin.getX();
        final float y = (position.getY() * gridHeight) + origin.getY();
        final float width = dimensions.getWidth() * gridWidth;
        final float height = dimensions.getHeight() * gridHeight;

        return new Transform(x, y, width, height);
    }

    /**
     * Calls {@link #getTransform(Coordinates, Dimensions)} to return a {@link
     * Transform} that resembles the given parameters with this grid as the
     * system of coordinates.
     *
     * @param x      the x position of the returned <code>Transform</code>
     *               within the grid
     * @param y      the y position of the returned <code>Transform</code>
     *               within the grid
     * @param width  the width of the returned
     *               <code>Transform</code> given
     *               as number of tiles
     * @param height the height of the returned
     *               <code>Transform</code> given
     *               as number of tiles
     *
     * @return a {@link Transform} that resembles the given parameters with the
     * grid as the system of coordinates
     * @see #getTransform(Coordinates, Dimensions)
     */
    public Transform getTransform(final int x, final int y, final int width, final int height) {
        return getTransform(new Coordinates(x, y), new Dimensions(width, height));
    }

    /**
     * Returns a {@link Transform} with the following values:
     * <p>
     * - the {@link Vector2f position} of the tile with the given coordinates
     * within this grid - the {@link Dimensions dimensinos} of on tile, which is
     * {@link #tileSize}
     *
     * @param x the x position of the tile within this grid
     * @param y the y position of the tile within this grid
     *
     * @return the <code>Transform</code> of the tile with the given {@link
     * Coordinates} within this grid
     */
    public Transform getTile(final int x, final int y) {
        return getTransform(new Coordinates(x, y), Dimensions.one());
    }

    /**
     * Gets {@link #tileSize}.
     *
     * @return value of {@link #tileSize}
     */
    public Dimensions getTileSize() {
        return tileSize;
    }

    /**
     * Sets {@link #tileSize}.
     *
     * @param tileSize the new {@link #tileSize}
     */
    public void setTileSize(final Dimensions tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Gets {@link #origin}.
     *
     * @return the value of {@link #origin}
     */
    public Vector2f getOrigin() {
        return origin;
    }

    /**
     * Sets {@link #origin}.
     *
     * @param origin the new value of {@link #origin}
     */
    public void setOrigin(final Vector2f origin) {
        this.origin = origin;
    }
}
