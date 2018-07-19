package de.me.edgelord.sjgl.audio;

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

    /**
     * Sets the position within the clip roght to the beginning and plays it one time
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
