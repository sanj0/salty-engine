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

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAbove(final Coordinates other) {
        return this.getY() < other.getY();
    }

    public boolean isBelow(final Coordinates other) {
        return this.getY() > other.getY();
    }

    public boolean isLeft(final Coordinates other) {
        return this.getX() < other.getX();
    }

    public boolean isRight(final Coordinates other) {
        return this.getX() > other.getX();
    }

    public void parseCoordinates(final Vector2f vector2F) {

        this.setX((int) vector2F.getX());
        this.setY((int) vector2F.getY());
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void changeX(final int delta) {

        setX(getX() + delta);
    }

    public void changeY(final int delta) {

        setY(getY() + delta);
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setCoordinates(final Coordinates coordinates) {

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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        final Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }
}
