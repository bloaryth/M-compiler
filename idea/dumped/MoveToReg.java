//package Compiler2018.IR.IRInstruction;
//
//import Compiler2018.IR.IRStructure.BasicBlock;
//import Compiler2018.IR.IRValue.AbstractValue;
//import Compiler2018.IR.IRValue.Register;
//
//// move [a] [b]
//// split into:
//// mov c [b], mov [a] c
//
//public class MoveToReg extends AbstractIRInstruction{
//    private final Register lhs;
//    private final AbstractValue rhs;
//    private final boolean rhsRegInMem;  // rhs instance of Register && false: rhs ; true: [rhs]
//
//    public MoveToReg(BasicBlock basicBlock, Register lhs, AbstractValue rhs, boolean rhsRegInMem) {
//        super(basicBlock);
//        this.lhs = lhs;
//        this.rhs = rhs;
//        this.rhsRegInMem = rhsRegInMem;
//    }
//
//    public Register getLeftOperand() {
//        return lhs;
//    }
//
//    public AbstractValue getRightOperand() {
//        return rhs;
//    }
//
//    public boolean isRhsRegInMem() {
//        return rhsRegInMem;
//    }
//}
