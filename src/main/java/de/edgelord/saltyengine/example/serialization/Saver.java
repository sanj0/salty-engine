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

import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.io.serialization.Species;

public class Saver implements Serializable {

    private int highScore = 99;

    @Override
    public void serialize(Species species) {
        // This method saves the data. With the given Species, you can add tags and values to the file
        // for example the Integer highScore, saved with the tag-name "highScore". You could use any other name there
        species.addTag("highScore", highScore);

        // And that's it. The data is being saved into a file!
    }

    @Override
    public void deserialize(Species species) {
        // This method reads the data. With the given Species, you can read tag values. All tags you add to the species won't be
        // saved.
        highScore = Integer.parseInt(species.getTagValue("highScore"));
    }

    @Override
    public String getDataSetName() {
        return "saver";
    }
}
