/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.example.net;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.net.MPEntity;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.net.InetAddress;

public class Player extends MPEntity {
    public Player(float xPos, float yPos, float width, float height, String username, InetAddress ipAddress, int port) {
        super(xPos, yPos, width, height, username, ipAddress, port);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(ColorUtil.GOLD);

        saltyGraphics.drawText(getUsername(), getX(), getY() - 25);
        saltyGraphics.drawRect(this);
    }
}
