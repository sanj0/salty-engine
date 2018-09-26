package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.core.Component;

import java.util.List;

public interface ComponentParent {

    /**
     * Adds the given {@link Component}
     *
     * @param component the component to add
     */
    void addComponent(Component component);

    /**
     * Removes a {@link Component} by searching for the one with the given name
     * and remove that one
     *
     * @param identifier the identifier of the component to be removed
     */
    void removeComponent(String identifier);

    /**
     * Removes the given {@link Component}
     * The implementation should be different from {@link #getComponent(String)}
     * due to better performance possible when directly removing a Component
     *
     * @param component the {@link Component} to be removed
     */
    void removeComponent(Component component);

    /**
     * Returns the {@link List} of {@link Component}s
     *
     * @return the {@link List} of {@link Component}s
     */
    List<Component> getComponents();

    /**
     * Returns the {@link Component} with the given identifier
     *
     * @param identifier the identifier of the {@link Component} to be returned
     * @return the requested {@link Component}
     */
    Component getComponent(String identifier);
}
