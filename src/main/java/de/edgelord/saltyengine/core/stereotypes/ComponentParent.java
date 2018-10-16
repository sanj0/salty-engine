package de.edgelord.saltyengine.core.stereotypes;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;

import java.util.List;

public abstract class ComponentParent implements TransformedObject {

    private String tag;

    public ComponentParent(String tag) {
        this.tag = tag;
    }

    /**
     * Adds the given {@link Component}
     *
     * @param component the component to add
     */
    public abstract void addComponent(Component component);

    /**
     * Removes a {@link Component} by searching for the one with the given name
     * and remove that one
     *
     * @param identifier the identifier of the component to be removed
     */
    public abstract void removeComponent(String identifier);

    /**
     * Removes the given {@link Component}
     * The implementation should be different from {@link #getComponent(String)}
     * due to better performance possible when directly removing a Component
     *
     * @param component the {@link Component} to be removed
     */
    public abstract void removeComponent(Component component);

    /**
     * Returns the {@link List} of {@link Component}s
     *
     * @return the {@link List} of {@link Component}s
     */
    public abstract List<Component> getComponents();

    /**
     * Returns the {@link Component} with the given identifier
     *
     * @param identifier the identifier of the {@link Component} to be returned
     * @return the requested {@link Component}
     */
    public abstract Component getComponent(String identifier);

    /**
     * Calls the method {@link Component#onFixedTick()} for every {@link Component}.
     */
    public void doComponentOnFixedTick() {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.onFixedTick();
            }
        });
    }

    /**
     * Calls the method {@link Component#draw(SaltyGraphics)} for every component with the given {@link SaltyGraphics}
     *
     * @param graphics the graphics context to draw the components
     */
    public void doComponentDrawing(SaltyGraphics graphics) {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.draw(graphics);
            }
        });
    }

    /**
     * Places this object in the middle of the {@link de.edgelord.saltyengine.core.Host} {@link Game#host}
     */
    public void centrePosition() {
        setPosition(Game.getHost().getCentrePosition(getDimensions()));
    }

    /**
     * Centres this object horizontally in the {@link de.edgelord.saltyengine.core.Host} {@link Game#host}
     */
    public void centreHorizontalPosition() {
        setX(Game.getHost().getHorizontalCentrePosition(getWidth()));
    }

    /**
     * Centres this object vertically in the {@link de.edgelord.saltyengine.core.Host} {@link Game#host}
     */
    public void centreVerticalPosition() {
        setX(Game.getHost().getVerticalCentrePosition(getHeight()));
    }

    @Override
    public abstract Transform getTransform();

    @Override
    public abstract void setTransform(Transform transform);

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
