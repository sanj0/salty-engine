/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.input;

public class KeyBoard {

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

    /*
    Arrow Keys
     */
    private boolean key_right = false;
    private boolean key_left = false;
    private boolean key_up = false;
    private boolean key_down = false;

    /*
    Math keys {, . - +}
     */
    private boolean key_comma = false;
    private boolean key_period = false;
    private boolean key_minus = false;
    private boolean key_plus = false;

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
    private boolean brace_open = false;
    private boolean brace_close = false;
    private boolean angelBracket_open = false;
    private boolean angelBracket_close = false;
    private boolean squareBracket_open = false;
    private boolean squareBracket_close = false;

    public boolean isEnter() {
        return enter;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    public boolean isBackspace() {
        return backspace;
    }

    public void setBackspace(boolean backspace) {
        this.backspace = backspace;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isTab() {
        return tab;
    }

    public void setTab(boolean tab) {
        this.tab = tab;
    }

    public boolean isShift() {
        return shift;
    }

    public void setShift(boolean shift) {
        this.shift = shift;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public boolean isAlt() {
        return alt;
    }

    public void setAlt(boolean alt) {
        this.alt = alt;
    }

    public boolean isCaps_lock() {
        return caps_lock;
    }

    public void setCaps_lock(boolean caps_lock) {
        this.caps_lock = caps_lock;
    }

    public boolean isEscape() {
        return escape;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    public boolean isPage_up() {
        return page_up;
    }

    public void setPage_up(boolean page_up) {
        this.page_up = page_up;
    }

    public boolean isPage_down() {
        return page_down;
    }

    public void setPage_down(boolean page_down) {
        this.page_down = page_down;
    }

    public boolean isKey_right() {
        return key_right;
    }

    public void setKey_right(boolean key_right) {
        this.key_right = key_right;
    }

    public boolean isKey_left() {
        return key_left;
    }

    public void setKey_left(boolean key_left) {
        this.key_left = key_left;
    }

    public boolean isKey_up() {
        return key_up;
    }

    public void setKey_up(boolean key_up) {
        this.key_up = key_up;
    }

    public boolean isKey_down() {
        return key_down;
    }

    public void setKey_down(boolean key_down) {
        this.key_down = key_down;
    }

    public boolean isKey_comma() {
        return key_comma;
    }

    public void setKey_comma(boolean key_comma) {
        this.key_comma = key_comma;
    }

    public boolean isKey_period() {
        return key_period;
    }

    public void setKey_period(boolean key_period) {
        this.key_period = key_period;
    }

    public boolean isKey_minus() {
        return key_minus;
    }

    public void setKey_minus(boolean key_minus) {
        this.key_minus = key_minus;
    }

    public boolean isKey_plus() {
        return key_plus;
    }

    public void setKey_plus(boolean key_plus) {
        this.key_plus = key_plus;
    }

    public boolean isNumber_0() {
        return number_0;
    }

    public void setNumber_0(boolean number_0) {
        this.number_0 = number_0;
    }

    public boolean isNumber_1() {
        return number_1;
    }

    public void setNumber_1(boolean number_1) {
        this.number_1 = number_1;
    }

    public boolean isNumber_2() {
        return number_2;
    }

    public void setNumber_2(boolean number_2) {
        this.number_2 = number_2;
    }

    public boolean isNumber_3() {
        return number_3;
    }

    public void setNumber_3(boolean number_3) {
        this.number_3 = number_3;
    }

    public boolean isNumber_4() {
        return number_4;
    }

    public void setNumber_4(boolean number_4) {
        this.number_4 = number_4;
    }

    public boolean isNumber_5() {
        return number_5;
    }

    public void setNumber_5(boolean number_5) {
        this.number_5 = number_5;
    }

    public boolean isNumber_6() {
        return number_6;
    }

    public void setNumber_6(boolean number_6) {
        this.number_6 = number_6;
    }

    public boolean isNumber_7() {
        return number_7;
    }

    public void setNumber_7(boolean number_7) {
        this.number_7 = number_7;
    }

    public boolean isNumber_8() {
        return number_8;
    }

    public void setNumber_8(boolean number_8) {
        this.number_8 = number_8;
    }

    public boolean isNumber_9() {
        return number_9;
    }

    public void setNumber_9(boolean number_9) {
        this.number_9 = number_9;
    }

    public boolean isKey_a() {
        return key_a;
    }

    public void setKey_a(boolean key_a) {
        this.key_a = key_a;
    }

    public boolean isKey_b() {
        return key_b;
    }

    public void setKey_b(boolean key_b) {
        this.key_b = key_b;
    }

    public boolean isKey_c() {
        return key_c;
    }

    public void setKey_c(boolean key_c) {
        this.key_c = key_c;
    }

    public boolean isKey_d() {
        return key_d;
    }

    public void setKey_d(boolean key_d) {
        this.key_d = key_d;
    }

    public boolean isKey_e() {
        return key_e;
    }

    public void setKey_e(boolean key_e) {
        this.key_e = key_e;
    }

    public boolean isKey_f() {
        return key_f;
    }

    public void setKey_f(boolean key_f) {
        this.key_f = key_f;
    }

    public boolean isKey_g() {
        return key_g;
    }

    public void setKey_g(boolean key_g) {
        this.key_g = key_g;
    }

    public boolean isKey_h() {
        return key_h;
    }

    public void setKey_h(boolean key_h) {
        this.key_h = key_h;
    }

    public boolean isKey_i() {
        return key_i;
    }

    public void setKey_i(boolean key_i) {
        this.key_i = key_i;
    }

    public boolean isKey_j() {
        return key_j;
    }

    public void setKey_j(boolean key_j) {
        this.key_j = key_j;
    }

    public boolean isKey_k() {
        return key_k;
    }

    public void setKey_k(boolean key_k) {
        this.key_k = key_k;
    }

    public boolean isKey_l() {
        return key_l;
    }

    public void setKey_l(boolean key_l) {
        this.key_l = key_l;
    }

    public boolean isKey_m() {
        return key_m;
    }

    public void setKey_m(boolean key_m) {
        this.key_m = key_m;
    }

    public boolean isKey_n() {
        return key_n;
    }

    public void setKey_n(boolean key_n) {
        this.key_n = key_n;
    }

    public boolean isKey_o() {
        return key_o;
    }

    public void setKey_o(boolean key_o) {
        this.key_o = key_o;
    }

    public boolean isKey_p() {
        return key_p;
    }

    public void setKey_p(boolean key_p) {
        this.key_p = key_p;
    }

    public boolean isKey_q() {
        return key_q;
    }

    public void setKey_q(boolean key_q) {
        this.key_q = key_q;
    }

    public boolean isKey_r() {
        return key_r;
    }

    public void setKey_r(boolean key_r) {
        this.key_r = key_r;
    }

    public boolean isKey_s() {
        return key_s;
    }

    public void setKey_s(boolean key_s) {
        this.key_s = key_s;
    }

    public boolean isKey_t() {
        return key_t;
    }

    public void setKey_t(boolean key_t) {
        this.key_t = key_t;
    }

    public boolean isKey_u() {
        return key_u;
    }

    public void setKey_u(boolean key_u) {
        this.key_u = key_u;
    }

    public boolean isKey_v() {
        return key_v;
    }

    public void setKey_v(boolean key_v) {
        this.key_v = key_v;
    }

    public boolean isKey_w() {
        return key_w;
    }

    public void setKey_w(boolean key_w) {
        this.key_w = key_w;
    }

    public boolean isKey_x() {
        return key_x;
    }

    public void setKey_x(boolean key_x) {
        this.key_x = key_x;
    }

    public boolean isKey_y() {
        return key_y;
    }

    public void setKey_y(boolean key_y) {
        this.key_y = key_y;
    }

    public boolean isKey_z() {
        return key_z;
    }

    public void setKey_z(boolean key_z) {
        this.key_z = key_z;
    }

    public boolean isNumpad_0() {
        return numpad_0;
    }

    public void setNumpad_0(boolean numpad_0) {
        this.numpad_0 = numpad_0;
    }

    public boolean isNumpad_1() {
        return numpad_1;
    }

    public void setNumpad_1(boolean numpad_1) {
        this.numpad_1 = numpad_1;
    }

    public boolean isNumpad_2() {
        return numpad_2;
    }

    public void setNumpad_2(boolean numpad_2) {
        this.numpad_2 = numpad_2;
    }

    public boolean isNumpad_3() {
        return numpad_3;
    }

    public void setNumpad_3(boolean numpad_3) {
        this.numpad_3 = numpad_3;
    }

    public boolean isNumpad_4() {
        return numpad_4;
    }

    public void setNumpad_4(boolean numpad_4) {
        this.numpad_4 = numpad_4;
    }

    public boolean isNumpad_5() {
        return numpad_5;
    }

    public void setNumpad_5(boolean numpad_5) {
        this.numpad_5 = numpad_5;
    }

    public boolean isNumpad_6() {
        return numpad_6;
    }

    public void setNumpad_6(boolean numpad_6) {
        this.numpad_6 = numpad_6;
    }

    public boolean isNumpad_7() {
        return numpad_7;
    }

    public void setNumpad_7(boolean numpad_7) {
        this.numpad_7 = numpad_7;
    }

    public boolean isNumpad_8() {
        return numpad_8;
    }

    public void setNumpad_8(boolean numpad_8) {
        this.numpad_8 = numpad_8;
    }

    public boolean isNumpad_9() {
        return numpad_9;
    }

    public void setNumpad_9(boolean numpad_9) {
        this.numpad_9 = numpad_9;
    }

    public boolean isFunction_1() {
        return function_1;
    }

    public void setFunction_1(boolean function_1) {
        this.function_1 = function_1;
    }

    public boolean isFunction_2() {
        return function_2;
    }

    public void setFunction_2(boolean function_2) {
        this.function_2 = function_2;
    }

    public boolean isFunction_3() {
        return function_3;
    }

    public void setFunction_3(boolean function_3) {
        this.function_3 = function_3;
    }

    public boolean isFunction_4() {
        return function_4;
    }

    public void setFunction_4(boolean function_4) {
        this.function_4 = function_4;
    }

    public boolean isFunction_5() {
        return function_5;
    }

    public void setFunction_5(boolean function_5) {
        this.function_5 = function_5;
    }

    public boolean isFunction_6() {
        return function_6;
    }

    public void setFunction_6(boolean function_6) {
        this.function_6 = function_6;
    }

    public boolean isFunction_7() {
        return function_7;
    }

    public void setFunction_7(boolean function_7) {
        this.function_7 = function_7;
    }

    public boolean isFunction_8() {
        return function_8;
    }

    public void setFunction_8(boolean function_8) {
        this.function_8 = function_8;
    }

    public boolean isFunction_9() {
        return function_9;
    }

    public void setFunction_9(boolean function_9) {
        this.function_9 = function_9;
    }

    public boolean isFunction_10() {
        return function_10;
    }

    public void setFunction_10(boolean function_10) {
        this.function_10 = function_10;
    }

    public boolean isFunction_11() {
        return function_11;
    }

    public void setFunction_11(boolean function_11) {
        this.function_11 = function_11;
    }

    public boolean isFunction_12() {
        return function_12;
    }

    public void setFunction_12(boolean function_12) {
        this.function_12 = function_12;
    }

    public boolean isCurrency_euro() {
        return currency_euro;
    }

    public void setCurrency_euro(boolean currency_euro) {
        this.currency_euro = currency_euro;
    }

    public boolean isCurrency_dollar() {
        return currency_dollar;
    }

    public void setCurrency_dollar(boolean currency_dollar) {
        this.currency_dollar = currency_dollar;
    }

    public boolean isKey_hash() {
        return key_hash;
    }

    public void setKey_hash(boolean key_hash) {
        this.key_hash = key_hash;
    }

    public boolean isBrace_open() {
        return brace_open;
    }

    public void setBrace_open(boolean brace_open) {
        this.brace_open = brace_open;
    }

    public boolean isBrace_close() {
        return brace_close;
    }

    public void setBrace_close(boolean brace_close) {
        this.brace_close = brace_close;
    }

    public boolean isAngelBracket_open() {
        return angelBracket_open;
    }

    public void setAngelBracket_open(boolean angelBracket_open) {
        this.angelBracket_open = angelBracket_open;
    }

    public boolean isAngelBracket_close() {
        return angelBracket_close;
    }

    public void setAngelBracket_close(boolean angelBracket_close) {
        this.angelBracket_close = angelBracket_close;
    }

    public boolean isSquareBracket_open() {
        return squareBracket_open;
    }

    public void setSquareBracket_open(boolean squareBracket_open) {
        this.squareBracket_open = squareBracket_open;
    }

    public boolean isSquareBracket_close() {
        return squareBracket_close;
    }

    public void setSquareBracket_close(boolean squareBracket_close) {
        this.squareBracket_close = squareBracket_close;
    }
}
