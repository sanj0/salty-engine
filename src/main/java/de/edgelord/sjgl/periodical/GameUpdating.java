/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.periodical;

@Deprecated
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
