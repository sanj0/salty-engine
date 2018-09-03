package de.edgelord.saltyengine.sgs;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.resource.InnerResource;

public class ScriptedGameObject extends GameObject {

    public ScriptedGameObject(String relativePathToScript) {
        super(0, 0, 0, 0, "de.edgelord.saltyengine.sgs.ScriptedGameObject");

        addComponent(ScriptInterpreter.loadScript(relativePathToScript, new InnerResource(), this, "de.edgelord.saltyengine.sgs.ScriptInterpreter"));
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }
}
