/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.example.serialization;

import de.edgelord.saltyengine.io.serialization.Serializer;

import java.io.IOException;

public class SerializationMain {

    public static void main(String[] args) {
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
        // In this example, as we are never telling the game how it is called, the name will be "salty-engine"
        // The method could throw an IOException, so we have to put it into a try/catch structure.
        try {
            Serializer.doDeserialization();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save the data to the file. That also happens automatically when you close the game-window.
        // You could also save to a custom file by using
        // Serializer.doSerialization("save1"); or
        // Serializer.doSerialization("unicorn");
        try {
            Serializer.doSerialization();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
