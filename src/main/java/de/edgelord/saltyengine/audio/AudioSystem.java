/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.audio;

import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.resource.InnerResource;

/**
 * This class is not really necessary, it has no special functionality, it has only organizational reasons
 */
public class AudioSystem {

    // The AudioPlayer from which to operate with Audios
    private AudioPlayer audioPlayer;

    // The AudioFactory from which to load new Audios
    private AudioFactory audioFactory;

    /**
     * A overload constructor, with an <code>AudioFactory</code> with an <code>InnerResource</code> as default.
     */
    public AudioSystem() {
        this(new AudioFactory(new InnerResource()));
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


    public void loadNewAudio(String name, String relativePath) {
        audioPlayer.loadNewAudio(name, relativePath);
    }

    public void play(String name) {

        audioPlayer.play(name);
    }

    public void loop(String name) {

        audioPlayer.loop(name);
    }

    public void stop(String name) {

        audioPlayer.stop(name);
    }

    public void setClipVolume(String name, float volume) {
        audioPlayer.setClipVolume(name, volume);
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
