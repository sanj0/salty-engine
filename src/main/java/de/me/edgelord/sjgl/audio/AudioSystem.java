package de.me.edgelord.sjgl.audio;

import de.me.edgelord.sjgl.factory.AudioFactory;

public class AudioSystem {

    private AudioPlayer audioPlayer;
    private AudioFactory audioFactory;

    public AudioSystem(AudioFactory audioFactory) {
        this.audioFactory = audioFactory;

        audioPlayer = new AudioPlayer(audioFactory);
    }

    public void loadNewAudio(String name, String relativePath){
        audioPlayer.loadNewAudio(name, relativePath);
    }

    public void play(String name){

        audioPlayer.play(name);
    }

    public void loop(String name){

        audioPlayer.loop(name);
    }

    public void stop(String name){

        audioPlayer.stop(name);
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
