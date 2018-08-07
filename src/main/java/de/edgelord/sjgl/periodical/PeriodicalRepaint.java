/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.periodical;

import de.edgelord.sjgl.display.DisplayManager;
import de.edgelord.sjgl.utils.GameStats;

@Deprecated
public class PeriodicalRepaint extends Periodical {

    private DisplayManager displayManager;
    private GameUpdating gameUpdating = null;

    public PeriodicalRepaint(long period, DisplayManager displayManager) {
        super(period);

        this.displayManager = displayManager;
    }

    public PeriodicalRepaint(long period, DisplayManager displayManager, GameUpdating gameUpdating) {
        super(period);

        this.displayManager = displayManager;
        this.gameUpdating = gameUpdating;
    }

    @Override
    public void doMe() {

        if (!GameStats.isPaused() && getGameUpdating() != null) {

            gameUpdating.gameUpdating();
        }

        displayManager.repaintStage();
    }

    public DisplayManager getDisplayManager() {
        return displayManager;
    }

    public void setDisplayManager(DisplayManager displayManager) {
        this.displayManager = displayManager;
    }

    public GameUpdating getGameUpdating() {
        return gameUpdating;
    }

    public void setGameUpdating(GameUpdating gameUpdating) {
        this.gameUpdating = gameUpdating;
    }
}
