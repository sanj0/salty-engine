/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.stage;

import de.me.edgelord.sjgl.display.Display;
import de.me.edgelord.sjgl.main.MainLoops;

import javax.swing.*;
import java.awt.*;

public class Stage extends JPanel {

    private Display display;
    private MainLoops mainLoops;
    private double currentZoomX = 1;
    private double currentZoomY = 1;

    public Stage(Display display, MainLoops mainLoops) {
        this.display = display;
        this.mainLoops = mainLoops;

        init();
    }

    private void init() {

        setBounds(0, 0, display.getWidth(), display.getHeight());
        setBackground(Color.WHITE);
        display.add(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.scale(currentZoomX, currentZoomY);

        mainLoops.render(graphics2D);
    }

    public void scale(double zoomX, double zoomY) {

        currentZoomX = zoomX;
        currentZoomY = zoomY;
    }
}
