/*
 * Copyright 2020 Malte Dostal
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

import de.edgelord.saltyengine.io.FileReader;

import java.io.File;
import java.io.IOException;

public class DataReader {

    public static String SDB_FILE_EXTENSION = ".sdb";
    private final String fileContent;
    private final String fileName;
    private final File file;

    public DataReader(File file) throws IOException {
        this(new FileReader(file));
    }

    public DataReader(FileReader fileReader) throws IOException {

        this.fileContent = fileReader.readFile();
        this.file = fileReader.getFile();

        fileName = fileReader.getFile().getName().replace(SDB_FILE_EXTENSION, "");
    }

    public static String getAsSpeciesStart(String name) {
        return "{" + name + "}";
    }

    public static String getAsSpeciesEnd(String name) {
        return "{*" + name + "}";
    }

    public static String getAsTagStart(String name) {
        return "(" + name + ")";
    }

    public static String getAsTagEnd(String name) {
        return "(*" + name + ")";
    }

    public DataWriter getDataWriter() throws IOException {
        return new DataWriter(file);
    }

    /**
     * Returns a Species with the given name and content by finding it in the File.
     *
     * @param speciesName the Species name
     * @return the Species with proper name and content
     * @see Species
     */
    public Species getSpecies(String speciesName) {
        return new Species(speciesName, splitString(fileContent, getAsSpeciesStart(speciesName), getAsSpeciesEnd(speciesName)));
    }

    /**
     * Returns the "Main"species by finding the species whose name is the name of the file without the .sdb extension
     *
     * @return the "Main"species with its proper content
     * @see Species
     */
    public Species getMainSpecies() {
        return new Species(fileName, splitString(fileContent, getAsSpeciesStart(fileName), getAsSpeciesEnd(fileName)));
    }

    /**
     * Returns the value of a specific tag within the whole file.
     *
     * @param tag the name of the tag whose value gets returned
     * @return the value of the specific tag
     */
    public String getTagValue(String tag) {
        return splitString(fileContent, "(" + tag + ")", "(*" + tag + ")");
    }

    private String splitString(String base, String start, String end) {
        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }

    public String getFileContent() {
        return fileContent;
    }

    public File getFile() {
        return file;
    }
}
