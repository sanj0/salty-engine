/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.resource;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.systemdependentfiles.SystemDependentFiles;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class InnerResource implements Resource {

    private final ClassLoader classLoader = InnerResource.class.getClassLoader();

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

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInput);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        return clip;
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

        File file = SystemDependentFiles.getUserFile("." + Game.gameName + "TmpFiles/" + relativePath.replaceAll("/", "."));

        file.createNewFile();

        OutputStream outputStream = new FileOutputStream(file);

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);

        outputStream.write(buffer);

        return file;
    }

    private void checkTmpDir() {
        File tmp = SystemDependentFiles.getUserFile("." + Game.gameName + "TmpFiles/");

        if (tmp.exists()) {
            if (!tmp.isDirectory()) {
                System.err.println("ERROR: TMP Directory is a file!");
            }
        } else {
            tmp.mkdir();
        }
    }

    private String arrangePath(String path) {
        if (path.startsWith("/")) {

            return path.replaceFirst("/", "");
        }

        return path;
    }
}
