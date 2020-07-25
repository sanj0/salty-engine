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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.resource.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameConfig {

    public static final String GAME_WIDTH_KEY = "game-width";
    public static final String GAME_HEIGHT_KEY = "game-height";
    public static final String GAME_NAME_KEY = "game-name";
    public static final String FIXED_TICK_MILLIS_KEY = "fixed-tick-millis";

    private final float resWidth;
    private final float resHeight;
    private final long fixedTickMillis;
    private final String gameName;

    public GameConfig(final float resWidth, final float resHeight, final String gameName, final long fixedTickMillis) {
        this.resWidth = resWidth;
        this.resHeight = resHeight;
        this.fixedTickMillis = fixedTickMillis;
        this.gameName = gameName;
    }

    public static GameConfig config(final float resWidth, final float resHeight, final String gameName, final long fixedTickMillis) {
        return new GameConfig(resWidth, resHeight, gameName, fixedTickMillis);
    }

    /**
     * Loads configurations from the file with the
     * given path relative to the given
     * <code>Resource</code> using {@link
     * #load(File)}.
     *
     * @param path     the path to the file
     * @param resource the <code>Resource</code>
     *                 providing the file
     *
     * @return a <code>GameConfig</code> object
     * representing the given file's properties
     * @throws IOException when something goes
     *                     wrong
     */
    public static GameConfig load(String path, Resource resource) throws IOException {
        return load(resource.getFileResource(path));
    }

    /**
     * Loads configurations from the given file
     * using {@link Properties}. The format is as
     * specified in {@link Properties#load(InputStream)}.
     * <p> Example: <p>
     * <pre>
     * game-width: 1200
     * game-height: 900
     * game-name: testing
     * fixed-tick-millis: 1
     * </pre>
     *
     * @param file the file to read the configuration
     *             from
     *
     * @return a <code>GameConfig</code> object
     * representing the given file's properties
     * @throws IOException if something goes
     *                     horribly wrong
     */
    public static GameConfig load(final File file) throws IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(file));

        final float width = Float.parseFloat(properties.getProperty(GAME_WIDTH_KEY));
        final float height = Float.parseFloat(properties.getProperty(GAME_HEIGHT_KEY));
        final String name = properties.getProperty(GAME_NAME_KEY);
        final long fixedTickMillis = Long.parseLong(properties.get(FIXED_TICK_MILLIS_KEY).toString());
        return new GameConfig(width, height, name, fixedTickMillis);
    }

    public float getResWidth() {
        return resWidth;
    }

    public float getResHeight() {
        return resHeight;
    }

    public long getFixedTickMillis() {
        return fixedTickMillis;
    }

    public String getGameName() {
        return gameName;
    }
}
