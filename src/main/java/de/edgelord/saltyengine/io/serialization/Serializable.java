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

package de.edgelord.saltyengine.io.serialization;

import de.edgelord.sanjo.SJClass;

/**
 * Provides functionality to make a class serializable using sanjo. Refer to
 * {@link Serializer} for additional information.
 */
public interface Serializable {

    /**
     * Serializes the data of this object to the given {@link SJClass sanjo
     * class}.
     *
     * @param data the {@link SJClass sanjo class} to write to
     */
    void serialize(final SJClass data);

    /**
     * Deserializes this object's data from the given {@link SJClass sanjo
     * class}.
     *
     * @param data the {@link SJClass sanjo class} to read the data from
     */
    void deserialize(final SJClass data);

    /**
     * Returns the name of the data set that is so be used for serializing and
     * deserializing this object's data. <br>This will be the {@link
     * SJClass#getName() name} of the {@link SJClass sanjo classes} passed to
     * {@link #serialize(SJClass)} and {@link #deserialize(SJClass)}
     *
     * @return the name of the data set that is to used for the serialization
     * and deserialization of this object
     */
    String getDataSetName();
}
