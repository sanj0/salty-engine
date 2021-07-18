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

/*
 * The following Code is partly from https://geosoft.no/software/colorutil/ColorUtil.java.html it is used under the following license:
 *
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA  02111-1307, USA.
 */

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.transform.Transform;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.List;
import java.util.*;

public class ColorUtil {

    // Red Colors:
    public static final Color MAROON_RED_COLOR = new Color(128, 0, 0);
    public static final Color TOMATO_RED = new Color(255, 9, 71);
    public static final Color FIREBRICK_RED = new Color(178, 34, 34);
    public static final Color CRIMSON_RED = new Color(220, 20, 60);
    public static final Color HEART_RED = new Color(229, 55, 84);
    public static final Color MODERN_RED = new Color(234, 104, 113);
    public static final Color CORAL_RED = new Color(255, 127, 80);
    public static final Color INDIAN_RED = new Color(205, 92, 92);
    public static final Color DARK_SALMON = new Color(233, 150, 122);
    public static final Color SALMON = new Color(250, 128, 114);

    // Orange Colors:
    public static final Color ORANGE_RED = new Color(255, 69, 0);
    public static final Color DARK_ORANGE = new Color(255, 140, 0);
    public static final Color NEUTRAL_ORANGE = new Color(229, 136, 71);
    public static final Color ORANGE = new Color(255, 165, 0);

    // Green Colors:
    public static final Color GRASS_GREEN = new Color(62, 136, 73);
    public static final Color TREE_GREEN = new Color(12, 174, 91);
    public static final Color OLIVE_COLOR = new Color(128, 128, 0);
    public static final Color DARK_GREEN_COLOR = new Color(0, 128, 0);
    public static final Color YELLOW_GREEN = new Color(154, 205, 50);
    public static final Color DARK_OLIVE_GREEN = new Color(154, 205, 50);
    public static final Color OLIVE_DRAB_GREEN = new Color(107, 142, 35);
    public static final Color FOREST_GREEN = new Color(34, 139, 34);
    public static final Color ACTIVE_GREEN = new Color(104, 204, 87);
    public static final Color LIGHT_GREEN = new Color(144, 238, 144);
    public static final Color PALE_GREEN = new Color(152, 251, 152);
    public static final Color MEDIUM_SPRING_GREEN = new Color(0, 250, 154);
    public static final Color SPRING_GREEN = new Color(0, 250, 127);
    public static final Color SEA_GREEN = new Color(46, 139, 87);
    public static final Color MEDIUM_SEA_GREEN = new Color(60, 179, 113);
    public static final Color HONEY_DEW_GREEN = new Color(240, 255, 240);
    public static final Color MINT_CREAM = new Color(245, 255, 250);

    // Blue Colors:
    public static final Color TEAL_BLUE_COLOR = new Color(0, 128, 128);
    public static final Color NAVY_BLUE_COLOR = new Color(0, 0, 128);
    public static final Color MEDIUM_MARINE_BLUE = new Color(102, 205, 170);
    public static final Color LIGHT_SEA_BLUE = new Color(32, 178, 170);
    public static final Color DARK_TURQUOISE = new Color(0, 206, 209);
    public static final Color TURQUOISE = new Color(64, 224, 208);
    public static final Color AQUA_MARINE_BLUE = new Color(127, 255, 212);
    public static final Color NEUTRAL_BLUE = new Color(62, 134, 160);
    public static final Color CADET_BLUE = new Color(95, 158, 160);
    public static final Color STEEL_BLUE = new Color(70, 130, 180);
    public static final Color CORN_FLOWER_BLUE = new Color(100, 149, 237);
    public static final Color DEEP_SKY_BLUE = new Color(0, 191, 255);
    public static final Color DODGER_BLUE = new Color(30, 144, 255);
    public static final Color MIDNIGHT_BLUE = new Color(25, 25, 112);
    public static final Color ROYAL_BLUE = new Color(65, 105, 255);
    public static final Color OCEAN_BLUE = new Color(0, 119, 190);

    // Yellow and Brown Colors:
    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color DARK_GOLD_ROD = new Color(184, 134, 11);
    public static final Color GOLD_ROD = new Color(218, 165, 32);
    public static final Color KHAKI = new Color(240, 230, 140);
    public static final Color DARK_KHAKI = new Color(240, 230, 140);
    public static final Color SADDLE_BROWN = new Color(139, 69, 19);
    public static final Color SIENNA_BROWN = new Color(160, 82, 45);
    public static final Color CHOCOLATE_BROWN = new Color(210, 105, 30);
    public static final Color PERU_BROWN = new Color(205, 133, 63);
    public static final Color SAND_BROWN = new Color(244, 164, 96);
    public static final Color MOCCASIN = new Color(255, 228, 181);
    public static final Color ROSY_BRON = new Color(188, 143, 143);
    public static final Color TREE_BROWN = new Color(97, 59, 22);

