package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.hitbox.SimpleHitbox;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

public class ResizeCage extends GameObject {

    public static final String TAG = "go-resize-cage";

    private final TransformedObject subject;
    private final float handleSize;
    private final float handleSizeDiv2;
    private final Color handleColor;

    private Transform handle0;//top left
    private Transform handle1;//top right
    private Transform handle2;//bottom right
    private Transform handle3;//bottom left

    private Transform draggedHandle = null;
    private Vector2f dragOffset = Vector2f.zero();

    public ResizeCage(final TransformedObject subject, final float handleSize, final Color handleColor) {
        super(0, 0, 0, 0, TAG);

        this.subject = subject;
        this.handleSize = handleSize;
        this.handleSizeDiv2 = handleSize / 2f;
        this.handleColor = handleColor;

        final float subjectMaxX = subject.getTransform().getMaxX();
        final float subjectMaxY = subject.getTransform().getMaxY();

        handle0 = new Transform(subject.getX() - handleSizeDiv2, subject.getY() - handleSizeDiv2, handleSize, handleSize);
        handle1 = new Transform(subjectMaxX - handleSizeDiv2, subject.getY() - handleSizeDiv2, handleSize, handleSize);
        handle2 = new Transform(subjectMaxX - handleSizeDiv2, subjectMaxY - handleSizeDiv2, handleSize, handleSize);
        handle3 = new Transform(subject.getX() - handleSizeDiv2, subjectMaxY - handleSizeDiv2, handleSize, handleSize);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCollision(CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {
        if (Input.mouseDown && draggedHandle == null) {
            final Transform cursor = Input.getCursor();
            // aaaaaaaaaah
            if (checkHandleGrab(handle0, cursor)) {
            } else if (checkHandleGrab(handle1, cursor)) {
            } else if (checkHandleGrab(handle2, cursor)) {
            } else if (checkHandleGrab(handle3, cursor)) {
            }
        } else if (!Input.mouseDown) {
            draggedHandle = null;
            dragOffset = Vector2f.zero();
        }

        if (Input.mouseDrags && draggedHandle != null) {
            draggedHandle.positionByCentre(Input.getCursorPosition().added(dragOffset));
            getPartnerHandleX(draggedHandle).setX(draggedHandle.getX());
            getPartnerHandleY(draggedHandle).setY(draggedHandle.getY());
        }

        final Vector2f position = handle0.getCentre();
        final Dimensions size = handle2.getCentre().subtracted(position).toDimensions();
        subject.setTransform(new Transform(position, size));
        if (subject instanceof GameObject) {
            ((GameObject) subject).setHitbox(new SimpleHitbox((GameObject) subject, subject.getWidth(), subject.getHeight(), 0, 0));
        }
    }

    private boolean checkHandleGrab(final Transform handle, final Transform cursor) {
        if (handle.contains(cursor)) {
            draggedHandle = handle;
            dragOffset = handle.getCentre().subtracted(cursor.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public void draw(SaltyGraphics g) {
        g.setColor(handleColor);
        g.drawRect(handle0);
        g.drawRect(handle1);
        g.drawRect(handle2);
        g.drawRect(handle3);
    }

    private Transform getPartnerHandleX(final Transform handle) {
        if (handle.equals(handle0)) {
            return handle1;
        } else if (handle.equals(handle1)) {
            return handle0;
        } else if (handle.equals(handle2)) {
            return handle3;
        } else if (handle.equals(handle3)) {
            return handle2;
        }
        return null;
    }

    private Transform getPartnerHandleY(final Transform handle) {
        if (handle.equals(handle0)) {
            return handle3;
        } else if (handle.equals(handle1)) {
            return handle2;
        } else if (handle.equals(handle2)) {
            return handle1;
        } else if (handle.equals(handle3)) {
            return handle0;
        }
        return null;
    }

    public boolean clicked(final Transform cursor) {
        return draggedHandle != null || handle0.contains(cursor) || handle1.contains(cursor) || handle2.contains(cursor) || handle3.contains(cursor);
    }

    public void update(final Transform transform) {
        draggedHandle = null;
        dragOffset = Vector2f.zero();
        final float subjectMaxX = transform.getMaxX();
        final float subjectMaxY = transform.getMaxY();

        handle0 = new Transform(transform.getX() - handleSizeDiv2, transform.getY() - handleSizeDiv2, handleSize, handleSize);
        handle1 = new Transform(subjectMaxX - handleSizeDiv2, transform.getY() - handleSizeDiv2, handleSize, handleSize);
        handle2 = new Transform(subjectMaxX - handleSizeDiv2, subjectMaxY - handleSizeDiv2, handleSize, handleSize);
        handle3 = new Transform(transform.getX() - handleSizeDiv2, subjectMaxY - handleSizeDiv2, handleSize, handleSize);
    }
}
