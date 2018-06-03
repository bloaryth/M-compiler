//package Compiler2018.IR.IRInstruction;
//
//import Compiler2018.IR.IRStructure.BasicBlock;
//import Compiler2018.IR.IRValue.Register;
//
//public class MoveToMem extends AbstractIRInstruction{
//    private final Register lhs;
//    private final Register rhs;
//    // assert rhsInMem false
//    // mov [lhs] rhs;
//
//    public MoveToMem(BasicBlock basicBlock, Register lhs, Register rhs) {
//        super(basicBlock);
//        this.lhs = lhs;
//        this.rhs = rhs;
//    }
//
//    public Register getLeftOperand() {
//        return lhs;
//    }
//
//    public Register getRightOperand() {
//        return rhs;
//    }
//}
