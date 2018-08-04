package de.edgelord.sjgl.audio;

import de.edgelord.sjgl.factory.AudioFactory;
import de.edgelord.sjgl.resource.InnerResource;
import de.edgelord.sjgl.resource.OuterResource;

import java.util.LinkedList;

/**
 * <code>AudioPlayer</code> is basically a collection of <code>Audio</code>.
 * It has evey necessary method of <code>Audio</code> implemented in a way that it searches for the id-name
 * and then does the specific action, e.g. playing it.
 */
public class AudioPlayer {

    // The list of Audio which correspondents to this AudioPlayer
    private LinkedList<Audio> audios = new LinkedList<>();

    /**
     * The <code>AudioFactory</code> which is used for getting the actual <code>Clip</code>
     * from whether within the .jar (<code>InnerResource</code>) or from a directory in the users home
     * folder (macOs: <code>/Users/currentUser</code>, linux: <code>/home/currentUser</code>)
     *
     * @see InnerResource
     * @see OuterResource
     * @see de.edgelord.sjgl.factory.Factory
     */
    private AudioFactory audioFactory;


    /**
     * The only constructor of AudioPlayer, taking in the <code>AudioFactory</code>
     * from which the Clips will be loaded.
     *
     * @param audioFactory the <code>Factory</code> from which to get the Clips for the <code>Audio</code>
     * @see #loadNewAudio(String, String)
     * @see de.edgelord.sjgl.factory.Factory
     * @see AudioFactory
     */
    public AudioPlayer(AudioFactory audioFactory) {
        this.audioFactory = audioFactory;
    }

    /**
     * This method loads a new <code>Clip</code> from the <code>AudioFactory</code> into a new <code>Audio</code>
     * together with the name and adds it to the list.
     *
     * @param name the id-name for the new <code>Audio</code>
     * @param relativePath the relative path from which the <code>AudioFactory</code> should read the <code>Clip</code>
     *
     * @see AudioFactory
     * @see javax.sound.sampled.Clip
     * @see InnerResource
     * @see OuterResource
     */
    public void loadNewAudio(String name, String relativePath){

        audios.add(new Audio(name, audioFactory.getClip(relativePath)));
    }

    /**
     * Searches for an <code>Audio</code> with the given name in the list and when found playing it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>play()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be played
     *
     * @see Audio#play()
     */
    public void play(String name){

        for (Audio audio : this.audios){

            if (audio.getName().equals(name)){
                audio.play();
                return;
            }
        }
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found looping it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>loop()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be looped
     *
     * @see Audio#loop()
     */
    public void loop(String name){

        for (Audio audio : this.audios){

            if (audio.getName().equals(name)){
                audio.loop();
                return;
            }
        }
    }

    /**
     * Searches for a <code>Audio</code> with the given name in the list and when found stopping it.
     * For performance reasons, the methods returns after the first found <code>Audio</code> and triggering its
     * <code>stop()</code> method, so adding multiple <code>Audio</code> with the same name won't act as maybe excepted.
     *
     * @param name the id-name of the <code>Audio</code> which should be stopped
     *
     * @see Audio#stop()
     */
    public void stop(String name){

        for (Audio audio : this.audios){

            if (audio.getName().equals(name)){
                audio.stop();
                return;
            }
        }
    }
}
