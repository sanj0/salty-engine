/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.components.gfx;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.effect.light.Light;
import de.edgelord.saltyengine.effect.light.LightSystem;
import de.edgelord.saltyengine.effect.light.PointLight;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.TransformRelationMode;
import de.edgelord.saltyengine.utils.TransformRelationUtil;

/**
 * This {@link de.edgelord.saltyengine.core.Component} makes a {@link Light} follow its parent using the rule
 * described in {@link #relationToParent} and {@link TransformRelationUtil}.
 * The light and the mode can be set in one of the constructors, if not, the defaults are:
 * {@code TransformRelationMode.CENTRE}
 * and
 * {@code new PointLight(parent.getTransform())} <p>
 *
 * <b>Important:</b> this class extends {@link GFXComponent}, so you will need to enable it first in order to see the light!
 */
@DefaultPlacement(method = DefaultPlacement.Method.TRANSFORM_RELATION)
public class LightComponent extends GFXComponent {

    /**
     * The relation to the parent object.
     */
    private TransformRelationMode relationToParent;

    /**
     * The light.
     */
    private Light light;

    /**
     * The base constructor.
     *
     * @param parent           the parent
     * @param name             the id-name
     * @param relationToParent the relation of the light ot the parent
     * @param light            the light
     */
    public LightComponent(ComponentContainer parent, String name, TransformRelationMode relationToParent, Light light) {
        super(parent, name);

        this.light = light;
        this.relationToParent = relationToParent;
    }

    /**
     * A constructor. Overloads {@link #relationToParent} with {@link TransformRelationMode#CENTRE}.
     *
     * @param parent the parent
     * @param name   the id-name
     * @param light  the light
     */
    public LightComponent(ComponentContainer parent, String name, Light light) {
        this(parent, name, TransformRelationMode.CENTRE, light);
    }

    /**
     * A constructor. Overloads {@link #light} with {@code new PointLight(parent.getTransform())}.
     *
     * @param parent           the parent
     * @param name             the id-name
     * @param relationToParent the light
     */
    public LightComponent(ComponentContainer parent, String name, TransformRelationMode relationToParent) {
        this(parent, name, relationToParent, new PointLight(parent.getTransform()));
    }

    /**
     * A constructor. Overloads {@link #light} with {@code new PointLight(parent.getTransform())}
     * and {@link #relationToParent} with {@link TransformRelationMode#CENTRE}.
     *
     * @param parent the parent
     * @param name   the id-name
     */
    public LightComponent(ComponentContainer parent, String name) {
        this(parent, name, TransformRelationMode.CENTRE, new PointLight((Transform) parent.getTransform().clone()));
    }

    /**
     * Empty implementation.
     *
     * @param saltyGraphics the graphics to draw to
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    /**
     * Repositions the {@link #light} using {@link TransformRelationUtil#positionRelativeTo(TransformRelationMode, Transform, Transform...)}.
     */
    @Override
    public void onFixedTick() {
        TransformRelationUtil.positionRelativeTo(relationToParent, getParent().getTransform(), light.getTransform());
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled) {
            addToLightSystem();
        }
        super.setEnabled(enabled);
    }

    /**
     * Adds the {@link #light} to the {@link Scene#getLightSystem() LightSystem} of the {@link SceneManager#getCurrentScene() current Scene}.
     */
    private void addToLightSystem() {
        LightSystem currentLightSystem = SceneManager.getCurrentScene().getLightSystem();

        if (currentLightSystem == null) {
            throw new NullPointerException("Can't add a LightComponent when the current scene has no LightSystem! Set one by using Scene#setLightSystem!");
        }

        currentLightSystem.addLight(light);
    }

    /**
     * Gets {@link #relationToParent}.
     *
     * @return the value of {@link #relationToParent}
     */
    public TransformRelationMode getRelationToParent() {
        return relationToParent;
    }

    /**
     * Sets {@link #relationToParent}.
     *
     * @param relationToParent the new value of {@link #relationToParent}
     */
    public void setRelationToParent(TransformRelationMode relationToParent) {
        this.relationToParent = relationToParent;
    }

    /**
     * Gets {@link #light}.
     *
     * @return the value of {@link #light}
     */
    public Light getLight() {
        return light;
    }

    /**
     * Sets {@link #light}.
     *
     * @param light the new value of {@link #light}
     */
    public void setLight(Light light) {
        this.light = light;
    }
}
