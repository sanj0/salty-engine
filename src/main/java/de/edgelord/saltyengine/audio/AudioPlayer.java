/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package de.edgelord.saltyengine.audio;

import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.OuterResource;

import javax.sound.sampled.FloatControl;
import java.util.LinkedList;

/**
 * <code>AudioPlayer</code> is basically a collection of <code>Audio</code>.
 * It has evey necessary method of <code>Audio</code> implemented in a way that it searches for the id-name
 * and then does the specific action, e.g. playing it.
 */
public class AudioPlayer {

    // The list of Audio which correspondents to this AudioPlayer
    private LinkedList<Audio> audios = new LinkedList<>();

    /**
     * The <code>AudioFactory</code> which is used for getting the actual <code>Clip</code>
     * from whether within the .jar (<code>InnerResource</code>) or from a directory in the users home
     * folder (macOs: <code>/Users/currentUser</code>, linux: <code>/home/currentUser</code>)
     *
     * @see InnerResource
     * @see OuterResource
     * @see de.edgelord.saltyengine.factory.Factory
     */
    private AudioFactory audioFactory;


    /**
     * The only constructor of AudioPlayer, taking in the <code>AudioFactory</code>
     * from which the Clips will be loaded.
     *
     * @param audioFactory the <code>Factory</code> from which to get the Clips for the <code>Audio</code>
     * @see #loadNewAudio(String, String)
     * @see de.edgelord.saltyengine.factory.Factory
     * @see AudioFactory
     */
    public AudioPlayer(AudioFactory audioFactory) {
        this.audioFactory = audioFactory;
    }

    /**
     * This method loads a new <code>Clip</code> from the <code>AudioFactory</code> into a new <code>Audio</code>
     * together with the name and adds it to the list.
     *
     * @param name         the id-name for the new <code>Audio</code>
     * @param relativePath the relative path from which the <code>AudioFactory</code> should read the <code>Clip</code>
     * @see AudioFactory
     * @see javax.sound.sampled.Clip
     * @see InnerResource
     * @see OuterResource
     */
    public void loadNewAudio(String name, String relativePath) {

        audios.add(new Audio(name, audioFactory.getClip(relativePath)));
    }

    /**
     * Searches for an <code>Audio</code> with the given name in the list and when found playing it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>play()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be played
     * @see Audio#play()
     */
    public void play(String name) {

        getAudio(name).play();
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found looping it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>loop()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be looped
     * @see Audio#loop()
     */
    public void loop(String name) {

        getAudio(name).loop();
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found stopping it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>stop()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be stopped
     * @see Audio#stop()
     */
    public void stop(String name) {

        getAudio(name).stop();
    }

    /**
     * Returns the Audio from the list with the given name
     *
     * @param name the name of the Audio to return
     * @return the audio with the given name
     */
    public Audio getAudio(String name) {

        for (Audio audio : this.audios) {
            if (audio.getName().equals(name)) {
                return audio;
            }
        }
        return null;
    }

    /**
     * Searches for the Audio with the given name and sets its Volume to the given one, with
     * 0f meaning no volume and 1f meaning full volume.
     *
     * @param name   the name of the Audio of which to change the Volume
     * @param volume the target volume, 0f is min, 1f is max
     */
    public void setClipVolume(String name, float volume) {

        FloatControl gainControl = (FloatControl) getAudio(name).getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
