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

import de.edgelord.saltyengine.displaymanager.DisplayManager;
import de.edgelord.saltyengine.io.FileReader;
import de.edgelord.saltyengine.io.FileWriter;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SanjoFile;
import de.edgelord.sanjo.SanjoParser;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Static fields and methods for serializing and deserializing data easily
 * within the engine.
 */
public class Serializer {

    private static final List<Serializable> consumer = new ArrayList<>();
    /**
     * The name of the file this process should be saving the data to when the
     * display is closing (only works when using {@link DisplayManager}
     * as the {@link de.edgelord.saltyengine.core.Host}, which is default). This
     * is to be relative and without any file extensions.
     */
    private static String saveFileName = "save0";
    /**
     * Whether to add a checksum to the file or not
     */
    private static boolean addChecksum = true;
    private static MessageDigest hashCreator;

    static {
        try {
            hashCreator = MessageDigest.getInstance("sha1");
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void serialize(final FileWriter writer, final SJClass root) throws IOException {
        consumer.forEach(serializable -> {
            final SJClass data = root.addChild(serializable.getDataSetName());
            serializable.serialize(data);
        });

        writer.writeThrough(root.write());

        if (addChecksum) {
            final FileReader saveReader = new FileReader(writer.getFile());
            final FileWriter checksumWriter = new FileWriter(SaltySystem.defaultHiddenOuterResource.getFileResource("checksum." + writer.getFile().getName()));

            final String checksum = new String(hashCreator.digest(prepareForChecksumCreation(saveReader.readFile())));
            checksumWriter.writeThrough(removeSpacesAndNewLines(checksum));
        }
    }

    private static boolean deserialize(final FileReader reader, final SJClass root) throws IOException {
        final boolean isCorrupt;
        boolean alreadySaid = false;

        final File checksumFile = SaltySystem.defaultHiddenOuterResource.getFileResource("checksum." + reader.getFile().getName());

        if (!checksumFile.exists()) {
            System.err.println("Checksum-file does not exist.");
            alreadySaid = true;
        }

        final FileReader checksumReader = new FileReader(checksumFile);
        final FileReader saveReader = new FileReader(reader.getFile());

        final String checksum = checksumReader.readFile();

        final byte[] saveFileBytes = prepareForChecksumCreation(saveReader.readFile());
        final String savefileSum = new String(hashCreator.digest(saveFileBytes));

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

        for (final Serializable serializable : consumer) {
            final SJClass data;
            try {
                data = root.getChild(serializable.getDataSetName());
            } catch (final Exception e) {
                System.out.println("Never serialized something for " + serializable.getDataSetName() + " so cannot deserialize for it!");
                continue;
            }
            serializable.deserialize(data);
        }

        return isCorrupt;
    }

    private static byte[] prepareForChecksumCreation(final String input) {
        return removeSpacesAndNewLines(input).getBytes();
    }

    private static String removeSpacesAndNewLines(final String input) {
        return input.replaceAll(" ", "").replaceAll("([\n\r])", "");
    }

    /**
     * Serializes all {@link Serializable}s within {@link #consumer} in a file
     * with the given name relative to a hidden {@link OuterResource}. The
     * extension of the file will be automatically added as {@link
     *
     * @param name the name of the save file
     *
     * @throws IOException when the I/O process with the file fails
     */
    public static void doSerialization(final String name) throws IOException {
        serialize(new FileWriter(SaltySystem.defaultHiddenOuterResource.getFileResource(name + SanjoFile.FILE_EXTENSION)), new SJClass(name));
    }

    /**
     * Calls {@link #doSerialization(String)} with {@link #saveFileName} as the
     * file name.
     *
     * @throws IOException when the I/O process with the file fails
     */
    public static void doSerialization() throws IOException {
        doSerialization(saveFileName);
    }

    /**
     * Deserialize all {@link Serializable}s within {@link #consumer} from a
     * file with the given name relative to a hidden {@link OuterResource}. The
     * extension of the file will be automatically added as {@link
     * SanjoFile#FILE_EXTENSION}.
     *
     * @param name the name of the save file
     *
     * @return whether the savefile is corrupt or not. True means that the
     * savefile was changed after the last writing from Salty Engine. If there
     * wasn't a checksum file, it return true.
     * @throws IOException when the I/O process with the file fails
     */
    public static boolean doDeserialization(final String name) throws IOException {
        final File file = SaltySystem.defaultHiddenOuterResource.getFileResource(name + SanjoFile.FILE_EXTENSION);
        final SanjoParser parser = new SanjoParser(new SanjoFile(file.getAbsolutePath()));

        return deserialize(new FileReader(file), parser.parse());
    }

    /**
     * Calls {@link #doDeserialization(String)} with {@link #saveFileName} as
     * the file name.
     *
     * @return whether the savefile is corrupt or not. If there wasn't a
     * checksum file, it return false.
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

    public static boolean contains(final Serializable o) {
        return consumer.contains(o);
    }

    public static boolean add(final Serializable serializable) {
        return consumer.add(serializable);
    }

    public static boolean remove(final Object o) {
        return consumer.remove(o);
    }

    public static void clear() {
        consumer.clear();
    }

    public static void add(final int index, final Serializable element) {
        consumer.add(index, element);
    }

    public static boolean removeIf(final Predicate<? super Serializable> filter) {
        return consumer.removeIf(filter);
    }

    public static String getSaveFileName() {
        return saveFileName;
    }

    public static void setSaveFileName(final String saveFileName) {
        Serializer.saveFileName = saveFileName;
    }

    public static boolean isAddChecksum() {
        return addChecksum;
    }

    public static void setAddChecksum(final boolean addChecksum) {
        Serializer.addChecksum = addChecksum;
    }
}
