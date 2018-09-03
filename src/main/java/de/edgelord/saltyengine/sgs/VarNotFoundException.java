package de.edgelord.saltyengine.sgs;

public class VarNotFoundException extends Exception {


    public VarNotFoundException(String name) {
        super("Var " + name + " not found in global var pool.");
    }
}
