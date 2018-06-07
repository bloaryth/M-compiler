package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

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
            posStr = "# ";
        }
        String str = "\tLEA " +
                destination.toIRString() + " " +
                base.toIRString() + " " +
                posStr+
                offset.toString() + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

    @Override
    public Register getDefinedRegister() {
        return destination;
    }

    private List<Register> usedRegisterList = null;

    @Override
    public List<Register> getUsedRegisterList() {
        if (usedRegisterList == null) {
            usedRegisterList = new LinkedList<>();
            usedRegisterList.add(base);
            if (pos != null) {
                usedRegisterList.add(pos);
            }
        }
        return usedRegisterList;
    }
}
