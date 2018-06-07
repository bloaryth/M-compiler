package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;

public class ConfictGraphBuilder implements IIRVistor {
    @Override
    public void visit(IRProgram irProgram) {
        irProgram.getIrFunctionMap().forEach((x, y) -> y.accept(this));
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
            AbstractIRInstruction irInstruction = iter.next();
            irInstruction.buildGraph();
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
