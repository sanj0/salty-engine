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
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.util.LinkedList;

/**
 * An interface for handling multiple {@link Audio}.
 */
public class AudioPlayer {

    /**
     * The list of loaded {@link Audio}.
     */
    private LinkedList<Audio> audios = new LinkedList<>();

    /**
     * The {@link AudioFactory} which is used for loading the audio.
     *
     * @see InnerResource
     * @see OuterResource
     * @see de.edgelord.saltyengine.factory.Factory
     */
    private AudioFactory audioFactory;

    /**
     * The master volume of all {@link Audio} obtained by this player.
     */
    private float masterVolume = 1f;

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

        if (SaltySystem.audioEnabled) {
            audios.add(new Audio(name, audioFactory.getClip(relativePath)));
            multiplyAudioVolume(getAudio(name), masterVolume);
        }
    }

    /**
     * Searches for an <code>Audio</code> with the given name in the list and when found plays it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>play()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be played
     * @see Audio#play()
     */
    public void play(String name) {
        if (SaltySystem.audioEnabled) {
            getAudio(name).play();
        }
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found loops it.
     * The methods returns after the first found <code>Audio</code> and triggering its
     * <code>loop()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be looped
     * @see Audio#loop()
     */
    public void loop(String name) {
        if (SaltySystem.audioEnabled) {
            getAudio(name).loop();
        }
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found stops it.
     * The methods returns after the first found <code>Audio</code> and triggering its
     * <code>stop()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be stopped
     * @see Audio#stop()
     */
    public void stop(String name) {
        if (SaltySystem.audioEnabled) {
            getAudio(name).stop();
        }
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found pauses it.
     * The methods returns after the first found <code>Audio</code> and triggering its
     * <code>pause()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be paused
     * @see Audio#pause()
     */
    public void pause(String name) {
        if (SaltySystem.audioEnabled) {
            getAudio(name).pause();
        }
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
     * Searches for the Audio with the given name and sets its volume to the given one using
     * {@link Audio#setVolume(float)}
     *
     * @param name   the name of the {@link Audio} of which to change the volume
     * @param volume the target volume
     * @see Audio#setVolume(float)
     */
    public void setClipVolume(String name, float volume) {
        if (SaltySystem.audioEnabled) {
            getAudio(name).setVolume(volume);
        }
    }

    /**
     * Returns the volume of the audio with the given name.
     *
     * @param name the name of the desired {@link Audio}
     * @return the volume of the audio
     * @see Audio#getVolume()
     */
    public float getClipVolume(String name) {
        return getAudio(name).getVolume();
    }

    /**
     * Searches for the Audio with the given name and sets its pan to the given one using
     * {@link Audio#setPan(float)}
     *
     * @param name the name of the {@link Audio} of which to change the pan
     * @param pan  the target pan
     * @see Audio#setPan(float)
     */
    public void setClipPan(String name, float pan) {
        if (SaltySystem.audioEnabled) {
            getAudio(name).setPan(pan);
        }
    }

    /**
     * Returns the pan of the audio with the given name.
     *
     * @param name the name of the desired {@link Audio}
     * @return the pan of the audio
     * @see Audio#getPan() ()
     */
    public float getClipPan(String name) {
        return getAudio(name).getPan();
    }

    /**
     * Sets the master volume for this player. All {@link Audio}s will be affected, but they're relative volume will stay the same.
     * Example:
     * <p>
     * Audio 1 having a volume of 0.75 and Audio 2 a volume of 1.5. <br>
     * Audio 2 is twice as loud as Audio 1, and when the master volume is set to 1.25,
     * Audio 1 has a volume of 0.9375 and Audio 2 1.875. Still twice as loud.
     *
     * <p>When an audio's volume would be greater than 2f or smaller than 0f with the new master volume, it will be set to 2f or 0f, which is the max volume.
     *
     * @param masterVolume the new master volume
     */
    public void setMasterVolume(float masterVolume) {

        if (SaltySystem.audioEnabled) {
            this.masterVolume = masterVolume;

            for (Audio audio : audios) {
                multiplyAudioVolume(audio, masterVolume);
            }
        }
    }

    /**
     * @return the {@link #masterVolume}
     */
    public float getMasterVolume() {
        return masterVolume;
    }

    /**
     * A private method that multiplies the volume of the given {@link Audio} with the given factor
     * and  makes sure that it won't be greater than 2f nor smaller than 0f.
     *
     * @param audio  the {@link Audio} whose volume is to be multiplied
     * @param factor the factor by which the volume is to be multiplied.
     */
    private void multiplyAudioVolume(Audio audio, float factor) {
        float newVolume = audio.getVolume() * factor;

        if (newVolume < 0f) {
            audio.setVolume(0f);
        } else if (newVolume > 2f) {
            audio.setVolume(2f);
        } else {
            audio.setVolume(newVolume);
        }
    }
}
