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

package de.edgelord.saltyengine.effect;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToDataConverter;

import java.io.File;
import java.io.IOException;
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

    private HashMap<Coordinates, SaltyImage> tiles = new HashMap<>();

    private SaltyImage tileGrid;

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

    public abstract void buildTileGrid(HashMap<Coordinates, SaltyImage> grid);

    /**
     * Reads a Tilemap file created with Salty Tilemap Creator
     *
     * @param stm             the file
     * @param position        the position of the tilemap in user-space
     * @param drawingPosition either to draw it before or after the {@link GameObject}s etc.
     * @return a new {@link StaticTileGrid}
     * @throws IOException when the file can't be read
     */
    public static StaticTileGrid readSTM(File stm, Vector2f position, DrawingPosition drawingPosition) throws IOException {

        DataReader dataReader = new DataReader(stm);
        Species metaInf = dataReader.getSpecies("meta-inf");
        Species images = dataReader.getSpecies("images");
        Species tiles = dataReader.getSpecies("tiles");

        return new StaticTileGrid(drawingPosition, position, new Dimensions(ValueToDataConverter.convertToFloat(metaInf, "tile-height"), ValueToDataConverter.convertToFloat(metaInf, "tile-width"))) {
            @Override
            public void buildTileGrid(HashMap<Coordinates, SaltyImage> grid) {

                Map<String, SaltyImage> imageMap = new HashMap<>();

                for (int i = 0; i < ValueToDataConverter.convertToInteger(images, "entry-count"); i++) {
                    String value = images.getTagValue("image" + i);
                    String[] values = value.split(",");
                    imageMap.put(values[0], SaltySystem.defaultImageFactory.getImageResource(values[1]));
                }

                for (int i = 0; i < ValueToDataConverter.convertToInteger(tiles, "entry-count"); i++) {
                    String value = tiles.getTagValue("tile" + i);
                    String[] values = value.split(",");
                    String[] coordinates = values[1].split("#");
                    grid.put(new Coordinates(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1])), imageMap.get(values[0]));
                }

                System.out.println(grid.size());
            }
        };
    }

    private SaltyImage createStaticGridPicture() {

        int maxX = 0;
        int maxY = 0;
        SaltyImage image;
        Iterator iterator;
        SaltyGraphics graphics;

        iterator = tiles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();

            Coordinates coordinates = (Coordinates) pair.getKey();

            maxX = Math.max(maxX, coordinates.getX());
            maxY = Math.max(maxY, coordinates.getY());
        }

        int width = Math.round(++maxX * tileSize.getWidth());
        int height = Math.round(++maxY * tileSize.getHeight());

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("You have to fill the StaticTileGrid with at least one tile within buildTileGrid(HashMap)");
        }

        image = SaltySystem.createPreferredImage(width, height);
        graphics = new SaltyGraphics(image.createGraphics());

        iterator = tiles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();

            SaltyImage tileImage = (SaltyImage) pair.getValue();
            Coordinates coordinates = (Coordinates) pair.getKey();
            Vector2f tilePos = getTilePosition(coordinates, false);

            graphics.drawImage(tileImage, tilePos.getX(), tilePos.getY(), tileSize.getWidth(), tileSize.getHeight());
        }
        graphics.getGraphics2D().dispose();

        return image;
    }

    /**
     * Fills a specific rectangular "area" within the given HashMap with the given {@link SaltyImage}
     * The "area" starts at the given {@link Coordinates} and ends after the given width and the given height was reached.
     * You can use this within {@link #buildTileGrid(HashMap)}
     *
     * @param grid         the HasMpa to fill
     * @param tile         the image with which the area is to be filled
     * @param startingTile the tile which is the upper left corner of the rectangle
     * @param width        the width of the rectangle
     * @param height       the height of the rectangle
     */
    public void fillArea(HashMap<Coordinates, SaltyImage> grid, SaltyImage tile, Coordinates startingTile, int width, int height) {

        int currentX = startingTile.getX();
        int currentY = startingTile.getY();
        while (currentX < width + 1) {

            while (currentY < height + 1) {

                grid.put(new Coordinates(currentX, currentY), tile);

                currentY++;
            }
            currentY = startingTile.getY();
            currentX++;
        }
    }

    /**
     * Adds an {@link EmptyGameObject} starting at the left upper corner of the tile with the given coordinates
     * and with the given size in tiles to the given scene.
     *
     * @param tilePosition the tile which is the starting point for the hitbox
     * @param size         the size (measured in tile - not in pixels) for the hitbox
     * @param scene        the scene the GameObject is to be added to
     * @return the created and already added {@link EmptyGameObject}
     */
    public GameObject addHitbox(Coordinates tilePosition, Dimensions size, Scene scene) {
        Vector2f tilePos = getTilePosition(tilePosition, true);
        float width = size.getWidth() * tileSize.getWidth();
        float height = size.getHeight() * tileSize.getHeight();

        GameObject hitbox = new EmptyGameObject(tilePos.getX(), tilePos.getY(), width, height, "tileHitbox-" + tilePos.getX() + "," + tilePos.getY() + ";" + width + "," + height);

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
     * @return the added {@link EmptyGameObject}
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
