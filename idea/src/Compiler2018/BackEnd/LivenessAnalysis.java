package Compiler2018.BackEnd;

import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LivenessAnalysis implements IIRVistor{
    private IRProgram irProgram;

    public LivenessAnalysis(IRProgram irProgram) {
        this.irProgram = irProgram;
    }

    private void initBlock(BasicBlock basicBlock) {
        BasicBlock.Iter iter = new BasicBlock.Iter(basicBlock);
        while (iter.hasNext()) {
            AbstractIRInstruction inst = iter.next();
            if (inst.getLiveInSet() == null) {
                inst.setLiveInSet(new LinkedHashSet<>());
                inst.setLiveOutSet(new LinkedHashSet<>());
            } else {
                inst.getLiveInSet().clear();
                inst.getLiveOutSet().clear();
            }
        }
    }

    @Override
    public void visit(IRProgram irProgram) {
        irProgram.getIrFunctionMap().forEach((x,y) -> y.accept(this));
    }

    @Override
    public void visit(IRFunction irFunction) {
//        List<BasicBlock> basicBlockList = irFunction.getReversePreOrder();
        Set<BasicBlock> basicBlockList = irFunction.getBasicBlockSet();
        basicBlockList.forEach(this::initBlock);

        Set<Register> inSet = new LinkedHashSet<>();
        Set<Register> outSet = new LinkedHashSet<>();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (BasicBlock block : basicBlockList) {
                for (AbstractIRInstruction inst = block.getTail(); inst != null; inst = inst.getPrev()) {
                    inSet.clear();
                    outSet.clear();
                    if (inst instanceof Branch) {
                            outSet.addAll(((Branch) inst).getIfTrue().getHead().getLiveInSet());
                            outSet.addAll(((Branch) inst).getIfFalse().getHead().getLiveInSet());
                    } else if (inst instanceof Jump) {
                            outSet.addAll(((Jump) inst).getJumpBlock().getHead().getLiveInSet());
                    } else if (!(inst instanceof Ret)) {
                            outSet.addAll(inst.getNext().getLiveInSet());
                    }
                    inSet.addAll(outSet);
                    Register definedRegister = inst.getDefinedRegister();
                    if (definedRegister != null) {
                        inSet.remove(definedRegister);
                    }
                    List<Register> usedRegisterList = inst.getUsedRegisterList();
                    if (usedRegisterList != null) {
                        inSet.addAll(usedRegisterList);
                    }
                    if (!inst.getLiveInSet().equals(inSet) || !inst.getLiveOutSet().equals(outSet)) {
                        changed = true;
                        inst.getLiveInSet().clear();
                        inst.getLiveOutSet().clear();
                        inst.getLiveInSet().addAll(inSet);
                        inst.getLiveOutSet().addAll(outSet);
                    }
                }
            }
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
