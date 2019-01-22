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

package de.edgelord.saltyengine.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation that stores information about the component, used for a planned gui.
 */
@Retention(value= RetentionPolicy.RUNTIME)
public @interface ComponentInfo {

    /**
     * The name of the component
     * @return the name of the component
     */
    String name();

    /**
     * The description of the component.
     * @return the description of the component.
     */
    String description();

    /**
     * The fully classified name of the component
     * @return the fully classified name of the component
     */
    String fullyClassifiedName();

    /**
     * The writable values of the component. Stored like this:
     * <code>"[valueName]:setValue"</code>
     * Example:
     * <code>""</code>
     *
     * @return the writable values of the component.
     */
    String[] values();
}
