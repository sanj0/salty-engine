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

import de.edgelord.saltyengine.utils.GeneralUtil;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class represents an audio resource.
 * It contains a {@link #clip} which is the actual audio, as well as a {@link #name}.
 *
 * @see AudioPlayer
 * @see Clip
 */
public class Audio {

    /**
     * The id-like name of this audio
     */
    private String name;

    /**
     * The actual audio resource
     */
    private Clip clip;

    /**
     * The volume of the clip, from 0f to 2f.
     */
    private float volume = 1f;

    /**
     * The pan value of te clip, ranging from
     * -1f (completely left) to
     * 1f (completely right)
     */
    private float pan = 0f;

    /**
     * The only constructor taking in all necessary parameters.
     * As a user of this library, you normally never use this, use {@link AudioPlayer#loadNewAudio(String, String)} instead.
     *
     * @param name the {@link #name} of this audio
     * @param clip the {@link #clip}
     */
    public Audio(final String name, final Clip clip) {
        this.name = name;
        this.clip = clip;
    }

    /**
     * @return the {@link #name} of this audio.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the {@link #name} of this audio.
     *
     * @param name the new {@link #name}.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the {@link #clip}.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Sets the {@link #clip} of this audio.
     *
     * @param clip the new audio resource.
     */
    public void setClip(final Clip clip) {
        this.clip = clip;
    }

    /**
     * Sets the position within the clip right to the beginning and plays it one time.
     *
     * @see Clip#start()
     * @see AudioPlayer
     */
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Once called, this method will loop the clip from where ever it is at the moment and won't stop until
     * <code>stop()</code> is called.
     *
     * @see #stop()
     * @see Clip#loop(int)
     * @see AudioPlayer
     */
    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the clip.
     *
     * @see Clip#stop()
     * @see Clip#setFramePosition(int)
     * @see AudioPlayer
     */
    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }

    /**
     * Pauses the clip.
     *
     * @see Clip#stop()
     * @see AudioPlayer
     */
    public void pause() {
        clip.stop();
    }

    /**
     * Sets the volume of the clip, 1f is the normal volume, 0f is completely quiet and 2f is twice as loud.
     *
     * @param volume the new volume of this audio
     */
    public void setVolume(float volume) {

        volume = GeneralUtil.clamp(volume, 0f, 2f);
        FloatControl gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
        this.volume = volume;
    }

    public void setPan(float pan) {

        pan = GeneralUtil.clamp(pan, -1f, 1f);
        FloatControl panControl = (FloatControl) getClip().getControl(FloatControl.Type.PAN);
        panControl.setValue(pan);
        this.pan = pan;
    }

    /**
     * @return the volume of this audio
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Gets {@link #pan}.
     *
     * @return the value of {@link #pan}
     */
    public float getPan() {
        return pan;
    }
}
