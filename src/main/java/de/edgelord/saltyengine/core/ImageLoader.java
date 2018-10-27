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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageLoader {

    private static HashMap<String, BufferedImage> images = new HashMap<>();

    /**
     * The default {@link BufferedImage} to be returned if the requested wasn't found.
     */
    private static BufferedImage defaultImage = new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_GRAY);

    /**
     * This method will load a new Image with the given name into the map
     * {@link #images}. If the name is already in the map, it won't load it again.
     * This method will always load an optimized image using {@link ImageFactory#getOptimizedImageResource(String)}.
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path of the image to be loaded from
     * @param imageFactory the Factory from which to load the image
     */
    public static void loadNewImage(String name, String relativePath, ImageFactory imageFactory) {
        if (images.containsKey(name)) {
            System.out.println("[INFO] The image " + name + " is already loaded!");
        } else {
            images.put(name, imageFactory.getOptimizedImageResource(relativePath));
        }
    }

    /**
     * This method will call {@link #loadNewImage(String, String, ImageFactory)} with the
     * default {@link ImageFactory} {@link StaticSystem#defaultImageFactory}
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path of the image to be loaded from
     */
    public static void loadNewImage(String name, String relativePath) {
        loadNewImage(name, relativePath, StaticSystem.defaultImageFactory);
    }

    /**
     * Returns the image with the given name and loads it before
     * if it isn't already.
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path to image to load it from if it isn't already
     * @param imageFactory the Factory from which to load the image
     * @return the {@link BufferedImage} corresponding to the given name and/or path
     */
    public static BufferedImage getOrLoadImage(String name, String relativePath, ImageFactory imageFactory) {

        if (!imageAlreadyLoaded(name)) {
            loadNewImage(name, relativePath, imageFactory);
        }

        return getImage(name);
    }

    /**
     * Calls the method {@link #getOrLoadImage(String, String, ImageFactory)} with the default
     * {@link ImageFactory} {@link StaticSystem#defaultImageFactory}.
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path to image to load it from if it isn't already
     * @return the {@link BufferedImage} corresponding to the given name and/or path
     */
    public static BufferedImage getOrLoadImage(String name, String relativePath) {
        return getOrLoadImage(name, relativePath, StaticSystem.defaultImageFactory);
    }

    /**
     * Returns true if the given name is already in the list
     * of loaded images. Returns false if not
     *
     * @param name the name of the image to be checked.
     * @return whether the image with the given name is already loaded or not
     */
    public static boolean imageAlreadyLoaded(String name) {
        return images.containsKey(name);
    }

    /**
     * Returns true if the given {@link BufferedImage} is already in the list
     * of loaded images. Returns false if not
     *
     * @param image the image to be checked.
     * @return whether the image with the given name is already loaded or not
     */
    public static boolean imageAlreadyLoaded(BufferedImage image) {
        return images.containsValue(image);
    }

    /**
     * Returns the {@link BufferedImage} that corresponds to the given name.
     * If it can't be found, this method will return the default Image {@link #defaultImage}
     *
     * @param name the name of the image to be returned
     * @return the image corresponding to the given name
     */
    public static BufferedImage getImage(String name) {
        return images.getOrDefault(name, defaultImage);
    }

    /**
     * Removes the image with the given id-name from the list.
     *
     * @param name the id-name of the image to be removed.
     */
    public void removeImage(String name) {
        images.remove(name);
    }

    /**
     * Removes all images from the list
     */
    public void removeAll() {
        images.clear();
    }
}
