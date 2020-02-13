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

package de.edgelord.saltyengine.input;

import java.awt.event.KeyEvent;

/**
 * Represents a virtual keyboard with all common keys.
 * The states of the keys represented as booleans are
 * updated on every <code>KeyEvent</code> that occurred
 * in the {@link de.edgelord.saltyengine.displaymanager.display.Display}.
 */
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
    private boolean capsLock = false;
    private boolean escape = false;
    private boolean pageUp = false;
    private boolean pageDown = false;
    private boolean space = false;

    /*
    Arrow Keys
     */
    private boolean rightArrow = false;
    private boolean leftArrow = false;
    private boolean upArrow = false;
    private boolean downArrow = false;

    /*
    Math keys
     */
    private boolean comma = false;
    private boolean period = false; /* = "."*/
    private boolean minus = false;
    private boolean plus = false;
    private boolean less = false;
    private boolean greater = false;

    /*
    Numbers from 0 to 9
     */
    private boolean number0 = false;
    private boolean number1 = false;
    private boolean number2 = false;
    private boolean number3 = false;
    private boolean number4 = false;
    private boolean number5 = false;
    private boolean number6 = false;
    private boolean number7 = false;
    private boolean number8 = false;
    private boolean number9 = false;

    /*
    Letters from A to Z
     */
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    /*
    Numpad-numbers from 0 to 9
     */
    private boolean numpad0 = false;
    private boolean numpad1 = false;
    private boolean numpad2 = false;
    private boolean numpad3 = false;
    private boolean numpad4 = false;
    private boolean numpad5 = false;
    private boolean numpad6 = false;
    private boolean numpad7 = false;
    private boolean numpad8 = false;
    private boolean numpad9 = false;

    /*
    Function keys from 1 to 12
     */
    private boolean function1 = false;
    private boolean function2 = false;
    private boolean function3 = false;
    private boolean function4 = false;
    private boolean function5 = false;
    private boolean function6 = false;
    private boolean function7 = false;
    private boolean function8 = false;
    private boolean function9 = false;
    private boolean function10 = false;
    private boolean function11 = false;
    private boolean function12 = false;

    /*
    Currency signs
     */
    private boolean euro = false;
    private boolean dollar = false;

    /*
    Other keys that can be accessed without using e.g. shift and some brackets
     */
    private boolean hash = false;
    private boolean openParenthesis = false;
    private boolean closeParenthesis = false;
    private boolean openBrace = false;
    private boolean closeBrace = false;
    private boolean openSquareBracket = false;
    private boolean closeSquareBracket = false;

    public void handleKeyPressed(KeyEvent keyPressedEvent) {

        flagMatchingKeyAs(true, keyPressedEvent);
    }

    public void handleKeyReleased(KeyEvent keyReleasedEvent) {

        flagMatchingKeyAs(false, keyReleasedEvent);
    }

    private void flagMatchingKeyAs(boolean targetFlag, KeyEvent event) {

        if (event.getKeyChar() == ' ') {
            space = targetFlag;
        }

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
                capsLock = targetFlag;
                break;
            case KeyEvent.VK_ESCAPE:
                escape = targetFlag;
                break;
            case KeyEvent.VK_PAGE_UP:
                pageUp = targetFlag;
                break;
            case KeyEvent.VK_PAGE_DOWN:
                pageDown = targetFlag;
                break;

            /*
            Arrow keys
             */
            case KeyEvent.VK_RIGHT:
                rightArrow = targetFlag;
                break;
            case KeyEvent.VK_LEFT:
                leftArrow = targetFlag;
                break;
            case KeyEvent.VK_UP:
                upArrow = targetFlag;
                break;
            case KeyEvent.VK_DOWN:
                downArrow = targetFlag;
                break;

            /*
            Math keys
             */
            case KeyEvent.VK_COMMA:
                comma = targetFlag;
                break;
            case KeyEvent.VK_PERIOD:
                period = targetFlag;
                break;
            case KeyEvent.VK_MINUS:
                minus = targetFlag;
                break;
            case KeyEvent.VK_PLUS:
                plus = targetFlag;
                break;

            /*
            Numbers from 0 to 9
             */
            case KeyEvent.VK_0:
                number0 = targetFlag;
                break;
            case KeyEvent.VK_1:
                number1 = targetFlag;
                break;
            case KeyEvent.VK_2:
                number2 = targetFlag;
                break;
            case KeyEvent.VK_3:
                number3 = targetFlag;
                break;
            case KeyEvent.VK_4:
                number4 = targetFlag;
                break;
            case KeyEvent.VK_5:
                number5 = targetFlag;
                break;
            case KeyEvent.VK_6:
                number6 = targetFlag;
                break;
            case KeyEvent.VK_7:
                number7 = targetFlag;
                break;
            case KeyEvent.VK_8:
                number8 = targetFlag;
                break;
            case KeyEvent.VK_9:
                number9 = targetFlag;
                break;

            /*
            Letters from A to Z
             */
            case KeyEvent.VK_A:
                a = targetFlag;
                break;
            case KeyEvent.VK_B:
                b = targetFlag;
                break;
            case KeyEvent.VK_C:
                c = targetFlag;
                break;
            case KeyEvent.VK_D:
                d = targetFlag;
                break;
            case KeyEvent.VK_E:
                e = targetFlag;
                break;
            case KeyEvent.VK_F:
                f = targetFlag;
                break;
            case KeyEvent.VK_G:
                g = targetFlag;
                break;
            case KeyEvent.VK_H:
                h = targetFlag;
                break;
            case KeyEvent.VK_I:
                i = targetFlag;
                break;
            case KeyEvent.VK_J:
                j = targetFlag;
                break;
            case KeyEvent.VK_K:
                k = targetFlag;
                break;
            case KeyEvent.VK_L:
                l = targetFlag;
                break;
            case KeyEvent.VK_M:
                m = targetFlag;
                break;
            case KeyEvent.VK_N:
                n = targetFlag;
                break;
            case KeyEvent.VK_O:
                o = targetFlag;
                break;
            case KeyEvent.VK_P:
                p = targetFlag;
                break;
            case KeyEvent.VK_Q:
                q = targetFlag;
                break;
            case KeyEvent.VK_R:
                r = targetFlag;
                break;
            case KeyEvent.VK_S:
                s = targetFlag;
                break;
            case KeyEvent.VK_T:
                t = targetFlag;
                break;
            case KeyEvent.VK_U:
                u = targetFlag;
                break;
            case KeyEvent.VK_V:
                v = targetFlag;
                break;
            case KeyEvent.VK_W:
                w = targetFlag;
                break;
            case KeyEvent.VK_X:
                x = targetFlag;
                break;
            case KeyEvent.VK_Y:
                y = targetFlag;
                break;
            case KeyEvent.VK_Z:
                z = targetFlag;
                break;

            /*
            Numpad-numbers from 0 to 9
             */
            case KeyEvent.VK_NUMPAD0:
                numpad0 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD1:
                numpad1 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD2:
                numpad2 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD3:
                numpad3 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD4:
                numpad4 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD5:
                numpad5 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD6:
                numpad6 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD7:
                numpad7 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD8:
                numpad8 = targetFlag;
                break;
            case KeyEvent.VK_NUMPAD9:
                numpad9 = targetFlag;
                break;

            /*
            Function keys from 1 to 12
             */
            case KeyEvent.VK_F1:
                function1 = targetFlag;
                break;
            case KeyEvent.VK_F2:
                function2 = targetFlag;
                break;
            case KeyEvent.VK_F3:
                function3 = targetFlag;
                break;
            case KeyEvent.VK_F4:
                function4 = targetFlag;
                break;
            case KeyEvent.VK_F5:
                function5 = targetFlag;
                break;
            case KeyEvent.VK_F6:
                function6 = targetFlag;
                break;
            case KeyEvent.VK_F7:
                function7 = targetFlag;
                break;
            case KeyEvent.VK_F8:
                function8 = targetFlag;
                break;
            case KeyEvent.VK_F9:
                function9 = targetFlag;
                break;
            case KeyEvent.VK_F10:
                function10 = targetFlag;
                break;
            case KeyEvent.VK_F11:
                function11 = targetFlag;
                break;
            case KeyEvent.VK_F12:
                function12 = targetFlag;
                break;

            /*
            Currency signs
             */
            case KeyEvent.VK_EURO_SIGN:
                euro = targetFlag;
                break;
            case KeyEvent.VK_DOLLAR:
                dollar = targetFlag;
                break;

            /*
            Other keys that can be accessed without using e.g. shift and some brackets
             */
            case KeyEvent.VK_NUMBER_SIGN:
                hash = targetFlag;
                break;
            case KeyEvent.VK_LEFT_PARENTHESIS:
                openParenthesis = targetFlag;
                break;
            case KeyEvent.VK_RIGHT_PARENTHESIS:
                closeParenthesis = targetFlag;
                break;
            case KeyEvent.VK_BRACELEFT:
                openBrace = targetFlag;
                break;
            case KeyEvent.VK_BRACERIGHT:
                closeBrace = targetFlag;
                break;
            case KeyEvent.VK_OPEN_BRACKET:
                openSquareBracket = targetFlag;
                break;
            case KeyEvent.VK_CLOSE_BRACKET:
                closeSquareBracket = targetFlag;
                break;
        }
    }

    /**
     * Gets {@link #enter}.
     *
     * @return the value of {@link #enter}
     */
    public boolean isEnter() {
        return enter;
    }

    /**
     * Gets {@link #backspace}.
     *
     * @return the value of {@link #backspace}
     */
    public boolean isBackspace() {
        return backspace;
    }

    /**
     * Gets {@link #delete}.
     *
     * @return the value of {@link #delete}
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * Gets {@link #tab}.
     *
     * @return the value of {@link #tab}
     */
    public boolean isTab() {
        return tab;
    }

    /**
     * Gets {@link #shift}.
     *
     * @return the value of {@link #shift}
     */
    public boolean isShift() {
        return shift;
    }

    /**
     * Gets {@link #control}.
     *
     * @return the value of {@link #control}
     */
    public boolean isControl() {
        return control;
    }

    /**
     * Gets {@link #alt}.
     *
     * @return the value of {@link #alt}
     */
    public boolean isAlt() {
        return alt;
    }

    /**
     * Gets {@link #capsLock}.
     *
     * @return the value of {@link #capsLock}
     */
    public boolean isCapsLock() {
        return capsLock;
    }

    /**
     * Gets {@link #escape}.
     *
     * @return the value of {@link #escape}
     */
    public boolean isEscape() {
        return escape;
    }

    /**
     * Gets {@link #pageUp}.
     *
     * @return the value of {@link #pageUp}
     */
    public boolean isPageUp() {
        return pageUp;
    }

    /**
     * Gets {@link #pageDown}.
     *
     * @return the value of {@link #pageDown}
     */
    public boolean isPageDown() {
        return pageDown;
    }

    /**
     * Gets {@link #space}.
     *
     * @return the value of {@link #space}
     */
    public boolean isSpace() {
        return space;
    }

    /**
     * Gets {@link #rightArrow}.
     *
     * @return the value of {@link #rightArrow}
     */
    public boolean isRightArrow() {
        return rightArrow;
    }

    /**
     * Gets {@link #leftArrow}.
     *
     * @return the value of {@link #leftArrow}
     */
    public boolean isLeftArrow() {
        return leftArrow;
    }

    /**
     * Gets {@link #upArrow}.
     *
     * @return the value of {@link #upArrow}
     */
    public boolean isUpArrow() {
        return upArrow;
    }

    /**
     * Gets {@link #downArrow}.
     *
     * @return the value of {@link #downArrow}
     */
    public boolean isDownArrow() {
        return downArrow;
    }

    /**
     * Gets {@link #comma}.
     *
     * @return the value of {@link #comma}
     */
    public boolean isComma() {
        return comma;
    }

    /**
     * Gets {@link #period}.
     *
     * @return the value of {@link #period}
     */
    public boolean isPeriod() {
        return period;
    }

    /**
     * Gets {@link #minus}.
     *
     * @return the value of {@link #minus}
     */
    public boolean isMinus() {
        return minus;
    }

    /**
     * Gets {@link #plus}.
     *
     * @return the value of {@link #plus}
     */
    public boolean isPlus() {
        return plus;
    }

    /**
     * Gets {@link #less}.
     *
     * @return the value of {@link #less}
     */
    public boolean isLess() {
        return less;
    }

    /**
     * Gets {@link #greater}.
     *
     * @return the value of {@link #greater}
     */
    public boolean isGreater() {
        return greater;
    }

    /**
     * Gets {@link #number0}.
     *
     * @return the value of {@link #number0}
     */
    public boolean isNumber0() {
        return number0;
    }

    /**
     * Gets {@link #number1}.
     *
     * @return the value of {@link #number1}
     */
    public boolean isNumber1() {
        return number1;
    }

    /**
     * Gets {@link #number2}.
     *
     * @return the value of {@link #number2}
     */
    public boolean isNumber2() {
        return number2;
    }

    /**
     * Gets {@link #number3}.
     *
     * @return the value of {@link #number3}
     */
    public boolean isNumber3() {
        return number3;
    }

    /**
     * Gets {@link #number4}.
     *
     * @return the value of {@link #number4}
     */
    public boolean isNumber4() {
        return number4;
    }

    /**
     * Gets {@link #number5}.
     *
     * @return the value of {@link #number5}
     */
    public boolean isNumber5() {
        return number5;
    }

    /**
     * Gets {@link #number6}.
     *
     * @return the value of {@link #number6}
     */
    public boolean isNumber6() {
        return number6;
    }

    /**
     * Gets {@link #number7}.
     *
     * @return the value of {@link #number7}
     */
    public boolean isNumber7() {
        return number7;
    }

    /**
     * Gets {@link #number8}.
     *
     * @return the value of {@link #number8}
     */
    public boolean isNumber8() {
        return number8;
    }

    /**
     * Gets {@link #number9}.
     *
     * @return the value of {@link #number9}
     */
    public boolean isNumber9() {
        return number9;
    }

    /**
     * Gets {@link #a}.
     *
     * @return the value of {@link #a}
     */
    public boolean isA() {
        return a;
    }

    /**
     * Gets {@link #b}.
     *
     * @return the value of {@link #b}
     */
    public boolean isB() {
        return b;
    }

    /**
     * Gets {@link #c}.
     *
     * @return the value of {@link #c}
     */
    public boolean isC() {
        return c;
    }

    /**
     * Gets {@link #d}.
     *
     * @return the value of {@link #d}
     */
    public boolean isD() {
        return d;
    }

    /**
     * Gets {@link #e}.
     *
     * @return the value of {@link #e}
     */
    public boolean isE() {
        return e;
    }

    /**
     * Gets {@link #f}.
     *
     * @return the value of {@link #f}
     */
    public boolean isF() {
        return f;
    }

    /**
     * Gets {@link #g}.
     *
     * @return the value of {@link #g}
     */
    public boolean isG() {
        return g;
    }

    /**
     * Gets {@link #h}.
     *
     * @return the value of {@link #h}
     */
    public boolean isH() {
        return h;
    }

    /**
     * Gets {@link #i}.
     *
     * @return the value of {@link #i}
     */
    public boolean isI() {
        return i;
    }

    /**
     * Gets {@link #j}.
     *
     * @return the value of {@link #j}
     */
    public boolean isJ() {
        return j;
    }

    /**
     * Gets {@link #k}.
     *
     * @return the value of {@link #k}
     */
    public boolean isK() {
        return k;
    }

    /**
     * Gets {@link #l}.
     *
     * @return the value of {@link #l}
     */
    public boolean isL() {
        return l;
    }

    /**
     * Gets {@link #m}.
     *
     * @return the value of {@link #m}
     */
    public boolean isM() {
        return m;
    }

    /**
     * Gets {@link #n}.
     *
     * @return the value of {@link #n}
     */
    public boolean isN() {
        return n;
    }

    /**
     * Gets {@link #o}.
     *
     * @return the value of {@link #o}
     */
    public boolean isO() {
        return o;
    }

    /**
     * Gets {@link #p}.
     *
     * @return the value of {@link #p}
     */
    public boolean isP() {
        return p;
    }

    /**
     * Gets {@link #q}.
     *
     * @return the value of {@link #q}
     */
    public boolean isQ() {
        return q;
    }

    /**
     * Gets {@link #r}.
     *
     * @return the value of {@link #r}
     */
    public boolean isR() {
        return r;
    }

    /**
     * Gets {@link #s}.
     *
     * @return the value of {@link #s}
     */
    public boolean isS() {
        return s;
    }

    /**
     * Gets {@link #t}.
     *
     * @return the value of {@link #t}
     */
    public boolean isT() {
        return t;
    }

    /**
     * Gets {@link #u}.
     *
     * @return the value of {@link #u}
     */
    public boolean isU() {
        return u;
    }

    /**
     * Gets {@link #v}.
     *
     * @return the value of {@link #v}
     */
    public boolean isV() {
        return v;
    }

    /**
     * Gets {@link #w}.
     *
     * @return the value of {@link #w}
     */
    public boolean isW() {
        return w;
    }

    /**
     * Gets {@link #x}.
     *
     * @return the value of {@link #x}
     */
    public boolean isX() {
        return x;
    }

    /**
     * Gets {@link #y}.
     *
     * @return the value of {@link #y}
     */
    public boolean isY() {
        return y;
    }

    /**
     * Gets {@link #z}.
     *
     * @return the value of {@link #z}
     */
    public boolean isZ() {
        return z;
    }

    /**
     * Gets {@link #numpad0}.
     *
     * @return the value of {@link #numpad0}
     */
    public boolean isNumpad0() {
        return numpad0;
    }

    /**
     * Gets {@link #numpad1}.
     *
     * @return the value of {@link #numpad1}
     */
    public boolean isNumpad1() {
        return numpad1;
    }

    /**
     * Gets {@link #numpad2}.
     *
     * @return the value of {@link #numpad2}
     */
    public boolean isNumpad2() {
        return numpad2;
    }

    /**
     * Gets {@link #numpad3}.
     *
     * @return the value of {@link #numpad3}
     */
    public boolean isNumpad3() {
        return numpad3;
    }

    /**
     * Gets {@link #numpad4}.
     *
     * @return the value of {@link #numpad4}
     */
    public boolean isNumpad4() {
        return numpad4;
    }

    /**
     * Gets {@link #numpad5}.
     *
     * @return the value of {@link #numpad5}
     */
    public boolean isNumpad5() {
        return numpad5;
    }

    /**
     * Gets {@link #numpad6}.
     *
     * @return the value of {@link #numpad6}
     */
    public boolean isNumpad6() {
        return numpad6;
    }

    /**
     * Gets {@link #numpad7}.
     *
     * @return the value of {@link #numpad7}
     */
    public boolean isNumpad7() {
        return numpad7;
    }

    /**
     * Gets {@link #numpad8}.
     *
     * @return the value of {@link #numpad8}
     */
    public boolean isNumpad8() {
        return numpad8;
    }

    /**
     * Gets {@link #numpad9}.
     *
     * @return the value of {@link #numpad9}
     */
    public boolean isNumpad9() {
        return numpad9;
    }

    /**
     * Gets {@link #function1}.
     *
     * @return the value of {@link #function1}
     */
    public boolean isFunction1() {
        return function1;
    }

    /**
     * Gets {@link #function2}.
     *
     * @return the value of {@link #function2}
     */
    public boolean isFunction2() {
        return function2;
    }

    /**
     * Gets {@link #function3}.
     *
     * @return the value of {@link #function3}
     */
    public boolean isFunction3() {
        return function3;
    }

    /**
     * Gets {@link #function4}.
     *
     * @return the value of {@link #function4}
     */
    public boolean isFunction4() {
        return function4;
    }

    /**
     * Gets {@link #function5}.
     *
     * @return the value of {@link #function5}
     */
    public boolean isFunction5() {
        return function5;
    }

    /**
     * Gets {@link #function6}.
     *
     * @return the value of {@link #function6}
     */
    public boolean isFunction6() {
        return function6;
    }

    /**
     * Gets {@link #function7}.
     *
     * @return the value of {@link #function7}
     */
    public boolean isFunction7() {
        return function7;
    }

    /**
     * Gets {@link #function8}.
     *
     * @return the value of {@link #function8}
     */
    public boolean isFunction8() {
        return function8;
    }

    /**
     * Gets {@link #function9}.
     *
     * @return the value of {@link #function9}
     */
    public boolean isFunction9() {
        return function9;
    }

    /**
     * Gets {@link #function10}.
     *
     * @return the value of {@link #function10}
     */
    public boolean isFunction10() {
        return function10;
    }

    /**
     * Gets {@link #function11}.
     *
     * @return the value of {@link #function11}
     */
    public boolean isFunction11() {
        return function11;
    }

    /**
     * Gets {@link #function12}.
     *
     * @return the value of {@link #function12}
     */
    public boolean isFunction12() {
        return function12;
    }

    /**
     * Gets {@link #euro}.
     *
     * @return the value of {@link #euro}
     */
    public boolean isEuro() {
        return euro;
    }

    /**
     * Gets {@link #dollar}.
     *
     * @return the value of {@link #dollar}
     */
    public boolean isDollar() {
        return dollar;
    }

    /**
     * Gets {@link #hash}.
     *
     * @return the value of {@link #hash}
     */
    public boolean isHash() {
        return hash;
    }

    /**
     * Gets {@link #openParenthesis}.
     *
     * @return the value of {@link #openParenthesis}
     */
    public boolean isOpenParenthesis() {
        return openParenthesis;
    }

    /**
     * Gets {@link #closeParenthesis}.
     *
     * @return the value of {@link #closeParenthesis}
     */
    public boolean isCloseParenthesis() {
        return closeParenthesis;
    }

    /**
     * Gets {@link #openBrace}.
     *
     * @return the value of {@link #openBrace}
     */
    public boolean isOpenBrace() {
        return openBrace;
    }

    /**
     * Gets {@link #closeBrace}.
     *
     * @return the value of {@link #closeBrace}
     */
    public boolean isCloseBrace() {
        return closeBrace;
    }

    /**
     * Gets {@link #openSquareBracket}.
     *
     * @return the value of {@link #openSquareBracket}
     */
    public boolean isOpenSquareBracket() {
        return openSquareBracket;
    }

    /**
     * Gets {@link #closeSquareBracket}.
     *
     * @return the value of {@link #closeSquareBracket}
     */
    public boolean isCloseSquareBracket() {
        return closeSquareBracket;
    }
}
