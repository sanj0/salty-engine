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

package de.edgelord.saltyengine.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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

    // The volume of the clip
    private float volume = 1f;

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

    /**
     * Sets the volume of the clip, 1f is the normal volume, 0f is completely quiet and 2f is twice as loud.
     *
     * @param volume the new volume of this audio
     */
    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
        this.volume = volume;
    }

    /**
     * @return the volume of this audio
     */
    public float getVolume() {
        return volume;
    }
}
