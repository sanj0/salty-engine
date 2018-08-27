package testing;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.gameobject.components.DebugLogGameObjectStat;
import de.edgelord.saltyengine.ui.elements.Button;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.event.MouseEvent;

public class PauseButton extends Button {

    public PauseButton() {
        super("Pause", Game.getDisplayManager().getCenter(100, 35), 100, 35);

        /*
        WobblingEffect wobblingEffect = new WobblingEffect(this, "wobblingGFX");
        wobblingEffect.init(5, 5, -5, -5);
        wobblingEffect.startGFX();

        this.setBackgroundColor(Color.orange);
        this.setForegroundColor(Color.white);

        this.addComponent(wobblingEffect);
        */
        this.addComponent(new DebugLogGameObjectStat(this, "debugLog"));
    }

    @Override
    public void onClick(MouseEvent e) {

        if (e.getClickCount() == 2) {
            System.out.println("Exit Game due to double-click onto that pause button");
            System.exit(0);
        }

        if (StaticSystem.isPaused()) {

            System.out.println("Unpause game!");
            StaticSystem.setPaused(false);
        } else {
            System.out.println("Pause game!");
            StaticSystem.setPaused(true);
        }
    }
}
