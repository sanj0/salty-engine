package de.me.edgelord.sjgl.utils;

import de.edgelord.stdf.writing.FileWriter;
import de.me.edgelord.sjgl.StaticVars.StaticSystem;
import de.me.edgelord.sjgl.StaticVars.Time;
import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FPSViewer extends GameObject {

    private float fps;
    private File storage;
    private FileWriter infoFileWriter;
    private String renderTimeInfo = "";
    private String recordsInfo = "";
    private float fpsPeak = 0f;
    private long deltaTimePeak = 0;
    private float lowestFPS = 0f;
    private long lowestDeltaTime = 0;
    private long currentFrame = 0;
    private long deltaTime = 0;


    public FPSViewer() throws IOException {
        super(new Coordinates(100, 100), 1, 1);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd|MM|yyyy_HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        File storage = StaticSystem.getOuterResource().getFile("info/renderTimeInfo_" + timeFormatter.format(currentDateTime) + ".txt");

        System.out.println("INFO: The path were the rendertime info will be stored to ist: " + storage.getAbsolutePath());

        storage.createNewFile();

        infoFileWriter = new FileWriter(storage);
    }

    private void updateRecordsInfo() {

        recordsInfo = "FPS peak " + fpsPeak + "\nDelta time peak " + deltaTimePeak + "\nLowest FPS " + lowestFPS + "\nLowest delta time " + lowestDeltaTime + "\n";
    }

    private void updateRenderTimeInfo() {

        renderTimeInfo += "Frame " + String.valueOf(currentFrame) + "\n    FPS: " + String.valueOf(fps) + "\n    Delta millis: " + String.valueOf(deltaTime) + "\n";
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(GameObject other) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {

        currentFrame++;
        deltaTime = Time.getDeltaMillis();

        if (deltaTime != 0) {

            fps = 1000 / deltaTime;
        } else {
            fps = 3.14f;
        }

        if (fps > fpsPeak) {
            fpsPeak = fps;
        }

        if (deltaTime > deltaTimePeak) {
            deltaTimePeak = deltaTime;
        }

        if (lowestFPS > fps) {
            lowestFPS = fps;
        }

        if (lowestDeltaTime > deltaTime) {
            lowestDeltaTime = deltaTime;
        }

        updateRecordsInfo();
        updateRenderTimeInfo();

        try {
            infoFileWriter.writeThrough(recordsInfo + renderTimeInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

        String fpsString = String.valueOf(fps);

        // System.out.println("FPS: " + fpsString);
        // System.out.println("Deltatime: " + Time.getDeltaMillis());

        graphics.drawString(fpsString, getCoordinates().getX(), getCoordinates().getY());
    }
}
