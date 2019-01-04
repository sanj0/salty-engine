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

package de.edgelord.saltyengine.io.serialization;

import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.FileReader;
import de.edgelord.stdf.writing.DataWriter;
import de.edgelord.stdf.writing.FileWriter;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Serializer {

    /**
     * The name of the file this process should be saving the data to when the display is closing (only works when using
     * {@link de.edgelord.saltyengine.displaymanager.display.DisplayManager} as the {@link de.edgelord.saltyengine.core.Host}, which is default).
     * This is to be relative and without any file extensions.
     */
    private static String saveFileName = "save0";

    /**
     * Whether to add a checksum to the file or not
     */
    private static boolean addChecksum = true;

    private static MessageDigest md5Creator;

    static {
        try {
            md5Creator = MessageDigest.getInstance("sha1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static List<Serializable> consumer = new ArrayList<>();

    private static void serialize(DataWriter writer) throws IOException {
        consumer.forEach(serializable -> {
            Species species = new Species(serializable.getDataSetName(), "");

            serializable.serialize(species);

            writer.addSpecies(species);
        });

        writer.syncFile();

        if (addChecksum) {

            FileReader saveReader = new FileReader(writer.getFile());
            FileWriter checksumWriter = new FileWriter(SaltySystem.defaultHiddenOuterResource.getFileResource("checksum." + writer.getFile().getName()));

            String checksum = new String(md5Creator.digest(prepareForChecksumCreation(saveReader.readFile())));

            checksumWriter.writeThrough(removeSpacesAndNewLines(checksum));
        }
    }

    private static boolean deserialize(DataReader reader) throws IOException {

        boolean isCorrupt;
        boolean alreadySaid = false;

        File checksumFile = SaltySystem.defaultHiddenOuterResource.getFileResource("checksum." + reader.getFile().getName());

        if (!checksumFile.exists()) {
            System.err.println("Checksum-file does not exist.");
            alreadySaid = true;
        }

        FileReader checksumReader = new FileReader(checksumFile);
        FileReader saveReader = new FileReader(reader.getFile());

        String checksum = checksumReader.readFile();

        byte[] saveFileBytes = prepareForChecksumCreation(saveReader.readFile());
        String savefileSum = new String(md5Creator.digest(saveFileBytes));

        if (removeSpacesAndNewLines(checksum).equals(removeSpacesAndNewLines(savefileSum))) {
            isCorrupt = false;
            if (!alreadySaid) {
                System.out.println("The savefile " + reader.getFile().getName() + " does not seem to be corrupt.");
            }
        } else {
            isCorrupt = true;
            if (!alreadySaid) {
                System.err.println("The savefile " + reader.getFile().getName() + " seems to be corrupt!");
            }
        }

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

        return isCorrupt;
    }

    private static byte[] prepareForChecksumCreation(String input) {
        return removeSpacesAndNewLines(input).getBytes();
    }

    private static String removeSpacesAndNewLines(String input) {
        return input.replaceAll(" ", "").replaceAll("([\n\r])", "");
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
     * Deserialize all {@link Serializable}s within {@link #consumer} from a file with the given name relative to a
     * hidden {@link OuterResource}.
     * The extension of the file will be automatically added as {@link DataReader#SDB_FILE_EXTENSION}.
     *
     * @param name the name of the save file
     * @return whether the savefile is corrupt or not. True means that the savefile was changed after the last writing from Salty Engine.
     * If there wasn't a checksum file, it return true.
     * @throws IOException when the I/O process with the file fails
     */
    public static boolean doDeserialization(String name) throws IOException {

        File file = SaltySystem.defaultHiddenOuterResource.getFileResource(name + DataReader.SDB_FILE_EXTENSION);

        if (file.getTotalSpace() < 5) {
            DataWriter writer = new DataWriter(file);
            writer.syncFile();
        }

        return deserialize(new DataReader(file));
    }

    /**
     * Calls {@link #doDeserialization(String)} with {@link #saveFileName} as the file name.
     *
     * @return whether the savefile is corrupt or not. If there wasn't a checksum file, it return false.
     * @throws IOException when the I/O process with the file fails
     */
    public static boolean doDeserialization() throws IOException {
        return doDeserialization(saveFileName);
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

    public static boolean isAddChecksum() {
        return addChecksum;
    }

    public static void setAddChecksum(boolean addChecksum) {
        Serializer.addChecksum = addChecksum;
    }
}
