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

package de.edgelord.saltyengine.resource;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.io.SystemDependentFiles;
import de.edgelord.saltyengine.utils.SaltySystem;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class InnerResource implements Resource {

    private final ClassLoader classLoader = InnerResource.class.getClassLoader();
    private File tmpDir = null;

    @Override
    public SaltyImage getImageResource(final String relativePath) {

        final String arrangedPath = arrangePath(relativePath);

        try (final InputStream inputStream = classLoader.getResourceAsStream(arrangedPath)) {

            final BufferedImage image = ImageIO.read(inputStream);
            return SaltySystem.createPreferredImage(image);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Clip getAudioResource(final String relativePath) {

        AudioInputStream audioInput = null;

        final String arrangedPath = arrangePath(relativePath);

        try {

            final InputStream inputStream = classLoader.getResourceAsStream(arrangedPath);
            final InputStream bufferedIn = new BufferedInputStream(inputStream);
            audioInput = AudioSystem.getAudioInputStream(bufferedIn);
        } catch (final IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        return Resource.createClip(audioInput);
    }

    @Override
    public File getFileResource(final String relativePath) {
        try {
            if (SaltySystem.writePrivilege) {
                return makeTemporaryFile(relativePath);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private File makeTemporaryFile(final String relativePath) throws IOException {

        checkTmpDir();

        final InputStream inputStream = this.classLoader.getResourceAsStream(this.arrangePath(relativePath));
        final File file = SystemDependentFiles.getUserFile("." + Game.gameName + "/tmp/" + relativePath.replaceAll("/", "."));
        file.createNewFile();

        final OutputStream outputStream = new FileOutputStream(file);
        final byte[] buffer = new byte[4096];

        int readBytes;
        while ((readBytes = Objects.requireNonNull(inputStream).read(buffer)) > 0) {
            outputStream.write(buffer, 0, readBytes);
        }

        inputStream.close();
        outputStream.close();
        return file;
    }

    private void checkTmpDir() {
        if (tmpDir == null) {
            tmpDir = SystemDependentFiles.getUserFile("." + Game.gameName + "/tmp/");

            if (tmpDir.exists()) {
                if (tmpDir.isDirectory()) {
                    final String[] files = tmpDir.list();
                    for (final String file : files) {
                        final File currentFile = new File(tmpDir.getPath(), file);
                        currentFile.delete();
                    }
                }
            } else {
                tmpDir.mkdir();
            }
        }
    }

    private String arrangePath(final String path) {
        if (path.startsWith("/")) {

            return path.replaceFirst("/", "");
        }

        return path;
    }
}
