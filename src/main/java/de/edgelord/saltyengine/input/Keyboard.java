/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.input;

import java.awt.event.KeyEvent;

public class Keyboard {

    /*
    Special keys
     */
    private boolean enter = false;
    private boolean backspace = false;
    private boolean delete = false;
    private boolean tab = false;
    private boolean shift = false;
    private boolean control = false;
    private boolean alt = false;
    private boolean caps_lock = false;
    private boolean escape = false;
    private boolean page_up = false;
    private boolean page_down = false;
    private boolean space = false;

    /*
    Arrow Keys
     */
    private boolean arrow_right = false;
    private boolean arrow_left = false;
    private boolean arrow_up = false;
    private boolean arrow_down = false;

    /*
    Math keys
     */
    private boolean key_comma = false;
    private boolean key_period = false; /* = "."*/
    private boolean key_minus = false;
    private boolean key_plus = false;
    private boolean key_less = false;
    private boolean key_greater = false;

    /*
    Numbers from 0 to 9
     */
    private boolean number_0 = false;
    private boolean number_1 = false;
    private boolean number_2 = false;
    private boolean number_3 = false;
    private boolean number_4 = false;
    private boolean number_5 = false;
    private boolean number_6 = false;
    private boolean number_7 = false;
    private boolean number_8 = false;
    private boolean number_9 = false;

    /*
    Letters from A to Z
     */
    private boolean key_a = false;
    private boolean key_b = false;
    private boolean key_c = false;
    private boolean key_d = false;
    private boolean key_e = false;
    private boolean key_f = false;
    private boolean key_g = false;
    private boolean key_h = false;
    private boolean key_i = false;
    private boolean key_j = false;
    private boolean key_k = false;
    private boolean key_l = false;
    private boolean key_m = false;
    private boolean key_n = false;
    private boolean key_o = false;
    private boolean key_p = false;
    private boolean key_q = false;
    private boolean key_r = false;
    private boolean key_s = false;
    private boolean key_t = false;
    private boolean key_u = false;
    private boolean key_v = false;
    private boolean key_w = false;
    private boolean key_x = false;
    private boolean key_y = false;
    private boolean key_z = false;

    /*
    Numpad-numbers from 0 to 9
     */
    private boolean numpad_0 = false;
    private boolean numpad_1 = false;
    private boolean numpad_2 = false;
    private boolean numpad_3 = false;
    private boolean numpad_4 = false;
    private boolean numpad_5 = false;
    private boolean numpad_6 = false;
    private boolean numpad_7 = false;
    private boolean numpad_8 = false;
    private boolean numpad_9 = false;

    /*
    Function keys from 1 to 12
     */
    private boolean function_1 = false;
    private boolean function_2 = false;
    private boolean function_3 = false;
    private boolean function_4 = false;
    private boolean function_5 = false;
    private boolean function_6 = false;
    private boolean function_7 = false;
    private boolean function_8 = false;
    private boolean function_9 = false;
    private boolean function_10 = false;
    private boolean function_11 = false;
    private boolean function_12 = false;

    /*
    Currency signs
     */
    private boolean currency_euro = false;
    private boolean currency_dollar = false;

    /*
    Other keys that can be accessed without using e.g. shift and some brackets
     */
    private boolean key_hash = false;
    private boolean parenthesis_open = false;
    private boolean parenthesis_close = false;
    private boolean brace_open = false;
    private boolean brace_close = false;
    private boolean squareBracket_open = false;
    private boolean squareBracket_close = false;

    public void handleKeyPressed(KeyEvent keyPressedEvent) {

        flagMatchingKeyAs(true, keyPressedEvent);
    }

    public void handleKeyReleased(KeyEvent keyReleasedEvent) {

        flagMatchingKeyAs(false, keyReleasedEvent);
    }

