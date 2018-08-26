/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.output;

import java.io.PrintStream;

public class Output {

    private PrintStream outputStream;

    public Output(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    public void println(Object obj) {

        outputStream.println(obj);
    }

    public void errln(Object obj) {

        outputStream.println(obj);
    }
}
