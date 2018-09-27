package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

public class DebugLogGameObjectStat extends Component {

    private FixedRate fixedRate = new FixedRate(getParent(), "de.edgelord.saltyengine.DebugLogGameObjectStat$1<tmp>", 100);

    public DebugLogGameObjectStat(ComponentParent parent, String name) {
        super(parent, name, Components.CORE_COMPONENT);

        getParent().addComponent(fixedRate);
    }

    private void debugLog() {
        System.out.println("--------------------");
        // System.out.println(getParent().getTag() + " stat:");
        System.out.println();

        System.out.println("-----");
        System.out.println("Transform:");
        System.out.println();
        System.out.println("x position = " + getParent().getX());
        System.out.println("y position = " + getParent().getY());
        System.out.println("width = " + (int) getParent().getWidth() + " <exact = " + getParent().getWidth() + ">");
        System.out.println("height = " + (int) getParent().getHeight() + " <exact = " + getParent().getHeight() + ">");

        System.out.println("-----");
        System.out.println("Components (" + getParent().getComponents().size() + "):");
        System.out.println();

        for (Component component : getParent().getComponents()) {
            if (component.isEnabled()) {
                System.out.println("Enabled Component:");
            } else {
                System.out.println("Disabled Component:");
            }

            System.out.println("tag = " + component.getTag());
            System.out.println("name = " + component.getName());
            System.out.println();
        }

        System.out.println();
    }

    @Override
    public void onFixedTick() {

        if (fixedRate.now()) {
            debugLog();
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(Color.RED);
        saltyGraphics.fillOval(getParent().getX() - 20, getParent().getY() - 20, 20, 20);
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void setGate(int gate) {
        fixedRate.setGate(gate);
    }
}
