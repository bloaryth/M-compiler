package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

public class PreRegisterAllocator implements IIRVistor{
    @Override
    public void visit(IRProgram irProgram) {
        irProgram.getIrFunctionMap().forEach((x,y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(IRClass irClass) {

    }

    @Override
    public void visit(StaticData irStaticData) {

    }

    @Override
    public void visit(BasicBlock basicBlock) {
        BasicBlock.Iter iter = new BasicBlock.Iter(basicBlock);
        while (iter.hasNext()) {
            iter.next().accept(this);
        }
    }

    @Override
    public void visit(BinaryCalc ir) {

    }

    @Override
    public void visit(Branch ir) {

    }

    @Override
    public void visit(Call ir) {
        if (ir.getArgs().size() > 0) {
            ir.getArgs().get(0).setAllocatedRegister(Register.PysicalRegister.RDI);
        }
        if (ir.getArgs().size() > 1) {
            ir.getArgs().get(1).setAllocatedRegister(Register.PysicalRegister.RSI);
        }
        if (ir.getArgs().size() > 2) {
            ir.getArgs().get(2).setAllocatedRegister(Register.PysicalRegister.RDX);
        }
        if (ir.getArgs().size() > 3) {
            ir.getArgs().get(3).setAllocatedRegister(Register.PysicalRegister.RCX);
        }
        if (ir.getArgs().size() > 4) {
            ir.getArgs().get(4).setAllocatedRegister(Register.PysicalRegister.R8);
        }
        if (ir.getArgs().size() > 5) {
            ir.getArgs().get(5).setAllocatedRegister(Register.PysicalRegister.R9);
        }
        if (ir.getRet() != null) {
            ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
        }
    }

    @Override
    public void visit(Compare ir) {

    }

    @Override
    public void visit(Jump ir) {

    }

    @Override
    public void visit(Lea ir) {

    }

    @Override
    public void visit(Move ir) {

    }

    @Override
    public void visit(MoveU ir) {

    }

    @Override
    public void visit(Ret ir) {
        if (ir.getRet() != null) {
            ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
        }
    }

    @Override
    public void visit(SelfInc ir) {

    }

    @Override
    public void visit(UnaryCalc ir) {

    }
}