    private void flagMatchingKeyAs(boolean targetFlag, KeyEvent event) {

        switch (event.getKeyCode()) {

            /*
            Special keys
             */
            case KeyEvent.VK_ENTER:
                enter = targetFlag;
                break;
            case KeyEvent.VK_BACK_SPACE:
                backspace = targetFlag;
                break;
            case KeyEvent.VK_DELETE:
                delete = targetFlag;
                break;
            case KeyEvent.VK_TAB:
                tab = targetFlag;
                break;
            case KeyEvent.VK_SHIFT:
                shift = targetFlag;
                break;
            case KeyEvent.VK_CONTROL:
                control = targetFlag;
                break;
            case KeyEvent.VK_ALT:
                alt = targetFlag;
                break;
            case KeyEvent.VK_CAPS_LOCK:
                caps_lock = targetFlag;
                break;
            case KeyEvent.VK_ESCAPE:
                escape = targetFlag;
                break;
            case KeyEvent.VK_PAGE_UP:
                page_up = targetFlag;
                break;
            case KeyEvent.VK_PAGE_DOWN:
                page_down = targetFlag;
                break;
            case KeyEvent.VK_SPACE:
                space = targetFlag;

            /*
            Arrow keys
             */
            case KeyEvent.VK_RIGHT:
                arrow_right = targetFlag;
                break;
            case KeyEvent.VK_LEFT:
                arrow_left = targetFlag;
                break;
            case KeyEvent.VK_UP:
                arrow_up = targetFlag;
                break;
            case KeyEvent.VK_DOWN:
                arrow_down = targetFlag;
                break;

            /*
            Math keys
             */
            case KeyEvent.VK_COMMA:
                key_comma = targetFlag;
                break;
            case KeyEvent.VK_PERIOD:
                key_period = targetFlag;
                break;
            case KeyEvent.VK_MINUS:
                key_minus = targetFlag;
                break;
            case KeyEvent.VK_PLUS:
                key_plus = targetFlag;
                break;

            /*
            Numbers from 0 to 9
             */
            case KeyEvent.VK_0:
                number_0 = targetFlag;
                break;
            case KeyEvent.VK_1:
                number_1 = targetFlag;
                break;
            case KeyEvent.VK_2:
                number_2 = targetFlag;
                break;
            case KeyEvent.VK_3:
                number_3 = targetFlag;
                break;
            case KeyEvent.VK_4:
                number_4 = targetFlag;
                break;
            case KeyEvent.VK_5:
                number_5 = targetFlag;
                break;
            case KeyEvent.VK_6:
                number_6 = targetFlag;
                break;
            case KeyEvent.VK_7:
                number_7 = targetFlag;
                break;
            case KeyEvent.VK_8:
                number_8 = targetFlag;
                break;
            case KeyEvent.VK_9:
                number_9 = targetFlag;
                break;

            /*
            Letters from A to Z
             */
            case KeyEvent.VK_A:
                key_a = targetFlag;
                break;
            case KeyEvent.VK_B:
                key_b = targetFlag;
                break;
            case KeyEvent.VK_C:
                key_c = targetFlag;
                break;
            case KeyEvent.VK_D:
                key_d = targetFlag;
                break;
            case KeyEvent.VK_E:
                key_e = targetFlag;
                break;
            case KeyEvent.VK_F:
                key_f = targetFlag;
                break;
            case KeyEvent.VK_G:
                key_g = targetFlag;
                break;
            case KeyEvent.VK_H:
                key_h = targetFlag;
                break;
            case KeyEvent.VK_I:
                key_i = targetFlag;
                break;
            case KeyEvent.VK_J:
                key_j = targetFlag;
                break;
            case KeyEvent.VK_K:
                key_k = targetFlag;
                break;
            case KeyEvent.VK_L:
                key_l = targetFlag;
                break;
            case KeyEvent.VK_M:
                key_m = targetFlag;
                break;
            case KeyEvent.VK_N:
                key_n = targetFlag;
                break;
            case KeyEvent.VK_O:
                key_o = targetFlag;
                break;
            case KeyEvent.VK_P:
                key_p = targetFlag;
                break;
            case KeyEvent.VK_Q:
                key_q = targetFlag;
                break;
            case KeyEvent.VK_R:
                key_r = targetFlag;
                break;
            case KeyEvent.VK_S:
                key_s = targetFlag;
                break;
            case KeyEvent.VK_T:
                key_t = targetFlag;
                break;
            case KeyEvent.VK_U:
                key_u = targetFlag;
                break;
            case KeyEvent.VK_V:
                key_v = targetFlag;
                break;
            case KeyEvent.VK_W:
                key_w = targetFlag;
                break;
            case KeyEvent.VK_X:
                key_x = targetFlag;
                break;
            case KeyEvent.VK_Y:
                key_y = targetFlag;
                break;
            case KeyEvent.VK_Z:
                key_z = targetFlag;
                break;

            /*
            Numpad-numbers from 0 to 9
             */
            case KeyEvent.VK_NUMPAD0:
                numpad_0 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD1:
                numpad_1 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD2:
                numpad_2 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD3:
                numpad_3 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD4:
                numpad_4 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD5:
                numpad_5 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD6:
                numpad_6 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD7:
                numpad_7 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD8:
                numpad_8 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD9:
                numpad_9 = targetFlag;
                break;

            /*
            Function keys from 1 to 12
             */
            case KeyEvent.VK_F1:
                function_1 = targetFlag;
                break;
            case KeyEvent.VK_F2:
                function_2 = targetFlag;
                break;
            case KeyEvent.VK_F3:
                function_3 = targetFlag;
                break;
            case KeyEvent.VK_F4:
                function_4 = targetFlag;
                break;
            case KeyEvent.VK_F5:
                function_5 = targetFlag;
                break;
            case KeyEvent.VK_F6:
                function_6 = targetFlag;
                break;
            case KeyEvent.VK_F7:
                function_7 = targetFlag;
                break;
            case KeyEvent.VK_F8:
                function_8 = targetFlag;
                break;
            case KeyEvent.VK_F9:
                function_9 = targetFlag;
                break;
            case KeyEvent.VK_F10:
                function_10 = targetFlag;
                break;
            case KeyEvent.VK_F11:
                function_11 = targetFlag;
                break;
            case KeyEvent.VK_F12:
                function_12 = targetFlag;
                break;

            /*
            Currency signs
             */
            case KeyEvent.VK_EURO_SIGN:
                currency_euro = targetFlag;
                break;
            case KeyEvent.VK_DOLLAR:
                currency_dollar = targetFlag;
                break;

            /*
            Other keys that can be accessed without using e.g. shift and some brackets
             */
            case KeyEvent.VK_NUMBER_SIGN:
                key_hash = targetFlag;
                break;
            case KeyEvent.VK_LEFT_PARENTHESIS:
                parenthesis_open = targetFlag;
                break;
            case KeyEvent.VK_RIGHT_PARENTHESIS:
                parenthesis_close = targetFlag;
                break;
            case KeyEvent.VK_BRACELEFT:
                brace_open = targetFlag;
                break;
            case KeyEvent.VK_BRACERIGHT:
                brace_close = targetFlag;
                break;
            case KeyEvent.VK_OPEN_BRACKET:
                squareBracket_open = targetFlag;
                break;
            case KeyEvent.VK_CLOSE_BRACKET:
                squareBracket_close = targetFlag;
                break;
        }
    }

