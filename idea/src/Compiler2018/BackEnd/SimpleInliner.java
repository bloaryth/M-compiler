package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static Compiler2018.IR.IRInstruction.AbstractIRInstruction.rename;

public class SimpleInliner implements IIRVistor {
//
    private IRProgram irProgram;
//
//    public SimpleInliner(IRProgram irProgram) {
//        this.irProgram = irProgram;
//    }

    //    private Map<Register, Register> makeReplaceMap(List<Register> callerRegList, List<Register> calleRegList) {
//        Map<Register, Register> replaceMap = new LinkedHashMap<>();
//        for (int i = 0; i < calleRegList.size(); ++i) {
//            replaceMap.put(calleRegList.get(i), calleRegList.get(i));
//        }
//        return replaceMap;
//    }
//
//    private boolean isSimpleBLock(BasicBlock basicBlock) {
//        BasicBlock.Iter iter = new BasicBlock.Iter(basicBlock);
//        while (iter.hasNext()) {
//            AbstractIRInstruction inst = iter.next();
//            if (inst instanceof Branch) {
//                return false;
//            }
//            if (inst instanceof Jump) {
//                return false;
//            }
//        }
//        return true;
//    }

    private void simpleMove(BasicBlock insertedBlock, BasicBlock newBlock, List<Register> callerRegList, List<Register> calleRegList, Map<Register, Register> renameTable) {
        for (int i = 0; i < calleRegList.size(); ++i) {
            if (calleRegList.get(i) == null | callerRegList.get(i) == null) {
                throw new RuntimeException();
            }
            try {
                newBlock.addTail(new Move(newBlock,  rename(renameTable, calleRegList.get(i)), false, callerRegList.get(i), false));
            } catch (Exception e){
                System.err.println("clone error");
            }
        }
    }

    private void simpleSubstitude(BasicBlock insertedBlock, BasicBlock newBlock, List<Register> callerRegList, List<Register> calleRegList, Map<Register, Register> renameTable) {
        for (int i = 0; i < calleRegList.size(); ++i) {
            renameTable.put(calleRegList.get(i), callerRegList.get(i));
        }
    }

//    private Ret findSimpleRet(BasicBlock calleeBlock) {
//        BasicBlock.Iter iter = new BasicBlock.Iter(calleeBlock);
//        while (iter.hasNext()) {
//            AbstractIRInstruction inst = iter.next();
//            if (inst instanceof Ret) {
//                return ((Ret) inst);
//            }
//        }
//        throw new RuntimeException();
//    }

    @Override
    public void visit(IRProgram irProgram) {
        this.irProgram = irProgram;
        irProgram.getIrFunctionMap().values().forEach(x -> x.accept(this));
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
            AbstractIRInstruction inst = iter.next();
            inst.accept(this);
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
        switch (ir.getProcessedName()) {
            case "print":
            case "println":
            case "getString":
            case "getInt":
            case "toString":
            case "length":
            case "substring":
            case "parseInt":
            case "ord":
            case "address":
            case "_malloc":
            case "_newArray":
            case "newArray":
            case "size":
            case "_strADD":
            case "_strLT":
            case "_strGT":
            case "_strLE":
            case "_strGE":
            case "_strEQ":
            case "_strNE":
                return;
        }
        IRFunction calleeFunc = irProgram.getIRFunction(ir.getProcessedName());
        Map<Register, Register> renameTable = new LinkedHashMap<>();
        if (calleeFunc.getBasicBlockSet().size() != 1) {
            return;
        }

        List<Register> calleeRegList = calleeFunc.getParameterList();
        BasicBlock calleeBlock = calleeFunc.getStartBlock();

        List<Register> callerRegList = ir.getUsedRegisterList();
        AbstractIRInstruction prev = ir.getPrev();
        AbstractIRInstruction next = ir.getNext();

        BasicBlock newBlock = new BasicBlock(null, null);
        BasicBlock insertedBlock = ir.getBasicBlock();

//        simpleMove(insertedBlock, newBlock, callerRegList, calleeRegList, renameTable);
        simpleSubstitude(insertedBlock, newBlock, callerRegList, calleeRegList, renameTable);
        insertedBlock.remove(ir);

        Ret calleeRet = null;
        BasicBlock.Iter iter = new BasicBlock.Iter(calleeBlock);
        while (iter.hasNext()) {
            AbstractIRInstruction inst = iter.next();
            if (inst instanceof Ret) {
                calleeRet = ((Ret) inst);
                break;
            }
            try {
                AbstractIRInstruction newInst = inst.partClone(renameTable);
                newInst.setBasicBlock(newBlock);
                newBlock.addTail(newInst);
            }catch (Exception e){
                System.err.println("clone failed");
                System.err.println(inst.toIRString());
            }
        }
        if (ir.getRet() != null) {
            AbstractIRInstruction move = new Move(newBlock, ir.getRet(), false, rename(renameTable, calleeRet.getRet()), false);
            newBlock.addTail(move);
        }

        if (prev == null) {
            insertedBlock.setHead(newBlock.getHead());
        } else {
            prev.linkNext(newBlock.getHead());
        }
        next.linkPrev(newBlock.getTail());
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
