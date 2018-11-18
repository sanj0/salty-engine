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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.ValueToDataConverter;
import de.edgelord.stdf.reading.ValueToListConverter;

import java.util.List;

public class AdvancedSaver implements Serializable {

    private int counter = 0;

    private static final String CAMERA_POSITION_TAG = "camPos";
    private static final String COUNTER_TAG = "counter";
    private static final String REDUNDANT_MESSAGE_TAG = "message";

    @Override
    public void serialize(Species species) {
        species.addTag(COUNTER_TAG, ++counter);
        species.addTag(REDUNDANT_MESSAGE_TAG, "This*_*is*_*a*_*redundant*_*message*_*to*_*show*_*that*_*you*_*have*_*to*_*encode*_*spaces*_*with*_*these*_*weird*_*combinations*_*of*_*stars*_*and*_*underscores!");
        species.addTag(CAMERA_POSITION_TAG, Game.getCamera().getX() + "," + Game.getCamera().getY());
    }

    @Override
    public void deserialize(Species species) {
        counter = ValueToDataConverter.convertToInteger(species, COUNTER_TAG);

        List<String> cameraPosition = ValueToListConverter.convertToList(species, CAMERA_POSITION_TAG, ",");
        Game.getCamera().setPosition(new Vector2f(Float.valueOf(cameraPosition.get(0)), Float.valueOf(cameraPosition.get(1))));

        System.out.println("This example started " + counter + " times before on this computer!");
        System.out.println(species.getTagValue(REDUNDANT_MESSAGE_TAG));
    }

    @Override
    public String getDataSetName() {
        return "advancedSaver";
    }
}
