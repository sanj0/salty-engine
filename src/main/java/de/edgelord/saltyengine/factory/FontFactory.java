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

package de.edgelord.saltyengine.factory;

import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
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
        return Font.createFont(Font.TRUETYPE_FONT, getResource().getFileResource(relativePath)).deriveFont(size);
    }

    /**
     * Loads a new {@link Font} from the given *.ttf file and stores it in {@link StaticSystem#defaultFont}
     *
     * @param relativePath the relative path to the ttf file
     * @param size         the size of the defaultFont
     * @throws IOException         when the file is not readable or does not exist
     * @throws FontFormatException when the defaultFont format is not TTF
     * @see #getFont(String, float)
     */
    public void loadFontToDefault(String relativePath, float size) throws IOException, FontFormatException {
        StaticSystem.defaultFont = getFont(relativePath, size);
    }
}
