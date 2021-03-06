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

import java.io.File;

public class FileIO {

    private File file;

    public FileIO(final File file) {
        this.file = file;
    }

    /**
     * Gets {@link #file}.
     *
     * @return the value of {@link #file}
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets {@link #file}.
     *
     * @param file the new value of {@link #file}
     */
    public void setFile(final File file) {
        this.file = file;
    }
}