    // Purple Colors:
    public static final Color PURPLE_COLOR = new Color(128, 0, 128);
    public static final Color BLUE_VIOLET = new Color(138, 43, 226);
    public static final Color INDIGO = new Color(75, 0, 130);
    public static final Color DARK_MAGENTA = new Color(139, 0, 139);
    public static final Color ORCHID_PURPLE = new Color(153, 50, 204);
    public static final Color DEEP_PINK = new Color(255, 20, 147);

    // Black to White Colors:
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color SLATE_GRAY = new Color(112, 128, 144);
    public static final Color DARKER_GRAY = new Color(25, 25, 25);
    public static final Color SAD_GRAY = new Color(56, 57, 52);
    public static final Color DARK_GRAY = new Color(50, 50, 50);
    public static final Color GRAY = new Color(105, 105, 105);
    public static final Color LIGHT_GRAY = new Color(150, 150, 150);
    public static final Color LIGHTER_GRAY = new Color(200, 200, 200);
    public static final Color VINTAGE_GRAY_1 = new Color(129, 127, 113);
    public static final Color VINTAGE_GRAY_2 = new Color(75, 74, 58);
    public static final Color VINTAGE_GRAY_3 = new Color(47, 45, 26);
    public static final Color GHOST_WHITE = new Color(248, 248, 255);
    public static final Color SNOW_WHITE = new Color(255, 250, 250);
    public static final Color WHITE_SMOKE = new Color(245, 245, 245);
    public static final Color VINTAGE_WHITE = new Color(217, 215, 200);
    public static final Color WHITE = new Color(255, 255, 255);

    // Other Colors:
    public static final Color SILVER_COLOR = new Color(192, 192, 192);
    public static final Color IVORY = new Color(255, 255, 240);
    public static final Color NAVAJO_WHITE = new Color(255, 222, 173);
    public static final Color PEACH_PUFF = new Color(255, 218, 185);
    public static final Color LAVENDER_BUSH = new Color(255, 228, 225);
    public static final Color PAPAYA_WHIP = new Color(255, 239, 213);
    public static final Color SEA_SHELL = new Color(255, 245, 238);
    public static final Color OLD_LACE = new Color(253, 245, 230);
    public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    // Plain Colors:
    public static final Color MAX_RED = new Color(255, 0, 0);
    public static final Color RED = new Color(210, 50, 50);

    public static final Color MAX_GREEN = new Color(0, 255, 0);
    public static final Color GREEN = new Color(50, 210, 50);

    public static final Color MAX_BLUE = new Color(0, 0, 255);
    public static final Color BLUE = new Color(50, 50, 210);

    public static final Color MAX_YELLOW = new Color(255, 255, 0);
    public static final Color YELLOW = new Color(210, 210, 50);

    public static final Color MAX_CYAN = new Color(0, 255, 255);

    public static final Color MAX_MAGENTA = new Color(255, 0, 255);

    public static final Color PLAIN_BLACK = new Color(0, 0, 0);

    public static final Color PLAIN_WHITE = new Color(255, 255, 255);

    public static final List<Color> allColors = new LinkedList<>();
    public static final Map<String, Color> colors = new HashMap<>();

    /**
     * The list of colors defined in this class that start with {@code MAX_} and
     * therefore the colors considered the maximum value in a certain
     * direction.
     */
    public static final List<Color> maxColors = new LinkedList<>();

