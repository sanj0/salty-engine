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
     * @param size the size of the returned defaultFont
     * @return the {@link Font} read from the given file, derived to the given size
     * @throws IOException when the file is not readable or does not exist
     * @throws FontFormatException when the defaultFont format is not TTF
     */
    public Font getFont(String relativePath, float size) throws IOException, FontFormatException {
        return Font.createFont(Font.TRUETYPE_FONT, getResource().getFileResource(relativePath)).deriveFont(size);
    }

    /**
     * Loads a new {@link Font} from the given *.ttf file and stores it in {@link StaticSystem#defaultFont}
     *
     * @param relativePath the realtive path to the ttf file
     * @param size the size of the defaultFont
     * @throws IOException when the file is not readable or does not exist
     * @throws FontFormatException when the defaultFont format is not TTF
     * @see #getFont(String, float)
     */
    public void loadFontToDefault(String relativePath, float size) throws IOException, FontFormatException {
        StaticSystem.defaultFont = getFont(relativePath, size);
    }
}
