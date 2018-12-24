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
import java.io.File;
import java.io.IOException;

public class OuterResource implements Resource {

    private boolean hidden;
    private File sourceDirectory;

    public OuterResource(boolean hidden) {

        this.hidden = hidden;

        prepareSourceDirectory();

        if (!sourceDirectory.exists()) {
            sourceDirectory.mkdir();
        }
    }

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
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        return Resource.createClip(audioInputStream);
    }

    @Override
    public File getFileResource(String relativePath) {
        return getFile(relativePath);
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
