package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

import java.util.List;

public class PreRegisterAllocator implements IIRVistor{
    @Override
    public void visit(IRProgram irProgram) {
        irProgram.getIrFunctionMap().forEach((x,y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
        List<Register> parameterList = irFunction.getParameterList();
        if (parameterList.size() > 0) {
            parameterList.get(0).setAllocated(true);
            parameterList.get(0).setAllocatedRegister(Register.PysicalRegister.RDI);
        }
        if (parameterList.size() > 1) {
            parameterList.get(1).setAllocated(true);
            parameterList.get(1).setAllocatedRegister(Register.PysicalRegister.RSI);
        }
//        if (parameterList.size() > 2) {
//            parameterList.get(2).setAllocated(true);
//            parameterList.get(2).setAllocatedRegister(Register.PysicalRegister.RDX);
//        }
//        if (parameterList.size() > 3) {
//            parameterList.get(3).setAllocated(true);
//            parameterList.get(3).setAllocatedRegister(Register.PysicalRegister.RCX);
//        } // reserved
        if (parameterList.size() > 4) {
            parameterList.get(4).setAllocated(true);
            parameterList.get(4).setAllocatedRegister(Register.PysicalRegister.R8);
        }
        if (parameterList.size() > 5) {
            parameterList.get(5).setAllocated(true);
            parameterList.get(5).setAllocatedRegister(Register.PysicalRegister.R9);
        }
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
//        switch (ir.getOprator()) {
//            case DIV:
//                ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.RAX);
//                ir.getDestination().setAllocatedRegister(Register.PysicalRegister.RAX);
//                break;
//            case MOD:
//                ir.getLeftOperand().setAllocatedRegister(Register.PysicalRegister.RAX);
//                ir.getDestination().setAllocatedRegister(Register.PysicalRegister.RDX);
//                break;
//            case LSH:
//                ir.getRightOperand().setAllocatedRegister(Register.PysicalRegister.RCX);
//                break;
//            case RSH:
//                ir.getRightOperand().setAllocatedRegister(Register.PysicalRegister.RCX);
//                break;
//        }
    }

    @Override
    public void visit(Branch ir) {

    }

    @Override
    public void visit(Call ir) {
//        if (ir.getArgs().size() > 0) {
//            ir.getArgs().get(0).setAllocated(true);
//            ir.getArgs().get(0).setAllocatedRegister(Register.PysicalRegister.RDI);
//        }
//        if (ir.getArgs().size() > 1) {
//            ir.getArgs().get(1).setAllocated(true);
//            ir.getArgs().get(1).setAllocatedRegister(Register.PysicalRegister.RSI);
//        }
//        if (ir.getArgs().size() > 2) {
//            ir.getArgs().get(2).setAllocatedRegister(Register.PysicalRegister.RDX);
//        }
//        if (ir.getArgs().size() > 3) {
//            ir.getArgs().get(3).setAllocatedRegister(Register.PysicalRegister.RCX);
//        }
//        if (ir.getArgs().size() > 4) {
//            ir.getArgs().get(4).setAllocated(true);
//            ir.getArgs().get(4).setAllocatedRegister(Register.PysicalRegister.R8);
//        }
//        if (ir.getArgs().size() > 5) {
//            ir.getArgs().get(5).setAllocated(true);
//            ir.getArgs().get(5).setAllocatedRegister(Register.PysicalRegister.R9);
//        }
//        if (ir.getRet() != null) {
//            ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
//        }
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
//        if (ir.getRet() != null) {
//            ir.getRet().setAllocatedRegister(Register.PysicalRegister.RAX);
//        }
    }

    @Override
    public void visit(SelfInc ir) {

    }

    @Override
    public void visit(UnaryCalc ir) {

    }

    @Override
    public void visit(CSet ir) {

    }
}
