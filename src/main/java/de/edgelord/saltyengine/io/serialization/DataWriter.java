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

import de.edgelord.saltyengine.io.FileWriter;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

@Deprecated
public class DataWriter {

    private final LinkedList<Species> speciesList = new LinkedList<>();
    private FileWriter fileWriter;

    public DataWriter(final File file) throws IOException {
        this(new FileWriter(file));
    }

    public DataWriter(final FileWriter fileWriter) throws IOException {

        if (SaltySystem.writePrivilege) {
            if (!fileWriter.getFile().exists()) {
                fileWriter.getFile().createNewFile();
            }
        }

        this.fileWriter = fileWriter;
    }

    /**
     * Adds the given Species to the list of Species which will be written to
     * the file when requested
     *
     * @param species the Species which should be added to the list
     */
    public void addSpecies(final Species species) {
        speciesList.add(species);
    }

    /**
     * Writes the content of all Species from the list and their subspecies to
     * the file. With that, all existing content of the file will be
     * overwritten.
     *
     * @throws IOException when the file could not be written to
     */
    public void syncFile() throws IOException {
        final StringBuilder contentBuilder = new StringBuilder();
        for (final Species species : speciesList) {
            contentBuilder.append(species.getSyntax());
        }

        fileWriter.writeThrough(contentBuilder.toString());
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(final FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public File getFile() {
        return getFileWriter().getFile();
    }
}