    static {
        final Field[] fields = ColorUtil.class.getFields();

        for (final Field field : fields) {
            if (field.getType() == Color.class) {
                try {
                    final Color c = (Color) field.get(null);
                    allColors.add(c);
                    colors.put(field.getName(), c);
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        maxColors.add(MAX_RED);
        maxColors.add(MAX_GREEN);
        maxColors.add(MAX_BLUE);
        maxColors.add(MAX_YELLOW);
        maxColors.add(MAX_CYAN);
        maxColors.add(MAX_MAGENTA);
    }

    private ColorUtil() {
    }

    /**
     * Parses the given string to a color.
     * <p>supported formats:
     * <br>"CONSTANT_NAME"
     * <br>#html
     * <br>r, g, b, (a)
     * <p>returns null if s is not in the correct format
     *
     * @param s a string that represents a color
     *
     * @return the Color represented by the given string or null
     */
    public static Color parseColor(final String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return colors.get(s.substring(1, s.length() - 1));
        } else if (s.startsWith("#")) {
            return Color.decode(s);
        } else {
            final String[] components = s.split(",");
            final int alpha = components.length == 4 ? Integer.parseInt(components[3].trim()) : 255;
            if (components.length > 4 || components.length < 3) {
                return null;
            }
            return new Color(Integer.parseInt(components[0].trim()),
                    Integer.parseInt(components[1].trim()), Integer.parseInt(components[2].trim()), alpha);
        }
    }

    /**
     * Returns the {@link #maxColors max color} closest to the given color,
     * using {@link #getClosestColor(Color, List)}
     *
     * @param color a color
     *
     * @return the {@link #maxColors max color} closest to the given color
     */
    public static Color getClosestMaxColor(final Color color) {
        return getClosestColor(color, maxColors);
    }

    /**
     * Returns the list of colors defined in this class that are closer to the
     * given {@link #maxColors max color} than to any other {@link #maxColors
     * max color}.
     *
     * @param maxColor a {@link #maxColors max color}
     *
     * @return the list of colors defined in this class that are closer to the
     * given {@link #maxColors max color} than to any other {@link #maxColors
     * max color}
     */
    public static List<Color> getColorsOfMaxColor(final Color maxColor) {
        if (!maxColors.contains(maxColor)) {
            throw new IllegalArgumentException("only max colors allowed");
        }

        final List<Color> colors = new ArrayList<>();

        for (final Color c : allColors) {
            if (getClosestColor(c, maxColors).equals(maxColor)) {
                colors.add(c);
            }
        }

        return colors;
    }

    /**
     * Returns the one color from the given list, that is closest to the given
     * color or {@code null} if the given list is empty. The distance between
     * two colors is calculated using {@link #euclideanDistanceSquared(Color,
     * Color) their eucledean distance}. If two or more colors from the list
     * share the closest distance, the last of them is returned.
     *
     * @param color   a color
     * @param choices a list of colors to choose the closest to the given from
     *
     * @return the one color from the list that is closest to the given, or
     * {@code null} if the given list is empty
     */
    public static Color getClosestColor(final Color color, final List<Color> choices) {
        // initialize the closest distance with the maximum
        // distance plus one, meaning
        // 256^2 * 3 + 1
        double closestDistance = 196_609;
        Color closestColor = null;

        for (final Color c : choices) {
            final double distance = euclideanDistanceSquared(color, c);
            if (distance < closestDistance) {
                closestColor = c;
                closestDistance = distance;
            }
        }

        return closestColor;
    }

    /**
     * Returns the euclidean distance between the two given colors, without
     * taking the square root to save performance. If the result of this method
     * is to be used for anything other than comparing it to other result of
     * this method, one would likely want to take the square root of the result,
     * using {@link Math#sqrt(double)}.
     *
     * @param a a color
     * @param b another color
     *
     * @return the square of the euclidean distance between the two colors
     */
    public static double euclideanDistanceSquared(final Color a, final Color b) {
        return GeneralUtil.square(b.getRed() - a.getRed()) +
                GeneralUtil.square(b.getGreen() - a.getGreen()) +
                GeneralUtil.square(b.getBlue() - a.getBlue());
    }

    /**
     * Returns a random entry from {@link #allColors} using {@link
     * GeneralUtil#randomObjectFromList(List)}.
     *
     * @param exclude the colors to exclude from the random selection
     *
     * @return a random color
     */
    public static Color randomColor(Color... exclude) {
        Color color = null;
        final List<Color> excludeList = Arrays.asList(exclude);

        while (color == null || excludeList.contains(color)) {
            color = GeneralUtil.randomObjectFromList(allColors);
        }
        return color;
    }

    /**
     * Blends the two given colors by the given ratio.
     *
     * @param color1 the first color
     * @param color2 the second color
     * @param ratio  the blend ratio
     *
     * @return a blend between the two colors by the given ratio
     */
    public static Color blend(final Color color1, final Color color2, float ratio) {
        if (ratio > 1f) {
            ratio = 1f;
        } else if (ratio < 0f) {
            ratio = 0f;
        }

        final int argb1 = color1.getRGB();
        final int argb2 = color2.getRGB();

        final int alpha1 = getAlpha(argb1);
        final int red1 = getRed(argb1);
        final int green1 = getGreen(argb1);
        final int blue1 = getBlue(argb1);

        final int alpha2 = getAlpha(argb2);
        final int red2 = getRed(argb2);
        final int green2 = getGreen(argb2);
        final int blue2 = getBlue(argb2);

        final int alpha = (int) (alpha1 + (alpha2 - alpha1) * ratio);
        final int red = (int) (red1 + (red2 - red1) * ratio);
        final int green = (int) (green1 + (green2 - green1) * ratio);
        final int blue = (int) (blue1 + (blue2 - blue1) * ratio);

        return new Color(alpha << 24 | red << 16 | green << 8 | blue);
    }

    /**
     * Returns the alpha value from the given argb value.
     *
     * @param argb the argb value of the color
     *
     * @return the alpha value from the given argb color
     */
    public static int getAlpha(final int argb) {
        return (argb >> 24);
    }

    /**
     * Returns the red value from the given argb value.
     *
     * @param argb the argb value of the color
     *
     * @return the red value from the given argb color
     */
    public static int getRed(final int argb) {
        return ((argb & 0xff0000) >> 16);
    }

    /**
     * Returns the green value from the given argb value.
     *
     * @param argb the argb value of the color
     *
     * @return the green value from the given argb color
     */
    public static int getGreen(final int argb) {
        return ((argb & 0xff00) >> 8);
    }

    /**
     * Returns the blue value from the given argb value.
     *
     * @param argb the argb value of the color
     *
     * @return the blue value from the given argb color
     */
    public static int getBlue(final int argb) {
        return (argb & 0xff);
    }

    /**
     * Returns the given color with the given alpha value. The value goes from 0
     * to 255, 0 meaning complete transparency, 255 meaning full visibility.
     *
     * @param color the {@link Color} to return
     * @param alpha the alpha value of the color
     *
     * @return the given color with the given alpha value
     */
    public static Color withAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    /**
     * Returns the given color with the given alpha value. The value goes from
     * 0f to 1f, 0f meaning complete transparency, 1f meaning full visibility.
     *
     * @param color the {@link Color} to return
     * @param alpha the alpha value of the color
     *
     * @return the given color with the given alpha value
     */
    public static Color withAlpha(final Color color, final float alpha) {
        return withAlpha(color, (int) (alpha * 255f));
    }

    /**
     * Change the brightness of the given Color. 1 is black, -1 the brightest
     * Color without changing it's effect
     *
     * @param color  Color to make darker.
     * @param amount Darkness fraction.
     *
     * @return Darker color.
     * @deprecated
     */
    @Deprecated
    public static Color changeBrightness(final Color color, final float amount) {
        int red = (int) Math.round(color.getRed() * (1.0 - amount));
        int green = (int) Math.round(color.getGreen() * (1.0 - amount));
        int blue = (int) Math.round(color.getBlue() * (1.0 - amount));

        red = Math.round(GeneralUtil.clamp(red, 0, 255));
        green = Math.round(GeneralUtil.clamp(green, 0, 255));
        blue = Math.round(GeneralUtil.clamp(blue, 0, 255));

        return new Color(red, green, blue, color.getAlpha());
    }

    /**
     * Creates and returns a new {@link TexturePaint}. The given {@link
     * BufferedImage} is the texture and should be small, the given {@link
     * Transform} is the texture anchor. For a detailed description, please read
     * the documentation of the {@link TexturePaint class}.
     *
     * @param texture the texture to use. Should be small
     * @param anchor  the anchor of the texture
     *
     * @return a new {@link TexturePaint} using the given {@link BufferedImage}
     * as a texture and the given {@link Transform} as an anchor.
     */
    public static TexturePaint createTexturePaint(final BufferedImage texture, final Transform anchor) {
        return new TexturePaint(texture, anchor.getRect());
    }

    /**
     * Creates a new {@link RadialGradientPaint} around the given {@link
     * Transform} shifting from the given {@link Color} to the same color but
     * with the given alpha value.
     *
     * @param bounds      the bounds of the gradient
     * @param color       the color of the gradient
     * @param targetAlpha the alpha to shift the color to
     *
     * @return a new {@link RadialGradientPaint} around the given {@link
     * Transform} shifting from the given {@link Color} to the same color but
     * with the given alpha value.
     * @see RadialGradientPaint
     */
    public static RadialGradientPaint createRadialGradientPaint(final Transform bounds, final Color color, final int targetAlpha) {

        final float radius;

        if (bounds.getWidth() <= 0) {
            radius = 1;
        } else {
            radius = bounds.getWidth();
        }

        final float[] fractions = {0f, 1f};
        final Color endColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), targetAlpha);
        final Color[] colors = {color, endColor};

        return new RadialGradientPaint(bounds.getCentre().getX(), bounds.getCentre().getY(), radius, fractions, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
    }

    /**
     * Calls {@link #createRadialGradientPaint(Transform, Color, int)} with the
     * target alpha being 0.
     *
     * @param bounds the bounds of the gradient
     * @param color  the color of the gradient
     *
     * @return a new {@link RadialGradientPaint} around the given {@link
     * Transform} shifting from the given {@link Color} to the same color but
     * with an alpha of 0.
     */
    public static RadialGradientPaint createRadialGradientPaint(final Transform bounds, final Color color) {
        return createRadialGradientPaint(bounds, color, 0);
    }

    /**
     * Returns the given alpha, red, green or blue value ranging from 0 to 255
     * as a float value ranging from 0f to 1f.
     *
     * @param argb a single argb component ranging from 0 to 255
     *
     * @return the given integer in a range from 0 to 255 converted to a float
     * ranging from 0f to 1f
     */
    public static float intARGBToFloat(final int argb) {
        return (float) argb / 255;
    }
}
