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

public class ColorUtil {

    // Red Colors:
    public static final Color MAROON_RED_COLOR = new Color(128, 0, 0);
    public static final Color TOMATO_RED = new Color(255, 9, 71);
    public static final Color FIREBRICK_RED = new Color(178, 34, 34);
    public static final Color CRIMSON_RED = new Color(220, 20, 60);
    public static final Color CORAL_RED = new Color(255, 127, 80);
    public static final Color INDIAN_RED = new Color(205, 92, 92);
    public static final Color DARK_SALMON = new Color(233, 150, 122);
    public static final Color SALMON = new Color(250, 128, 114);

    // Orange Colors:
    public static final Color ORANGE_RED = new Color(255, 69, 0);
    public static final Color DARK_ORANGE = new Color(255, 140, 0);
    public static final Color ORANGE = new Color(255, 165, 0);

    // Green Colors:
    public static final Color OLIVE_COLOR = new Color(128, 128, 0);
    public static final Color DARK_GREEN_COLOR = new Color(0, 128, 0);
    public static final Color YELLOW_GREEN = new Color(154, 205, 50);
    public static final Color DARK_OLIVE_GREEN = new Color(154, 205, 50);
    public static final Color OLIVE_DRAB_GREEN = new Color(107, 142, 35);
    public static final Color FOREST_GREEN = new Color(34, 139, 34);
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
    public static final Color CADET_BLUE = new Color(95, 158, 160);
    public static final Color STEEL_BLUE = new Color(70, 130, 180);
    public static final Color CORN_FLOWER_BLUE = new Color(100, 149, 237);
    public static final Color DEEP_SKY_BLUE = new Color(0, 191, 255);
    public static final Color DODGER_BLUE = new Color(30, 144, 255);
    public static final Color MIDNIGHT_BLUE = new Color(25, 25, 112);
    public static final Color ROYAL_BLUE = new Color(65, 105, 255);

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
    public static final Color DARK_GRAY = new Color(50, 50, 50);
    public static final Color GRAY = new Color(105, 105, 105);
    public static final Color LIGHT_GRAY = new Color(150, 150, 150);
    public static final Color LIGHTER_GRAY = new Color(200, 200, 200);
    public static final Color GHOST_WHITE = new Color(248, 248, 255);
    public static final Color SNOW_WHITE = new Color(255, 250, 250);
    public static final Color WHITE_SMOKE = new Color(245, 245, 245);
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

    // Plain Colors:
    public static final Color PLAIN_RED = new Color(255, 0, 0);
    public static final Color RED = new Color(210, 50, 50);

    public static final Color PLAIN_GREEN = new Color(0, 255, 0);
    public static final Color GREEN = new Color(50, 210, 50);

    public static final Color PLAIN_BLUE = new Color(0, 0, 255);
    public static final Color BLUE = new Color(50, 50, 210);

    public static final Color PLAIN_YELLOW = new Color(255, 255, 0);
    public static final Color YELLOW = new Color(210, 210, 50);

    /**
     * Change the brightness of the given Color.
     * 1 is black, -1 the brightest Color without changing it's cosmetic
     *
     * @param color  Color to make darker.
     * @param amount Darkness fraction.
     * @return Darker color.
     */
    public static Color changeBrightness(Color color, float amount) {
        int red = (int) Math.round(color.getRed() * (1.0 - amount));
        int green = (int) Math.round(color.getGreen() * (1.0 - amount));
        int blue = (int) Math.round(color.getBlue() * (1.0 - amount));

        if (red < 0) red = 0;
        else if (red > 255) red = 255;
        if (green < 0) green = 0;
        else if (green > 255) green = 255;
        if (blue < 0) blue = 0;
        else if (blue > 255) blue = 255;

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);
    }

    /**
     * Creates a new {@link RadialGradientPaint} around the given {@link Transform} shifting from the given {@link Color}
     * to the same color but with the given alpha value.
     *
     * @param bounds      the bounds of the gradient
     * @param color       the color of the gradient
     * @param targetAlpha the alpha to shift the color to
     * @return a new {@link RadialGradientPaint} around the given {@link Transform} shifting from the given {@link Color}
     * to the same color but with the given alpha value.
     * @see RadialGradientPaint
     */
    public static RadialGradientPaint createRadialGradientPaint(Transform bounds, Color color, int targetAlpha) {

        float radius;

        if (bounds.getWidth() <= 0) {
            radius = 1;
        } else {
            radius = bounds.getWidth();
        }

        float[] fractions = {0f, 1f};
        Color endColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), targetAlpha);
        Color[] colors = {color, endColor};

        return new RadialGradientPaint(bounds.getCentre().getX(), bounds.getCentre().getY(), radius, fractions, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
    }

    /**
     * Calls {@link #createRadialGradientPaint(Transform, Color, int)} with the target alpha being 0.
     *
     * @param bounds the bounds of the gradient
     * @param color  the color of the gradient
     * @return a new {@link RadialGradientPaint} around the given {@link Transform} shifting from the given {@link Color}
     * to the same color but with an alpha of 0.
     */
    public static RadialGradientPaint createRadialGradientPaint(Transform bounds, Color color) {
        System.out.println(bounds);
        return createRadialGradientPaint(bounds, color, 0);
    }
}
