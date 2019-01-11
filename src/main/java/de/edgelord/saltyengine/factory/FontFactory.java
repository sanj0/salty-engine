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

package de.edgelord.saltyengine.factory;

import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class helps reading fonts from the resources from a TRUETYPE_FONT-file (*.ttf)
 */
public class FontFactory extends Factory {

    public FontFactory(Resource resource) {
        super(resource);
    }

    /**
     * Returns a new {@link Font} read from the *.ttf file with the given
     * relative path and derives it to the given size.
     *
     * @param relativePath the relative path to *.ttf file
     * @param size         the size of the returned defaultFont
     * @return the {@link Font} read from the given file, derived to the given size
     * @throws IOException         when the file is not readable or does not exist
     * @throws FontFormatException when the defaultFont format is not TTF
     */
    public Font getFont(String relativePath, float size) throws IOException, FontFormatException {
        File fontFile = getResource().getFileResource(relativePath);
        System.out.println(fontFile.getAbsolutePath());
        return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
    }

    /**
     * Loads a new {@link Font} from the given *.ttf file and stores it in {@link SaltySystem#defaultFont}
     *
     * @param relativePath the relative path to the ttf file
     * @param size         the size of the defaultFont
     * @throws IOException         when the file is not readable or does not exist
     * @throws FontFormatException when the defaultFont format is not TTF
     * @see #getFont(String, float)
     */
    public void loadFontToDefault(String relativePath, float size) throws IOException, FontFormatException {
        SaltySystem.defaultFont = getFont(relativePath, size);
    }
}
