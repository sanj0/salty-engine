package de.me.edgelord.sjgl.audio;

import javax.sound.sampled.Clip;

public class Audio {

    private String name;
    private Clip clip;

    public Audio(String name, Clip clip) {
        this.name = name;
        this.clip = clip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
