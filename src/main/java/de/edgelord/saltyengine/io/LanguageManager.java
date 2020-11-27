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

package de.edgelord.saltyengine.io;

import de.edgelord.saltyengine.io.serialization.DataReader;
import de.edgelord.saltyengine.resource.Resource;

import java.io.IOException;

/**
 * This class is a set of static methods to let your game support multiple
 * languages. The text is saved in a stdf file. A simple example for the text
 * "It's dangerous to go alone!", saved with the name "old_man_0" and "Take this
 * with you!", save with the name "old_man_1" (excuse my bad french!)
 * <pre>
 *     {@code
 *
 *     {old_man_0}
 *     The text for the old man to be introduced
 *     (english)It's dangerous to go alone!(*english)
 *     (german)Es ist gefährlich alleine zu gehen!(*german)
 *     (french)C'est dangereux d'aller seul!(*french)
 *     {*old_man_0}
 *
 *     {old_man_1}
 *     The text for the old man to turn out nice
 *     (english)Take this with you!(*english)
 *     (german)Nimm dies mit!(*german)
 *     (french)I DON'T KNOW HOW TO SAY THAT IN FRENCH!(*french)
 *     {*old_man_1}
 *     }
 * </pre>
 * <p>
 * You can use any languages, you just have to make sure that {@link #language}
 * is one the ones you used.
 */
@Deprecated
public class LanguageManager {

    /**
     * The language to use.
     * The value of this fields has to
     * be present in the text file.
     */
    private static String language = "english";

    private static DataReader textReader = null;

    /**
     * Initializes the mechanisms to read language specific text out of a file
     * with the given name relative to the given resource. The given name should
     * point to a stdf file. See
     * <a href="https://www.github.com/edgelord314/stdf">the
     * repository</a> or the example above for more information.
     *
     * @param fileName the name of the text-resource file, without any
     *                 extensions even though the extension of the file must be
     *                 {@link DataReader#SDB_FILE_EXTENSION}, which is ".sdb"
     * @param resource the resource to locate the file
     *
     * @throws IOException when the I/O process fails
     */
    public static void init(final String fileName, final Resource resource) throws IOException {
        textReader = new DataReader(resource.getFileResource(fileName + DataReader.SDB_FILE_EXTENSION));
    }

    /**
     * Reads the text with the given name in the language with the name {@link
     * #language} from the file specified by the {@link #init(String, Resource)}
     * call. For an example please look above to the class description.
     *
     * @param textId the id of the text
     *
     * @return the text with the given id and the language with the name {@link
     * #language}
     */
    public static String getText(final String textId) {
        if (textReader == null) {
            System.err.println("Cannot read text while LanguageManager wasn't initialized. Call LanguageManager.init(String, Resource) before!");
            return "";
        } else {
            return textReader.getSpecies(textId).getTagValue(language);
        }
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(final String language) {
        LanguageManager.language = language;
    }
}
