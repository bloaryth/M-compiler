package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.List;
import java.util.Set;

public abstract class AbstractIRInstruction {
    private BasicBlock basicBlock;
    private AbstractIRInstruction prev = null;
    private AbstractIRInstruction next = null;

    public AbstractIRInstruction(BasicBlock basicBlock) {
        this.basicBlock = basicBlock;
    }

    public BasicBlock getBasicBlock() {
        return basicBlock;
    }

    public void setBasicBlock(BasicBlock basicBlock) {
        this.basicBlock = basicBlock;
    }

    public void linkNext(AbstractIRInstruction next) {
        this.next = next;
        next.prev = this;
    }

    public void linkPrev(AbstractIRInstruction prev) {
        this.prev = prev;
        prev.next = this;
    }

    public void addNext(AbstractIRInstruction next) {
        if (this.next != null) {
            this.next.linkPrev(next);
        } else {
            basicBlock.setTail(next);
        }
        linkNext(next);
    }

    public void addPrev(AbstractIRInstruction pre) {
        if (this.prev != null) {
            this.prev.linkNext(pre);
        } else {
            basicBlock.setHead(pre);
        }
        linkPrev(pre);
    }

    public AbstractIRInstruction getPrev() {
        return prev;
    }

    public AbstractIRInstruction getNext() {
        return next;
    }

    public void setPrev(AbstractIRInstruction prev) {
        this.prev = prev;
    }

    public void setNext(AbstractIRInstruction next) {
        this.next = next;
    }

    // comment
    private String comment = null;

    public void setComment(String comment) {
        this.comment = comment;
    }

    // printer
    abstract public String toIRString();

    public abstract void accept(IIRVistor vistor);

    // liveness analysis
    private Set<Register> liveInSet = null;
    private Set<Register> liveOutSet = null;

    public Set<Register> getLiveInSet() {
        return liveInSet;
    }

    public void setLiveInSet(Set<Register> liveInSet) {
        this.liveInSet = liveInSet;
    }

    public Set<Register> getLiveOutSet() {
        return liveOutSet;
    }

    public void setLiveOutSet(Set<Register> liveOutSet) {
        this.liveOutSet = liveOutSet;
    }

    // conflict graph
    public abstract Register getDefinedRegister();

    public abstract List<Register> getUsedRegisterList();


    public void buildGraph(){
        Register defined = getDefinedRegister();
        if (defined != null && liveOutSet != null) {
            liveOutSet.forEach(x -> {
                if (x != null && !x.equals(defined)) {
                    x.getConflictRegisterSet().add(defined);
                    defined.getConflictRegisterSet().add(x);
                }
            });
//            liveInSet.forEach(x -> {
//                if (x != null && !x.equals(defined)) {
//                    x.getConflictRegisterSet().add(defined);
//                    defined.getConflictRegisterSet().add(x);
//                }
//            });
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
