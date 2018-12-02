/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.cosmetic;

import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.NullGameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This is a grid of tiles, which is static, that means it cannot be changed after it was build once.
 * It is a collection of {@link Coordinates} referring each to a BufferedImage.
 * You build the grid using {@link #buildTileGrid(HashMap)}, a example usage:
 *
 * <pre>
 *     {@code
 *
 *     public void buildTileGrid(HashMap<Coordinates, BufferedImage> grid) {
 *
 *         BufferedImage dirt = SaltySystem.defaultImageFactory.getOptimizedImageResource("res/pictures/dirt.png");
 *         BufferedImage grass = SaltySystem.defaultImageFactory.getOptimizedImageResource("res/pictures/grass.png");
 *
 *         grid.put(new Coordinates(0, 0), grass);
 *         grid.put(new Coordinates(1, 0), grass);
 *         grid.put(new Coordinates(2, 0), grass);
 *
 *         grid.put(new Coordinates(0, 1), dirt);
 *         grid.put(new Coordinates(1, 1), dirt);
 *         grid.put(new Coordinates(2, 1), dirt);
 *
 *         grid.put(new Coordinates(0, 2), dirt);
 *         grid.put(new Coordinates(1, 2), dirt);
 *         grid.put(new Coordinates(2, 2), dirt);
 *     }
 *     }
 * </pre>
 * <p>
 * That example would build a grid like that:
 * <p>
 * | grass | grass | grass | <br>
 * | dirt  | dirt  | dirt  | <br>
 * | dirt  | dirt  | dirt  | <br>
 * <p>
 * All images will be drawn with {@link #tileSize}. <br>
 * This class extends {@link DrawingRoutine}, which means that you can add it to a {@link de.edgelord.saltyengine.scene.Scene} using
 * {@link de.edgelord.saltyengine.scene.Scene#addDrawingRoutine(DrawingRoutine)}, and you can also set the {@link de.edgelord.saltyengine.gameobject.DrawingRoutine.DrawingPosition}
 * in the constructor. If not, the default will be {@link de.edgelord.saltyengine.gameobject.DrawingRoutine.DrawingPosition#BEFORE_GAMEOBJECTS}.
 * <p>
 * The first tile (0, 0) will be drawn at the position passed into the constructor, every other tiles will be drawn relative to that one,
 * like shown in the example above.
 */
public abstract class StaticTileGrid extends DrawingRoutine {

    private HashMap<Coordinates, BufferedImage> tiles = new HashMap<>();

    private BufferedImage tileGrid;

    private boolean resizeTiles = false;
    private Dimensions tileSize;
    private Vector2f position;

    public StaticTileGrid(DrawingPosition drawingPosition, Vector2f position, Dimensions tileSize) {
        super(drawingPosition);

        this.position = position;
        this.tileSize = tileSize;
        buildTileGrid(tiles);

        tileGrid = createStaticGridPicture();
    }

    public StaticTileGrid(DrawingPosition drawingPosition, float x, float y, float width, float height) {
        this(drawingPosition, new Vector2f(x, y), new Dimensions(width, height));
    }

    public StaticTileGrid(Vector2f position, Dimensions tileSize) {
        this(DrawingPosition.BEFORE_GAMEOBJECTS, position, tileSize);
    }

    public StaticTileGrid(float x, float y, float tileWidth, float tileHeight) {
        this(DrawingPosition.BEFORE_GAMEOBJECTS, new Vector2f(x, y), new Dimensions(tileWidth, tileHeight));
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.drawImage(tileGrid, position.getX(), position.getY());
    }

    public abstract void buildTileGrid(HashMap<Coordinates, BufferedImage> grid);

    private BufferedImage createStaticGridPicture() {

        int maxX = 0;
        int maxY = 0;
        BufferedImage image;
        Iterator iterator;
        SaltyGraphics graphics;

        iterator = tiles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();

            Coordinates coordinates = (Coordinates) pair.getKey();

            maxX = Math.max(maxX, coordinates.getX());
            maxY = Math.max(maxY, coordinates.getY());
        }

        image = new BufferedImage(Math.round(maxX * tileSize.getWidth()), Math.round(maxY * tileSize.getHeight()), BufferedImage.TYPE_INT_ARGB);
        graphics = new SaltyGraphics(image.createGraphics());

        iterator = tiles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();

            BufferedImage tileImage = (BufferedImage) pair.getValue();
            Coordinates coordinates = (Coordinates) pair.getKey();
            Vector2f tilePos = getTilePosition(coordinates, false);

            graphics.drawImage(tileImage, tilePos.getX(), tilePos.getY(), tileSize.getWidth(), tileSize.getHeight());
        }
        graphics.getGraphics2D().dispose();

        return image;
    }

    /**
     * Adds a GameObject with {@link de.edgelord.saltyengine.gameobject.GameObject#setStationary(boolean)} true and a hitbox
     * starting at the left upper corner of the tile with the given coordinates and with the given size in tiles to the given
     * scene.
     *
     * @param tilePosition the tile which is the starting point for the hitbox
     * @param size         the size (measured in tile - not in pixels) for the hitbox
     * @param scene        the scene the GameObject is to be added to
     * @return the created and already added {@link NullGameObject}
     */
    public GameObject addHitbox(Coordinates tilePosition, Dimensions size, Scene scene) {
        Vector2f tilePos = getTilePosition(tilePosition, true);
        float width = size.getWidth() * tileSize.getWidth();
        float height = size.getHeight() * tileSize.getHeight();

        GameObject hitbox = new NullGameObject(tilePos.getX(), tilePos.getY(), width, height, "tileHitbox-" + tilePos.getX() + "," + tilePos.getY() + ";" + width + "," + height);

        scene.addGameObject(hitbox);

        return hitbox;
    }

    /**
     * Calls {@link #addHitbox(Coordinates, Dimensions, Scene)} by parsing the given params to the needed ones
     *
     * @param tileX  the x position of the starting tile
     * @param tileY  the y position of the starting tile
     * @param width  the width (measured in tiles)
     * @param height the height (measured in tiles)
     * @param scene  the Scene the hitbox is to be added to
     * @return the added {@link NullGameObject}
     */
    public GameObject addHitbox(int tileX, int tileY, int width, int height, Scene scene) {
        return addHitbox(new Coordinates(tileX, tileY), new Dimensions(width, height), scene);
    }

    public Vector2f getTilePosition(Coordinates tile, boolean absolute) {

        if (absolute) {
            return new Vector2f(position.getX() + (tile.getX() * tileSize.getWidth()), position.getY() + (tile.getY() * tileSize.getHeight()));
        } else {
            return new Vector2f(tile.getX() * tileSize.getWidth(), tile.getY() * tileSize.getHeight());
        }
    }

    public boolean isResizeTiles() {
        return resizeTiles;
    }

    public void setResizeTiles(boolean resizeTiles) {
        this.resizeTiles = resizeTiles;
    }

    public Dimensions getTileSize() {
        return tileSize;
    }

    public void setTileSize(Dimensions tileSize) {
        this.tileSize = tileSize;
    }
}
