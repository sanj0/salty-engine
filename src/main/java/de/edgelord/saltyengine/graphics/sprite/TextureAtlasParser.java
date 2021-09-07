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

package de.edgelord.saltyengine.graphics.sprite;

import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;
import de.edgelord.sanjo.SanjoFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class describes and reads a sanjo-based file format to read different
 * Animations and Textures from a single image.
 * <p>The format is defined as in the following example:
 * <p>
 * <pre>
 * .resource=[inner/outer] # defaults to "inner"
 * .source-path=path/to/the/image
 * .sprite-size=width, height
 * .sprite-offset=offsetX,offsetY [optional - default: 0,0]
 * .starts-with-offset=true/false [optional - default: false]
 * :idOfObject1
 *     .type=animation
 *     .sprites=x1, y1;x2, y2; ...
 * :idOfObject2
 *     .type=image
 *     .sprite=x, y
 * :idOfObject3
 *     .type=animation
 *     .position=absolute
 *     .sprites=0, 0, 10, 10;0, 10, 10, 10; ...
 * </pre>
 * <p>
 * Optionally, {@code .position=absolute} together with an own {@code
 * .sprite-size = widthxheight} can be added to any object to make sprite
 * coordinates be treated as absolute positions in pixels instead of coordinates
 * in the spritesheet.
 */
public class TextureAtlasParser {

    public static final String KEY_RESOURCE = "resource";
    public static final String VALUE_INNER_RESOURCE = "inner";
    public static final String VALUE_OUTER_RESOURCE = "outer";

    public static final String KEY_SOURCE_PATH = "source-path";

    public static final String KEY_SPRITE_SIZE = "sprite-size";
    public static final String SPRITE_SIZE_SEPARATOR = "x";

    public static final String KEY_SPRITE_OFFSET = "sprite-offset";
    public static final String KEY_STARTS_WITH_OFFSET = "starts-with-offset";

    public static final String VALUE_SEPARATOR = ";";

    public static final String KEY_POSITION = "position";
    public static final String VALUE_ABSOLUTE = "absolute";
    /**
     * For the sake of completeness
     */
    public static final String VALUE_RELATIVE = "relative";

    public static final String KEY_TYPE = "type";
    public static final String VALUE_ANIMATION = "animation";
    public static final String VALUE_IMAGE = "image";

    public static final String KEY_SPRITE = "sprite";
    public static final String KEY_SPRITES = "sprites";

    private static final Resource DEFAULT_INNER_RESOURCE = new InnerResource();

    private TextureAtlasParser() {
    }

    /**
     * Reads the given atlas file into a map of id - object. All values in the
     * returned map are of one of the following types:
     * <br>{@link SaltyImage}
     * <br>{@link SpritesheetAnimation}
     *
     * @param atlas the atlas file
     *
     * @return the texture objects as described in the given atlas mapped id to
     * object
     */
    public static TextureAtlas readAtlas(final File atlas) throws IOException {
        final Map<String, Object> objectMap = new HashMap<>();
        final String path = atlas.getAbsolutePath();
        final SJClass dataRoot = new SanjoFile(path).parser().parse();

        final Resource resource = readResource(dataRoot, path);
        final SaltyImage srcImage = readSourceImage(dataRoot, path, resource);
        final Dimensions spriteSize = readSpriteSize(dataRoot, path);
        final Coordinates spriteOffset = readSpriteOffset(dataRoot).toCoordinates();
        final boolean startsWithOffset = readStartsWithOffset(dataRoot);
        final Spritesheet spritesheet = new Spritesheet(srcImage, spriteSize.getWidth(), spriteSize.getHeight(),
                spriteOffset.getX(), spriteOffset.getY(), startsWithOffset);

        for (final SJClass object : dataRoot.getChildren()) {
            final boolean absolutePosition = wantsAbsolutePosition(object, path);
            final String type = readType(object);
            final Object textureObject;

            if (type.equals(VALUE_IMAGE)) {
                if (absolutePosition) {
                    final Transform spriteTransform = Transform.parseTransform(object.getValue(KEY_SPRITE).get().string());
                    textureObject = srcImage.subImage((int) spriteTransform.getX(),
                            (int) spriteTransform.getY(), (int) spriteTransform.getWidth(), (int) spriteTransform.getHeight());
                } else {
                    final Vector2f position = readPosition(object.getValue(KEY_SPRITE).get().string());
                    final Coordinates c = position.toCoordinates();
                    textureObject = spritesheet.getFrame(c.getX(), c.getY()).getImage();
                }
            } else if (type.equals(VALUE_ANIMATION)) {
                final List<String> strings = Arrays.asList(object.getValue(KEY_SPRITES).get().string().split(VALUE_SEPARATOR));

                if (absolutePosition) {
                    final SpritesheetAnimation animation = new SpritesheetAnimation(new ArrayList<>(strings.size()));
                    for (final String s : strings) {
                        final Transform t = Transform.parseTransform(s);
                        animation.getFrames().add(new Frame(srcImage.subImage((int) t.getX(), (int) t.getY(), (int) t.getWidth(), (int) t.getHeight())));
                    }
                    textureObject = animation;
                } else {
                    final Coordinates[] spriteCoordinates = new Coordinates[strings.size()];

                    int i = 0;
                    for (final String s : strings) {
                        spriteCoordinates[i] = readLiteralPosition(s).toCoordinates();
                        i++;
                    }
                    textureObject = spritesheet.getAnimation(spriteCoordinates);
                }
            } else {
                throw new TextureAtlasFormatError(path, "unknown object type " + type);
            }
            objectMap.put(object.getName(), textureObject);
        }

        return new TextureAtlas(objectMap);
    }

