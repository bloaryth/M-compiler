package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRValue.Label;

public class StaticData {
    private final Label label;

    public StaticData(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

}
