package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRValue.Label;

public class StaticData {
    private final Label label;
    private final Object val;

    public StaticData(Label label, Object val) {
        this.label = label;
        this.val = val;
    }

    public Label getLabel() {
        return label;
    }

    public Object getVal() {
        return val;
    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

}
