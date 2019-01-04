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

package de.edgelord.saltyengine.transform;

public class Coordinates {

    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAbove(Coordinates other) {
        return this.getY() < other.getY();
    }

    public boolean isBelow(Coordinates other) {
        return this.getY() > other.getY();
    }

    public boolean isLeft(Coordinates other) {
        return this.getX() < other.getX();
    }

    public boolean isRight(Coordinates other) {
        return this.getX() > other.getX();
    }

    public void parseCoordinates(Coordinates2f coordinates2f) {

        this.setX((int) coordinates2f.getX());
        this.setY((int) coordinates2f.getY());
    }

    public int getX() {
        return x;
    }

    public void changeX(int delta) {

        setX(getX() + delta);
    }

    public void changeY(int delta) {

        setY(getY() + delta);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(Coordinates coordinates) {

        setX(coordinates.getX());
        setY(coordinates.getY());
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }
}
