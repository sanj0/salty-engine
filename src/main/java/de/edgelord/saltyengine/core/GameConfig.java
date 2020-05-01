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

public class GameConfig {

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
