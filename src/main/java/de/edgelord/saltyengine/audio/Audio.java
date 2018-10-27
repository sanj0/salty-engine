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

package de.edgelord.saltyengine.audio;

import javax.sound.sampled.Clip;

/**
 * This class represents a audio resource.
 * It contains a Clip which is the actual audio, as well as a name, which is kind of an id
 *
 * @see AudioSystem
 * @see AudioPlayer
 * @see Clip
 */
public class Audio {

    // The id-like name of the audio (can be anything, but normally it would be like a variable name)
    private String name;

    // The actual audio
    private Clip clip;

    public Audio(final String name, final Clip clip) {
        this.name = name;
        this.clip = clip;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(final Clip clip) {
        this.clip = clip;
    }

    /**
     * Sets the position within the clip RIGHT to the beginning and plays it one time
     *
     * @see Clip#start()
     * @see AudioPlayer
     * @see AudioSystem
     */
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Once called, this method will play the clip from where ever it is at the moment and won't stop until
     * <code>stop()</code> is called
     *
     * @see #stop()
     * @see Clip#loop(int)
     * @see AudioPlayer
     * @see AudioSystem
     */
    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the clip
     *
     * @see Clip#stop()
     * @see AudioPlayer
     * @see AudioSystem
     */
    public void stop() {
        clip.stop();
    }
}