    public boolean isEnter() {
        return enter;
    }

    public boolean isBackspace() {
        return backspace;
    }

    public boolean isDelete() {
        return delete;
    }

    public boolean isTab() {
        return tab;
    }

    public boolean isShift() {
        return shift;
    }

    public boolean isControl() {
        return control;
    }

    public boolean isAlt() {
        return alt;
    }

    public boolean isCaps_lock() {
        return caps_lock;
    }

    public boolean isEscape() {
        return escape;
    }

    public boolean isPage_up() {
        return page_up;
    }

    public boolean isPage_down() {
        return page_down;
    }

    public boolean isSpace() {
        return space;
    }

    public boolean isArrow_right() {
        return arrow_right;
    }

    public boolean isArrow_left() {
        return arrow_left;
    }

    public boolean isArrow_up() {
        return arrow_up;
    }

    public boolean isArrow_down() {
        return arrow_down;
    }

    public boolean isKey_comma() {
        return key_comma;
    }

    public boolean isKey_period() {
        return key_period;
    }

    public boolean isKey_minus() {
        return key_minus;
    }

    public boolean isKey_plus() {
        return key_plus;
    }

    public boolean isKey_less() {
        return key_less;
    }

    public boolean isKey_greater() {
        return key_greater;
    }

    public boolean isNumber_0() {
        return number_0;
    }

