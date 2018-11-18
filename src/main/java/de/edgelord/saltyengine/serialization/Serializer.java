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

package de.edgelord.saltyengine.serialization;

import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.writing.DataWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Serializer {

    /**
     * The name of the file this process should be saving the data to when the display is closing (only works when using
     * {@link de.edgelord.saltyengine.display.DisplayManager} as the {@link de.edgelord.saltyengine.core.Host}, which is default).
     * This is to be relative and without any file extensions.
     */
    private static String saveFileName = "save0";

    private static List<Serializable> consumer = new ArrayList<>();

    private static void serialize(DataWriter writer) throws IOException {
        consumer.forEach(serializable -> {
            Species species = new Species(serializable.getDataSetName(), "");

            serializable.serialize(species);

            writer.addSpecies(species);
        });

        writer.syncFile();
    }

    private static void deserialize(DataReader reader) {

        for (Serializable serializable : consumer) {
            Species species;
            try {
                species = reader.getSpecies(serializable.getDataSetName());
            } catch (Exception e) {
                System.out.println("Never serialized something for " + serializable.getDataSetName() + " so cannot deserialize for it!");
                continue;
            }
            serializable.deserialize(species);
        }
    }

    /**
     * Serializes all {@link Serializable}s within {@link #consumer} in a file with the given name relative to a hidden
     * {@link OuterResource}.
     * The extension of the file will be automatically added as {@link DataReader#SDB_FILE_EXTENSION}.
     *
     * @param name the name of the save file
     * @throws IOException when the I/O process with the file fails
     */
    public static void doSerialization(String name) throws IOException {
        serialize(new DataWriter(SaltySystem.defaultHiddenOuterResource.getFileResource(name + DataReader.SDB_FILE_EXTENSION)));
    }

    /**
     * Calls {@link #doSerialization(String)} with {@link #saveFileName} as the file name.
     *
     * @throws IOException when the I/O process with the file fails
     */
    public static void doSerialization() throws IOException {
        doSerialization(saveFileName);
    }

    /**
     * Deserializes all {@link Serializable}s within {@link #consumer} from a file with the given name relative to a
     * hidden {@link OuterResource}.
     * The extension of the file will be automatically added as {@link DataReader#SDB_FILE_EXTENSION}.
     *
     * @param name the name of the save file
     * @throws IOException when the I/O process with the file fails
     */
    public static void doDeserialization(String name) throws IOException {
        deserialize(new DataReader(SaltySystem.defaultHiddenOuterResource.getFileResource(name + DataReader.SDB_FILE_EXTENSION)));
    }

    /**
     * Calls {@link #doDeserialization(String)} with {@link #saveFileName} as the file name.
     *
     * @throws IOException when the I/O process with the file fails
     */
    public static void doDeserialization() throws IOException {
        doDeserialization(saveFileName);
    }

    public static int size() {
        return consumer.size();
    }

    public static boolean isEmpty() {
        return consumer.isEmpty();
    }

    public static boolean contains(Serializable o) {
        return consumer.contains(o);
    }

    public static boolean add(Serializable serializable) {
        return consumer.add(serializable);
    }

    public static boolean remove(Object o) {
        return consumer.remove(o);
    }

    public static void clear() {
        consumer.clear();
    }

    public static void add(int index, Serializable element) {
        consumer.add(index, element);
    }

    public static boolean removeIf(Predicate<? super Serializable> filter) {
        return consumer.removeIf(filter);
    }

    public static String getSaveFileName() {
        return saveFileName;
    }

    public static void setSaveFileName(String saveFileName) {
        Serializer.saveFileName = saveFileName;
    }
}
