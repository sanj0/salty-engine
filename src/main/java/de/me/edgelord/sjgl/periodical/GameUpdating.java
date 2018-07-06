/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.periodical;

public abstract class GameUpdating extends Periodical {

    @Deprecated
    public GameUpdating(long period) {
        super(period);
    }

    @Override
    public void doMe() {
        gameUpdating();
    }

    public abstract void gameUpdating();
}