    public boolean isNumber_1() {
        return number_1;
    }

    public boolean isNumber_2() {
        return number_2;
    }

    public boolean isNumber_3() {
        return number_3;
    }

    public boolean isNumber_4() {
        return number_4;
    }

    public boolean isNumber_5() {
        return number_5;
    }

    public boolean isNumber_6() {
        return number_6;
    }

    public boolean isNumber_7() {
        return number_7;
    }

    public boolean isNumber_8() {
        return number_8;
    }

    public boolean isNumber_9() {
        return number_9;
    }

    public boolean isKey_a() {
        return key_a;
    }

    public boolean isKey_b() {
        return key_b;
    }

    public boolean isKey_c() {
        return key_c;
    }

    public boolean isKey_d() {
        return key_d;
    }

    public boolean isKey_e() {
        return key_e;
    }

    public boolean isKey_f() {
        return key_f;
    }

    public boolean isKey_g() {
        return key_g;
    }

    public boolean isKey_h() {
        return key_h;
    }

    public boolean isKey_i() {
        return key_i;
    }

    public boolean isKey_j() {
        return key_j;
    }

    public boolean isKey_k() {
        return key_k;
    }

    public boolean isKey_l() {
        return key_l;
    }

    public boolean isKey_m() {
        return key_m;
    }

    public boolean isKey_n() {
        return key_n;
    }

    public boolean isKey_o() {
        return key_o;
    }

    public boolean isKey_p() {
        return key_p;
    }

    public boolean isKey_q() {
        return key_q;
    }

    public boolean isKey_r() {
        return key_r;
    }

    public boolean isKey_s() {
        return key_s;
    }

    public boolean isKey_t() {
        return key_t;
    }

    public boolean isKey_u() {
        return key_u;
    }

    public boolean isKey_v() {
        return key_v;
    }

    public boolean isKey_w() {
        return key_w;
    }

    public boolean isKey_x() {
        return key_x;
    }

    public boolean isKey_y() {
        return key_y;
    }

    public boolean isKey_z() {
        return key_z;
    }

    public boolean isNumpad_0() {
        return numpad_0;
    }

    public boolean isNumpad_1() {
        return numpad_1;
    }

    public boolean isNumpad_2() {
        return numpad_2;
    }

    public boolean isNumpad_3() {
        return numpad_3;
    }

    public boolean isNumpad_4() {
        return numpad_4;
    }

    public boolean isNumpad_5() {
        return numpad_5;
    }

    public boolean isNumpad_6() {
        return numpad_6;
    }

    public boolean isNumpad_7() {
        return numpad_7;
    }

    public boolean isNumpad_8() {
        return numpad_8;
    }

    public boolean isNumpad_9() {
        return numpad_9;
    }

    public boolean isFunction_1() {
        return function_1;
    }

    public boolean isFunction_2() {
        return function_2;
    }

    public boolean isFunction_3() {
        return function_3;
    }

    public boolean isFunction_4() {
        return function_4;
    }

    public boolean isFunction_5() {
        return function_5;
    }

    public boolean isFunction_6() {
        return function_6;
    }

    public boolean isFunction_7() {
        return function_7;
    }

    public boolean isFunction_8() {
        return function_8;
    }

    public boolean isFunction_9() {
        return function_9;
    }

    public boolean isFunction_10() {
        return function_10;
    }

    public boolean isFunction_11() {
        return function_11;
    }

    public boolean isFunction_12() {
        return function_12;
    }

    public boolean isCurrency_euro() {
        return currency_euro;
    }

    public boolean isCurrency_dollar() {
        return currency_dollar;
    }

    public boolean isKey_hash() {
        return key_hash;
    }

    public boolean isParenthesis_open() {
        return parenthesis_open;
    }

    public boolean isParenthesis_close() {
        return parenthesis_close;
    }

    public boolean isBrace_open() {
        return brace_open;
    }

    public boolean isBrace_close() {
        return brace_close;
    }

    public boolean isSquareBracket_open() {
        return squareBracket_open;
    }

    public boolean isSquareBracket_close() {
        return squareBracket_close;
    }
}
