package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GreedyAllocator implements IIRVistor{
    private IRFunction currentFunction;
    private Set<Register.PysicalRegister> allReg = new LinkedHashSet<>();
    private Set<Register.PysicalRegister> calleeReg = new LinkedHashSet<>();
    private List<Register> parameterList;

    void color(Register register){
        if (parameterList.contains(register)) {
            return;
        }
        if (!register.isTryed() && register.getAllocatedRegister() == null) {
            Set<Register.PysicalRegister> used = new LinkedHashSet<>();
            register.getConflictRegisterSet().forEach(x -> used.add(x.getAllocatedRegister()));
            for (Register.PysicalRegister reg : allReg) {
                if (!used.contains(reg)) {
//                    System.err.println(register.toIRString() + "<-" + reg.toString());
//                    System.err.println("\t");
//                    register.getConflictRegisterSet().forEach(x -> System.err.println(x.toIRString()));
                    if (calleeReg.contains(reg)) {
                        currentFunction.getCalleeUsed().add(reg);
                    }
                    register.setAllocatedRegister(reg);
                    register.setAllocated(true);
                    break;
                }
            }
            register.setTryed(true);
        }
    }

    void init(){
        allReg.add(Register.PysicalRegister.RAX);
        allReg.add(Register.PysicalRegister.RCX);
        allReg.add(Register.PysicalRegister.RDX);
        // caller
        allReg.add(Register.PysicalRegister.RSI);
        allReg.add(Register.PysicalRegister.RDI);
        allReg.add(Register.PysicalRegister.R8);
        allReg.add(Register.PysicalRegister.R9);
        allReg.add(Register.PysicalRegister.R10);
        allReg.add(Register.PysicalRegister.R11);
        // pos
        allReg.add(Register.PysicalRegister.RBP);
        allReg.add(Register.PysicalRegister.RSP);
        // callee
        allReg.add(Register.PysicalRegister.RBX);
        allReg.add(Register.PysicalRegister.R12);
        allReg.add(Register.PysicalRegister.R13);
        allReg.add(Register.PysicalRegister.R14);
        allReg.add(Register.PysicalRegister.R15);

        allReg.remove(Register.PysicalRegister.RAX);
        allReg.remove(Register.PysicalRegister.RCX);
        allReg.remove(Register.PysicalRegister.RDX);
        allReg.remove(Register.PysicalRegister.RBP);
        allReg.remove(Register.PysicalRegister.RSP);

//        allReg.remove(Register.PysicalRegister.R12);
//        allReg.remove(Register.PysicalRegister.R14);
//        allReg.remove(Register.PysicalRegister.R15);
//        allReg.remove(Register.PysicalRegister.R13); // inter

        // test
//        allReg.remove(Register.PysicalRegister.RBX);
//        allReg.remove(Register.PysicalRegister.RDI);
//        allReg.remove(Register.PysicalRegister.RSI);
//        allReg.remove(Register.PysicalRegister.R8);
//        allReg.remove(Register.PysicalRegister.R9);
//        allReg.remove(Register.PysicalRegister.R10);
//        allReg.remove(Register.PysicalRegister.R11);

        calleeReg.add(Register.PysicalRegister.RBX);
        calleeReg.add(Register.PysicalRegister.R12);
        calleeReg.add(Register.PysicalRegister.R13);
        calleeReg.add(Register.PysicalRegister.R14);
        calleeReg.add(Register.PysicalRegister.R15);
    }

    @Override
    public void visit(IRProgram irProgram) {
        init();
        irProgram.getIrFunctionMap().forEach((x, y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
        currentFunction = irFunction;
        parameterList = irFunction.getParameterList();
        irFunction.getStackOffsetMap().forEach((x, y) -> color(x)); // need resolver
        parameterList = null;
        currentFunction = null;
    }

    @Override
    public void visit(IRClass irClass) {

    }

    @Override
    public void visit(StaticData irStaticData) {

    }

    @Override
    public void visit(BasicBlock basicBlock) {

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
