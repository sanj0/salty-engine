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

package de.edgelord.saltyengine.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * A non-static utility class for writing text
 * to a given {@link #getFile() file}.
 */
public class FileWriter extends FileIO {

    public FileWriter(File file) {
        super(file);
    }

    /**
     * Overrides the existing content of the file with the given text.
     * If the file doesn't exist, it will be created.
     *
     * @param text the text which the content of the file should be overridden with
     */
    public void writeThrough(String text) throws IOException {

        getFile().createNewFile();

        BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(getFile()));

        bw.write(text);
        bw.close();
    }
}
