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
