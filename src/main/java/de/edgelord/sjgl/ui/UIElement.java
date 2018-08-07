package de.edgelord.sjgl.ui;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.utils.StaticSystem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIElement extends GameObject {

    private Font font = StaticSystem.font;

    public UIElement(Coordinates coordinates, int width, int height) {
        super(coordinates, width, height, "UIElement");
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCollision(CollisionEvent e) {
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {
    }

    public abstract void draw(Graphics2D graphics);

    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void keyTyped(KeyEvent e);

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