    private static String readType(final SJClass object) {
        return object.getValue(KEY_TYPE).get().string();
    }

    private static Vector2f readLiteralPosition(final String text) {
        return Vector2f.parseVector2f(text);
    }

    private static Vector2f readPosition(final String text) {
        return readLiteralPosition(text);
    }

    private static boolean wantsAbsolutePosition(final SJClass object, final String path) {
        final Optional<SJValue> position = object.getValue(KEY_POSITION);
        if (!position.isPresent() || position.get().string().equals(VALUE_RELATIVE)) {
            return false;
        } else if (position.get().string().equals(VALUE_ABSOLUTE)) {
            return true;
        } else {
            throw new TextureAtlasFormatError(path, "unknown position type " + position.get().string());
        }
    }

    private static Dimensions readSpriteSize(final SJClass dataRoot, final String path) {
        final Optional<SJValue> val = dataRoot.getValue(KEY_SPRITE_SIZE);
        if (!val.isPresent()) {
            throw new TextureAtlasFormatError(path, "missing sprite size <sprite-size>");
        }
        return Dimensions.parseDimensions(val.get().string());
    }

    private static Vector2f readSpriteOffset(final SJClass dataRoot) {
        final Optional<SJValue> val = dataRoot.getValue(KEY_SPRITE_OFFSET);
        return val.map(sjValue -> Vector2f.parseVector2f(sjValue.string())).orElseGet(Vector2f::zero);
    }

    private static boolean readStartsWithOffset(final SJClass dataRoot) {
        final Optional<SJValue> val = dataRoot.getValue(KEY_STARTS_WITH_OFFSET);
        return val.map(sjValue -> Boolean.parseBoolean(sjValue.string())).orElse(false);
    }

    private static SaltyImage readSourceImage(final SJClass dataRoot, final String path, final Resource resource) {
        final Optional<SJValue> imageValue = dataRoot.getValue(KEY_SOURCE_PATH);
        if (!imageValue.isPresent()) {
            throw new TextureAtlasFormatError(path, "missing source image path <source-path>");
        }
        return resource.getImageResource(imageValue.get().string());
    }

    private static Resource readResource(final SJClass dataRoot, final String path) {
        final Optional<SJValue> resourceValue = dataRoot.getValue(KEY_RESOURCE);
        if (!resourceValue.isPresent() || resourceValue.get().string().equals(VALUE_INNER_RESOURCE)) {
            return DEFAULT_INNER_RESOURCE;
        } else if (resourceValue.get().string().equals(VALUE_OUTER_RESOURCE)) {
            // cannot use default instance due to game name potentially
            // not being correct at static class member initialization
            return new OuterResource(false);
        } else {
            throw new TextureAtlasFormatError(path, "unknown resource type " + resourceValue);
        }
    }

    private static class TextureAtlasFormatError extends RuntimeException {
        public TextureAtlasFormatError(final String path, final String message) {
            super("    Error reading texture atlas at " + path + ": " + message);
        }
    }
}
