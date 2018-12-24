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
import de.edgelord.systemdependentfiles.SystemDependentFiles;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.*;

public class InnerResource implements Resource {

    private final ClassLoader classLoader = InnerResource.class.getClassLoader();
    private File tmpDir = null;

    @Override
    public BufferedImage getImageResource(String relativePath) {

        String arrangedPath = arrangePath(relativePath);

        try (InputStream inputStream = classLoader.getResourceAsStream(arrangedPath)) {

            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the return statement above fails, return a new empty BufferedImage with 314 x 314 pixels and ImageType 1

        return new BufferedImage(314, 314, 1);
    }

    @Override
    public Clip getAudioResource(String relativePath) {

        AudioInputStream audioInput = null;

        String arrangedPath = arrangePath(relativePath);

        try {

            InputStream inputStream = classLoader.getResourceAsStream(arrangedPath);
            InputStream bufferedIn = new BufferedInputStream(inputStream);
            audioInput = AudioSystem.getAudioInputStream(bufferedIn);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        return Resource.createClip(audioInput);
    }

    @Override
    public File getFileResource(String relativePath) {

        try {
            return makeTemporaryFile(relativePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private File makeTemporaryFile(String relativePath) throws IOException {

        checkTmpDir();

        InputStream inputStream = classLoader.getResourceAsStream(arrangePath(relativePath));

        File file = SystemDependentFiles.getUserFile("." + Game.gameName + "/tmp/" + relativePath.replaceAll("/", "."));

        file.createNewFile();

        OutputStream outputStream = new FileOutputStream(file);

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        outputStream.write(buffer);

        return file;
    }

    private void checkTmpDir() {
        if (tmpDir == null) {
            tmpDir = SystemDependentFiles.getUserFile("." + Game.gameName + "/tmp/");

            if (tmpDir.exists()) {
                if (tmpDir.isDirectory()) {
                    String[] files = tmpDir.list();
                    for (String file : files) {
                        File currentFile = new File(tmpDir.getPath(), file);
                        currentFile.delete();
                    }
                }
            } else {
                tmpDir.mkdir();
            }
        }
    }

    private String arrangePath(String path) {
        if (path.startsWith("/")) {

            return path.replaceFirst("/", "");
        }

        return path;
    }
}
