package de.edgelord.sjgl.sgs;

public class Var<ValueType> {

    public enum Type {Number, Float, Text, Image, Vector2F}

    private ValueType value;
    private Type type;
    private String name;

    public Var(String name, ValueType value, Type type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public ValueType getValue() {
        return value;
    }

    public void setValue(ValueType value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
