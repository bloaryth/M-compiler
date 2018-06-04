package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

public class NaiveRegisterAllocator implements IIRVistor{
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
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        if (ir.getIntermediate() != null) {
            ir.getIntermediate().setAllocatedRegister(Register.PysicalRegister.R13);
        }
        ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.R14);
        ir.getRightOperand().setAllocatedRegister(Register.PysicalRegister.R15);
    }

    @Override
    public void visit(Branch ir) {
        ir.getCond().accept(this);
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
    }

    @Override
    public void visit(Compare ir) {
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        if (ir.getIntermediate() != null) {
            ir.getIntermediate().setAllocatedRegister(Register.PysicalRegister.R13);
        }
        ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.R14);
        ir.getRightOperand().setAllocatedRegister(Register.PysicalRegister.R15);
    }

    @Override
    public void visit(Jump ir) {

    }

    @Override
    public void visit(Lea ir) {
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getBase().setAllocatedRegister(Register.PysicalRegister.R13);
        if (ir.getPos() != null) {
            ir.getPos().setAllocatedRegister(Register.PysicalRegister.R14);
        }
    }

    @Override
    public void visit(Move ir) {
        ir.getLhs().setAllocatedRegister(Register.PysicalRegister.R12);
        if (ir.getIntermediate() != null) {
            ir.getIntermediate().setAllocatedRegister(Register.PysicalRegister.R13);
        }
        ir.getRhs().setAllocatedRegister(Register.PysicalRegister.R14);
    }

    @Override
    public void visit(MoveU ir) {
        ir.getLhs().setAllocatedRegister(Register.PysicalRegister.R12);
    }

    @Override
    public void visit(Ret ir) {
        ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
    }

    @Override
    public void visit(SelfInc ir) {
        ir.getDest().setAllocatedRegister(Register.PysicalRegister.R12);
    }

    @Override
    public void visit(UnaryCalc ir) {
        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
        ir.getOperand().setAllocatedRegister(Register.PysicalRegister.R13);
    }
}
