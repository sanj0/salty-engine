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

import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.ValueToDataConverter;

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
        // saved. You could say that the given Species is read-only.
        // ValueToDataConverter.convertToInteger converts the given value from the given species to an integer. There are more
        // types available.
        // ValueToListConverter converts a value to a list, see more in the class "AdvancedSaver"
        //
        // You can also read a String by simply calling species.getTagValue("highScore");
        highScore = ValueToDataConverter.convertToInteger(species, "highScore");
    }

    @Override
    public String getDataSetName() {
        return "saver";
    }
}
