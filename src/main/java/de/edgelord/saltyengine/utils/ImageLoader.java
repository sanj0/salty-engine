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

import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.factory.ImageFactory;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageLoader {

    private static HashMap<String, SaltyImage> images = new HashMap<>();

    /**
     * The default {@link BufferedImage} to be returned if the requested wasn't found.
     */
    private static SaltyImage defaultImage = SaltySystem.createPreferredImage(10, 10);

    /**
     * This method will load a new Image with the given name into the map
     * {@link #images}. If the name is already in the map, it won't load it again.
     * This method will always load an optimized image using {@link ImageFactory#getImageResource(String)}.
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path of the image to be loaded from
     * @param imageFactory the Factory from which to load the image
     */
    public static void loadNewImage(String name, String relativePath, ImageFactory imageFactory) {
        if (images.containsKey(name)) {
            System.out.println("[INFO] The image " + name + " is already loaded!");
        } else {
            images.put(name, imageFactory.getImageResource(relativePath));
        }
    }

    /**
     * This method will call {@link #loadNewImage(String, String, ImageFactory)} with the
     * default {@link ImageFactory} {@link SaltySystem#defaultImageFactory}
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path of the image to be loaded from
     */
    public static void loadNewImage(String name, String relativePath) {
        loadNewImage(name, relativePath, SaltySystem.defaultImageFactory);
    }

    /**
     * Returns the image with the given name and loads it before
     * if it isn't already.
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path to image to load it from if it isn't already
     * @param imageFactory the Factory from which to load the image
     * @return the {@link SaltyImage} corresponding to the given name and/or path
     */
    public static SaltyImage getOrLoadImage(String name, String relativePath, ImageFactory imageFactory) {

        if (!imageAlreadyLoaded(name)) {
            loadNewImage(name, relativePath, imageFactory);
        }

        return getImage(name);
    }

    /**
     * Calls the method {@link #getOrLoadImage(String, String, ImageFactory)} with the default
     * {@link ImageFactory} {@link SaltySystem#defaultImageFactory}.
     *
     * @param name         the id-name of the image
     * @param relativePath the relative path to image to load it from if it isn't already
     * @return the {@link SaltyImage} corresponding to the given name and/or path
     */
    public static SaltyImage getOrLoadImage(String name, String relativePath) {
        return getOrLoadImage(name, relativePath, SaltySystem.defaultImageFactory);
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
     * Returns true if the given {@link SaltyImage} is already in the list
     * of loaded images. Returns false if not
     *
     * @param image the image to be checked.
     * @return whether the image with the given name is already loaded or not
     */
    public static boolean imageAlreadyLoaded(SaltyImage image) {
        return images.containsValue(image);
    }

    /**
     * Returns the {@link SaltyImage} that corresponds to the given name.
     * If it can't be found, this method will return the default Image {@link #defaultImage}
     *
     * @param name the name of the image to be returned
     * @return the image corresponding to the given name
     */
    public static SaltyImage getImage(String name) {
        return images.getOrDefault(name, defaultImage);
    }

    /**
     * Removes the image with the given id-name from the list.
     *
     * @param name the id-name of the image to be removed.
     */
    public static void removeImage(String name) {
        images.remove(name);
    }

    /**
     * Removes all images from the list
     */
    public static void removeAll() {
        images.clear();
    }
}
