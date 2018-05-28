package Compiler2018.IR.IRStructure;

import Compiler2018.IR.IRInstruction.AbstractIRInstruction;

import java.util.LinkedHashSet;
import java.util.Set;

public class BasicBlock {
    private final Function function;
    private final String name;

    public BasicBlock(Function function, String name) {
        this.function = function;
        this.name = name;
    }

    // linked List IR
    private AbstractIRInstruction head = null;
    private AbstractIRInstruction tail = null;

    public void addHead(AbstractIRInstruction head){
        if (this.head == null){
            this.head = head;
            this.tail = head;
        } else {
            this.head.addPrev(head);
        }
    }

    public void addTail(AbstractIRInstruction tail){
        if (this.tail == null) {
            this.head = tail;
            this.tail = tail;
        } else {
            this.tail.addNext(tail);
        }
    }

    public AbstractIRInstruction getHead() {
        return head;
    }

    public AbstractIRInstruction getTail() {
        return tail;
    }

    public void setHead(AbstractIRInstruction head) {
        this.head = head;
    }

    public void setTail(AbstractIRInstruction tail) {
        this.tail = tail;
    }

    private Set<BasicBlock> pred = new LinkedHashSet<>();
    private Set<BasicBlock> succ = new LinkedHashSet<>();

    public Set<BasicBlock> getPred() {
        return pred;
    }

    public Set<BasicBlock> getSucc() {
        return succ;
    }

    public void addPred(BasicBlock basicBlock){
        pred.add(basicBlock);
    }

    public void addSucc(BasicBlock basicBlock){
        succ.add(basicBlock);
    }
}
