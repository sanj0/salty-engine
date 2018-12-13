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

import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.utils.SaltySystem;

/**
 * This class is not really necessary, it has no special functionality, it has only organizational reasons
 */
public class AudioSystem {

    // The AudioPlayer from which to operate with Audios
    private AudioPlayer audioPlayer;

    // The AudioFactory from which to load new Audios
    private AudioFactory audioFactory;

    /**
     * A overload constructor, with an <code>AudioFactory</code> using an <code>InnerResource</code> as default.
     */
    public AudioSystem() {
        this(new AudioFactory(SaltySystem.defaultImageFactory.getResource()));
    }

    /**
     * The standard constructor.
     *
     * @param audioFactory the <code>AudioFactory</code> from which to read new <code>Audio</code>
     */
    public AudioSystem(AudioFactory audioFactory) {
        this.audioFactory = audioFactory;

        audioPlayer = new AudioPlayer(audioFactory);
    }

    /**
     * Loads a new Audio from the <code>relativePath</code> using the
     * {@link #audioFactory} and adds it to the {@link #audioPlayer} with the given name.
     *
     * @param name         the id-name of the audio to be added.
     * @param relativePath the path relative to the {@link #audioFactory} of the audio file.
     * @see AudioPlayer#loadNewAudio(String, String)
     * @see AudioFactory#getClip(String)
     */
    public void loadNewAudio(String name, String relativePath) {
        audioPlayer.loadNewAudio(name, relativePath);
    }

    /**
     * Plays the {@link Audio} with the given name from the {@link #audioPlayer}
     * from its current position to its end.
     *
     * @param name the id-name of the {@link Audio} to be played.
     * @see AudioPlayer#play(String)
     */
    public void play(String name) {

        audioPlayer.play(name);
    }

    /**
     * Loops the {@link Audio} from the {@link #audioPlayer} from its current position till
     * {@link #stop(String)} is called for this specific {@link Audio}.
     *
     * @param name the id-name of the {@link Audio} to be looped.
     * @see AudioPlayer#loop(String)
     */
    public void loop(String name) {

        audioPlayer.loop(name);
    }

    /**
     * Stops the {@link Audio} with the given name.
     *
     * @param name the id-name of the {@link Audio} to be stopped.
     * @see AudioPlayer#stop(String)
     */
    public void stop(String name) {

        audioPlayer.stop(name);
    }

    /**
     * Sets the volume of the {@link Audio} with the given name.
     * A <code>volume</code> of 1f means the default Volume,
     * A <code>volume</code> of 0f means no sound at all
     * A <code>volume</code> of 2f means twice the default volume.
     * <p>
     * You can use any value in between 0f and 2f.
     *
     * @param name   the id-name of the {@link Audio} whose volume is to be set
     * @param volume the target volume of the {@link Audio}
     * @throws IllegalArgumentException This methods throws a {@link IllegalArgumentException} if the given volume is
     *                                  less than 0f or greater than 2f.
     * @see AudioPlayer#setClipVolume(String, float)
     */
    public void setClipVolume(String name, float volume) {

        if (volume < 0f || volume > 2f) {

            if (volume < 0f) {
                throw new IllegalArgumentException("The volume of " + volume + " is illegal. The volume of a Clip can't be less than 0f");
            } else {
                throw new IllegalArgumentException("The volume of " + volume + " is illegal. The volume of a Clip can't be greater than 2f");
            }
        } else {
            audioPlayer.setClipVolume(name, volume);
        }
    }

    /**
     * Returns the volume of the given clip by calling {@link AudioPlayer#getClipVolume(String)}
     *
     * @param name the name of the audio
     * @return the volume of the audio
     */
    public float getClipVolume(String name) {
        return audioPlayer.getClipVolume(name);
    }

    /**
     * Sets the master volume of the {@link AudioPlayer} of this system by using {@link AudioPlayer#setMasterVolume(float)}.
     * That effects all {@link Audio}s, but they're relative volume will stay the same.
     *
     * @param volume the new master volume
     */
    public void setMasterVolume(float volume) {
        audioPlayer.setMasterVolume(volume);
    }

    /**
     * @return the master volume of {@link #audioPlayer}.
     * @see AudioPlayer#setMasterVolume(float)
     * @see AudioPlayer#getMasterVolume()
     * @see #setMasterVolume(float)
     */
    public float getMasterVolume() {
        return audioPlayer.getMasterVolume();
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public AudioFactory getAudioFactory() {
        return audioFactory;
    }

    public void setResource(AudioFactory audioFactory) {
        this.audioFactory = audioFactory;
    }
}
