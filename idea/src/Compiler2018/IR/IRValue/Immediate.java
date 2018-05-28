package Compiler2018.IR.IRValue;

public class Immediate extends AbstractValue {
    private final Integer val;

    public Immediate(Integer val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
