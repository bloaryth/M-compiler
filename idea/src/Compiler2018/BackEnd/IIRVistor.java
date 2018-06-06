package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;

public interface IIRVistor {

    // IR Structure
    void visit(IRProgram irProgram);
    void visit(IRFunction irFunction);
    void visit(IRClass irClass);
    void visit(StaticData irStaticData);
    void visit(BasicBlock basicBlock);

    // IR Instruction.
    void visit(BinaryCalc ir);
    void visit(Branch ir);
    void visit(Call ir);
    void visit(Compare ir);
    void visit(Jump ir);
    void visit(Lea ir);
    void visit(Move ir);
    void visit(MoveU ir);
    void visit(Ret ir);
    void visit(SelfInc ir);
    void visit(UnaryCalc ir);
    void visit(Set ir);
}
