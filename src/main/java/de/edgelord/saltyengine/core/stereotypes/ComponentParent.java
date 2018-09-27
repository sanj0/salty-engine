package de.edgelord.saltyengine.core.stereotypes;

import de.edgelord.saltyengine.core.Component;
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

    public void doComponentOnFixedTick() {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.onFixedTick();
            }
        });
    }

    public void doComponentDrawing(SaltyGraphics graphics) {
        getComponents().forEach(component -> {
            if (component.isEnabled()) {
                component.draw(graphics);
            }
        });
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
