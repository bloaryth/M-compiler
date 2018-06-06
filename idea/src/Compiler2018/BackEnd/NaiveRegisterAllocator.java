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
//        if (ir.getOprator() == BinaryCalc.BinaryOp.DIV) {
//            ir.getDestination().setAllocatedRegister(Register.PysicalRegister.RAX);
//            ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.RAX); // FIXME immediate != null
//        } else if (ir.getOprator() == BinaryCalc.BinaryOp.MOD) {
//            ir.getDestination().setAllocatedRegister(Register.PysicalRegister.RAX);
//            ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.RAX);
//        } // FIXME allocator

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

    }

    @Override
    public void visit(Compare ir) {
//        ir.getDestination().setAllocatedRegister(Register.PysicalRegister.R12);
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

    @Override
    public void visit(Set ir) {

    }
}
