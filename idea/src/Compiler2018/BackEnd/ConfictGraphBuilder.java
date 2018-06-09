package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConfictGraphBuilder implements IIRVistor {
    Set<Register> registerSet;

    @Override
    public void visit(IRProgram irProgram) {
        irProgram.getIrFunctionMap().forEach((x, y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
        registerSet = new LinkedHashSet<>();
        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
        registerSet = null;
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
            AbstractIRInstruction irInstruction = iter.next();
            irInstruction.buildGraph();
            irInstruction.accept(this);
        }
    }

    void link(Register lhs, Register rhs) {
        lhs.getConflictRegisterSet().add(rhs);
        rhs.getConflictRegisterSet().add(lhs);
    }

    @Override
    public void visit(BinaryCalc ir) {
        link(ir.getDestination(), ir.getLeftOperand());
        link(ir.getDestination(), ir.getRightOperand());
        link(ir.getLeftOperand(), ir.getRightOperand());
//        ir.getDestination().ge
//        registerSet.add(ir.getDestination());
//        registerSet.add(ir.getLeftOperand());
//        registerSet.add(ir.getRightOperand());
//        registerSet.add(ir.getIntermediate());
    }

    @Override
    public void visit(Branch ir) {

    }

    @Override
    public void visit(Call ir) {

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
