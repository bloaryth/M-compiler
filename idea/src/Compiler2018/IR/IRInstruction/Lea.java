package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

// load effective address
public class Lea extends AbstractIRInstruction {
    private final Register destination;
    private final Register base;
    private final Register pos;
    private final Integer offset;

    public Lea(BasicBlock basicBlock, Register destination, Register base, Register pos, Integer offset) {
        super(basicBlock);
        this.destination = destination;
        this.base = base;
        this.pos = pos;
        this.offset = offset;
    }

    public Register getDestination() {
        return destination;
    }

    public Register getBase() {
        return base;
    }

    public Register getPos() {
        return pos;
    }

    public Integer getOffset() {
        return offset;
    }

    @Override
    public String toIRString() {
        String posStr;
        if (pos != null) {
            posStr = pos.toIRString() + " ";
        } else {
            posStr = "";
        }
        String str = "\tLEA " +
                base.toIRString() + " " +
                posStr+
                offset.toString() + "\n";
        return str;
    }

    @Override
    void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
