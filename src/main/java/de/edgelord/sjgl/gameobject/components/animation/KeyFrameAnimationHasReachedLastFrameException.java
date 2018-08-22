/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components.animation;

public class KeyFrameAnimationHasReachedLastFrameException extends Exception {

    public KeyFrameAnimationHasReachedLastFrameException() {
        super("A KeyFrameAnimation has already reached the last frame but the next one is requested. To restart a KeyFrameAnimation call calculateAnimation().");
    }
}
