/*
 * Copyright 2020 Malte Dostal
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

import java.util.LinkedList;
import java.util.List;

@Deprecated
public class Species {

    private final String name;
    private final List<Species> subSpecies = new LinkedList<>();
    private String content;

    public Species(final String name, final String content) {
        this.content = content;
        this.name = name;
    }

    public Species(final String name) {
        this.content = "";
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    /**
     * Returns the value of the given tag within the Species and all its
     * subspecies
     *
     * @param tag the name of the tag whose value should be returned
     *
     * @return the value of the given tag
     */
    public String getTagValue(final String tag) {
        return splitString(content, "(" + tag + ")", "(*" + tag + ")");
    }

    /**
     * Returns the Species with the given name within the Species
     *
     * @param species name of the Species wich should be returned and added to
     *                the list
     *
     * @return the Species with the given name
     */
    public Species getSubSpecies(final String species) {
        return new Species(species, splitString(content, "{" + species + "}", "{*" + species + "}"));
    }

    private String splitString(final String base, final String start, final String end) {
        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }

    /**
     * Return the content of the Species and all its subspecies plus their head
     * {name} content {*name}
     *
     * @return the complete syntax of the Species plus all its subspecies
     */
    public String getSyntax() {
        final StringBuilder syntax = new StringBuilder();

        syntax.append("{").append(name).append("}").append(content);

        for (final Species species : subSpecies) {
            syntax.append(species.getSyntax());
        }
        syntax.append("{*").append(name).append("}");

        return syntax.toString();
    }

    /**
     * Adds a tag with the given name and value to the Species (name) value
     * (*name)
     *
     * @param tag   the name of the tag which should be added
     * @param value the value of the tag which should be added
     */
    public void addTag(final String tag, final Object value) {
        content = "(" + tag + ")" + value.toString() + "(*" + tag + ")" + content;
    }

    /**
     * Creates the Species with the given name within the Species and adds it to
     * the list, which will be written to the file if requested and returns it.
     *
     * @param name name of the Species which should be returned and added to the
     *             list
     *
     * @return the created Species with the given name
     */
    public Species addSubSpecies(final String name) {
        final Species speciesToReturn = new Species(name, "");

        subSpecies.add(speciesToReturn);
        return speciesToReturn;
    }
}
