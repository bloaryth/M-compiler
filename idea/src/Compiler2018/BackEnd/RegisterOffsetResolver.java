package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;

public class RegisterOffsetResolver implements IIRVistor {
    private IRFunction currentIRFunction;

    @Override
    public void visit(IRProgram irProgram) {
        irProgram.getIrFunctionMap().forEach((x,y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
        currentIRFunction = irFunction;
        irFunction.getBasicBlockSet().forEach(x -> x.accept(this));
        currentIRFunction = null;
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
        currentIRFunction.addStackOffset(ir.getDestination());
        currentIRFunction.addStackOffset(ir.getIntermediate());
        currentIRFunction.addStackOffset(ir.getLeftOperand());
        currentIRFunction.addStackOffset(ir.getRightOperand());
    }

    @Override
    public void visit(Branch ir) {
        ir.getCond().accept(this);
    }

    @Override
    public void visit(Call ir) {
        currentIRFunction.addStackOffset(ir.getRet());
        ir.getArgs().forEach(x -> currentIRFunction.addStackOffset(x));
    }

    @Override
    public void visit(Compare ir) {
//        currentIRFunction.addStackOffset(ir.getDestination());
        currentIRFunction.addStackOffset(ir.getIntermediate());
        currentIRFunction.addStackOffset(ir.getLeftOperand());
        currentIRFunction.addStackOffset(ir.getRightOperand());
    }

    @Override
    public void visit(Jump ir) {

    }

    @Override
    public void visit(Lea ir) {
        currentIRFunction.addStackOffset(ir.getBase());
        currentIRFunction.addStackOffset(ir.getDestination());
        currentIRFunction.addStackOffset(ir.getPos());
    }

    @Override
    public void visit(Move ir) {
        currentIRFunction.addStackOffset(ir.getIntermediate());
        currentIRFunction.addStackOffset(ir.getLhs());
        currentIRFunction.addStackOffset(ir.getRhs());
    }

    @Override
    public void visit(MoveU ir) {
        currentIRFunction.addStackOffset(ir.getLhs());
    }

    @Override
    public void visit(Ret ir) {
        currentIRFunction.addStackOffset(ir.getRet());
    }

    @Override
    public void visit(SelfInc ir) {
        currentIRFunction.addStackOffset(ir.getDest());
    }

    @Override
    public void visit(UnaryCalc ir) {
        currentIRFunction.addStackOffset(ir.getDestination());
        currentIRFunction.addStackOffset(ir.getOperand());
    }
}
