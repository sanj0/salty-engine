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

package de.edgelord.saltyengine.resource;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.systemdependentfiles.SystemDependentFiles;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OuterResource implements Resource {

    @Override
    public BufferedImage getImageResource(String relativePath) {

        if (relativePath.startsWith("/")) {

            try {
                return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + relativePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try {
                return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + "/" + relativePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // If the return statement above fails, return a new empty BufferedImage with 314 x 314 pixels and ImageType 1

        return new BufferedImage(314, 314, 1);
    }

    @Override
    public Clip getAudioResource(String relativePath) {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getFile(relativePath));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clip;
    }

    @Override
    public File getFileResource(String relativePath) {
        return getFile(relativePath);
    }

    private boolean hidden;
    private File sourceDirectory;

    public OuterResource(boolean hidden) {

        this.hidden = hidden;

        prepareSourceDirectory();

        if (!sourceDirectory.exists()) {
            sourceDirectory.mkdir();
        }
    }

    private void prepareSourceDirectory() {

        if (hidden) {

            sourceDirectory = SystemDependentFiles.getUserFile("." + Game.gameName);
        } else {

            sourceDirectory = SystemDependentFiles.getUserFile(Game.gameName);
        }
    }

    private File getFile(String relativePath) {

        if (relativePath.startsWith("/")) {

            return new File(sourceDirectory.getAbsolutePath() + relativePath);
        } else {

            return new File(sourceDirectory.getAbsolutePath() + "/" + relativePath);
        }
    }

    public BufferedImage getImage(String relativePath) throws IOException {

        if (relativePath.startsWith("/")) {

            return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + relativePath));
        } else {

            return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + "/" + relativePath));
        }
    }
}
