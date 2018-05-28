package Compiler2018.IR.IRStructure;

import Compiler2018.IR.IRValue.Label;

public class StaticData {
    public final Label name;
    public final String data;

    public StaticData(Label name, String data) {
        this.name = name;
        this.data = data;
    }
}
