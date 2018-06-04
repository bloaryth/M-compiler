package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;

public abstract class AbstractIRInstruction {
    private final BasicBlock basicBlock;
    private AbstractIRInstruction prev = null;
    private AbstractIRInstruction next = null;

    public AbstractIRInstruction(BasicBlock basicBlock) {
        this.basicBlock = basicBlock;
    }

    private void linkNext(AbstractIRInstruction next) {
        this.next = next;
        next.prev = this;
    }

    private void linkPrev(AbstractIRInstruction prev) {
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

    // comment
    private String comment = null;

    public void setComment(String comment) {
        this.comment = comment;
    }

    // printer
    abstract public String toIRString();

    public abstract void accept(IIRVistor vistor);
}
