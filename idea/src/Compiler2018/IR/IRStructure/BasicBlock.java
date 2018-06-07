package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRInstruction.AbstractIRInstruction;
import Compiler2018.IR.IRInstruction.Branch;
import Compiler2018.IR.IRInstruction.Jump;
import Compiler2018.IR.IRInstruction.Ret;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class BasicBlock {
    private static Integer Id = 0;
    private final Integer vId;

    // basic Info
    private final IRFunction IRFunction;
    private final String name;

    public BasicBlock(IRFunction IRFunction, String name) {
        vId = Id;
        Id += 1;
        this.IRFunction = IRFunction;
        this.name = name;
    }

    public Integer getvId() {
        return vId;
    }

    public Compiler2018.IR.IRStructure.IRFunction getIRFunction() {
        return IRFunction;
    }

    public String getProcessedName() {
        return name + "." + vId.toString();
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


    // data flow analysis
    private Set<BasicBlock> pred = new LinkedHashSet<>();
    private Set<BasicBlock> succ = new LinkedHashSet<>();

    public Set<BasicBlock> getPred() {
        return pred;
    }

    public Set<BasicBlock> getSucc() {
        return succ;
    }

    public void linkSucc(BasicBlock basicBlock) {
        if (basicBlock == null) {
            return;
        }
        succ.add(basicBlock);
        basicBlock.pred.add(this);
    }

    private boolean endWithJump = false;

    public boolean isEndWithJump() {
        return endWithJump;
    }

    public void endWith(AbstractIRInstruction jump) {
        addTail(jump);
        endWithJump = true;

        if(jump instanceof Branch){
            linkSucc(((Branch) jump).getIfTrue());
            linkSucc(((Branch) jump).getIfFalse());
        } else if(jump instanceof Jump){
            linkSucc(((Jump) jump).getJumpBlock());
        } else if(jump instanceof Ret){
//            parent.retInstruction.add((Return) jump);
        } else{
            assert false;
        }
    }

    public static class Iter implements Iterator<AbstractIRInstruction> {
        private AbstractIRInstruction head;
        private AbstractIRInstruction tail;
        private AbstractIRInstruction iter;

        private Iter(AbstractIRInstruction head, AbstractIRInstruction tail) {
            this.head = head;
            this.tail = tail;
            this.iter = head;
        }

        public Iter(BasicBlock basicBlock) {
            this(basicBlock.getHead(), basicBlock.getTail());
        }

        @Override
        public boolean hasNext() {
            return head != null && iter != null;
        }

        @Override
        public AbstractIRInstruction next() {
            AbstractIRInstruction retIter = iter;
            iter = iter.getNext();
            return retIter;
        }
    }


    public String toIRString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getProcessedName()).append(":\n");
        Iter iter = new Iter(this);
        while (iter.hasNext()) {
            builder.append(iter.next().toIRString());
        }
        return builder.toString();
    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
