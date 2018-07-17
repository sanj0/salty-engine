package de.me.edgelord.sjgl.audio;

import de.me.edgelord.sjgl.factory.AudioFactory;

import java.util.LinkedList;

public class AudioPlayer {

    private LinkedList<Audio> audios = new LinkedList<>();
    private AudioFactory audioFactory;

    public AudioPlayer(AudioFactory audioFactory) {
        this.audioFactory = audioFactory;
    }

    public void loadNewAudio(String name, String relativePath){

        audios.add(new Audio(name, audioFactory.getClip(relativePath)));
    }

    public void play(String name){

        for (Audio audio : this.audios){

            if (audio.getName().equals(name)){
                audio.play();
            }
        }
    }

    public void loop(String name){

        for (Audio audio : this.audios){

            if (audio.getName().equals(name)){
                audio.loop();
            }
        }
    }

    public void stop(String name){

        for (Audio audio : this.audios){

            if (audio.getName().equals(name)){
                audio.stop();
            }
        }
    }
}
