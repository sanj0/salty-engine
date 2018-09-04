package de.edgelord.saltyengine.sgs;

public class CannotParseVarException extends Exception {

    public CannotParseVarException(String varType, String arg) {
        super("Cannot parse a " + varType + " from " + arg);
    }
}
