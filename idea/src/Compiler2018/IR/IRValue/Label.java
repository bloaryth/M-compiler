package Compiler2018.IR.IRValue;

public class Label extends AbstractValue {
    private final String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toIRString() {
        return name;
    }
}
