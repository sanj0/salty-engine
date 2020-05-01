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

package de.edgelord.saltyengine.example.serialization;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.io.serialization.Serializer;

import java.io.IOException;

public class SerializationMain {

    public static void main(final String[] args) {
        Game.init(GameConfig.config(0, 0, "Serialization Example", 5));
        // Add a new Saver to the list
        Serializer.add(new Saver());
        // Add a new AdvancedSaver to the list
        Serializer.add(new AdvancedSaver());

        // read the data from the default save-file, called "save0.sdb".
        // you could also call e.g. Serializer.doDeserialization("save1") or Serializer.doDeserialization("unicorn")
        // When the file does not exist, it's being created and nothing is being read.
        //
        // It should be
        // in [Your homefolder]/.[game name]
        // In this example, we set the game name to be "Serialization Example" in the Game#init call.
        // The method could throw an IOException, so we have to put it into a try/catch structure.
        try {
            Serializer.doDeserialization();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        // save the data to the file. That also happens automatically when you close the game-window.
        // You could also save to a custom file by using
        // Serializer.doSerialization("save1"); or
        // Serializer.doSerialization("unicorn");
        try {
            Serializer.doSerialization();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
