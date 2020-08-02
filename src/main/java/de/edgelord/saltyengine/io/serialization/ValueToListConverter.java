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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ValueToListConverter {

    private ValueToListConverter() {
    }

    /**
     * Converts the value of the given tag in the given Species to a List of
     * Strings separated by the given String and returns it.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Strings.
     *
     * @return the List of Strings
     */
    public static List<String> convertToList(final Species species, final String tag, final String separator) {

        final String[] arrayToAdd = species.getTagValue(tag).split(separator);

        return new LinkedList<>(Arrays.asList(arrayToAdd));
    }

    /**
     * Returns the list from {@link #convertToList(Species, String, String)} as
     * an array.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Strings.
     *
     * @return the array of Strings
     */
    public static String[] convertToArray(final Species species, final String tag, final String separator) {
        return convertToList(species, tag, separator).toArray(new String[0]);
    }

    /**
     * Converts the value of the given tag in the given Species to a List of
     * Integers separated by the given String and returns it.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Integers.
     *
     * @return the List of ints
     */
    public static List<Integer> convertToIntegerList(final Species species, final String tag, final String separator) {

        final List<Integer> list = new LinkedList<>();

        for (final String string : convertToList(species, tag, separator)) {

            list.add(Integer.parseInt(string));
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToIntegerList(Species, String,
     * String)} as an array.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Strings.
     *
     * @return the array of ints
     */
    public static Integer[] convertToIntegerArray(final Species species, final String tag, final String separator) {
        return convertToIntegerList(species, tag, separator).toArray(new Integer[0]);
    }

    /**
     * Converts the value of the given tag in the given Species to a List of
     * doubles separated by the given String and returns it.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the doubles.
     *
     * @return the List of doubles
     */
    public static List<Double> convertToDoubleList(final Species species, final String tag, final String separator) {

        final List<Double> list = new LinkedList<>();

        for (final String string : convertToList(species, tag, separator)) {
            list.add(Double.parseDouble(string));
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToDoubleList(Species, String,
     * String)} as an array.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Strings.
     *
     * @return the array of doubles
     */
    public static Double[] convertToDoubleArray(final Species species, final String tag, final String separator) {
        return convertToDoubleList(species, tag, separator).toArray(new Double[0]);
    }

    /**
     * Converts the value of the given tag in the given Species to a List of
     * chars separated by the given String and returns it.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the chars.
     *
     * @return the List of chars
     */
    public static List<Character> convertToCharList(final Species species, final String tag, final String separator) {

        final List<Character> list = new LinkedList<>();

        for (final String string : convertToList(species, tag, separator)) {
            list.add(string.toCharArray()[0]);
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToCharList(Species, String, String)}
     * as an array.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Strings.
     *
     * @return the array of chars
     */
    public static Character[] convertToCharArray(final Species species, final String tag, final String separator) {
        return convertToCharList(species, tag, separator).toArray(new Character[0]);
    }

    /**
     * Converts the value of the given tag in the given Species to a List of
     * doubles separated by the given String and returns it.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the doubles.
     *
     * @return the List of doubles
     */
    public static List<Float> convertToFloatList(final Species species, final String tag, final String separator) {

        final List<Float> list = new LinkedList<>();

        for (final String string : convertToList(species, tag, separator)) {
            list.add(Float.valueOf(string));
        }

        return list;
    }

    /**
     * Returns the list from {@link #convertToFloatList(Species, String,
     * String)} as an array.
     *
     * @param species   the Species which contains the tag
     * @param tag       the tag which should be converted
     * @param separator the separator which divides the Strings.
     *
     * @return the array of floats
     */
    public static Float[] convertToFloatArray(final Species species, final String tag, final String separator) {
        return convertToFloatList(species, tag, separator).toArray(new Float[0]);
    }
}
