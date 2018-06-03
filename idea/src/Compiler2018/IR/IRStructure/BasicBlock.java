package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRInstruction.AbstractIRInstruction;
import Compiler2018.IR.IRInstruction.Branch;
import Compiler2018.IR.IRInstruction.Jump;
import Compiler2018.IR.IRInstruction.Ret;

import java.util.LinkedHashSet;
import java.util.Set;

public class BasicBlock {
    // basic Info
    private final IRFunction IRFunction;
    private final String name;

    public BasicBlock(IRFunction IRFunction, String name) {
        this.IRFunction = IRFunction;
        this.name = name;
    }

    public Compiler2018.IR.IRStructure.IRFunction getIRFunction() {
        return IRFunction;
    }

    public String getName() {
        return name;
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
        if (endWithJump) {
//            throw new RuntimeException("end With multiple jump"); // FIXME
//            System.err.println("hhh");
        }
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

    public String toIRString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(":\n");
        if (head != null) {
            AbstractIRInstruction iter = head;
            while (iter != tail) {
                builder.append(iter.toIRString());
                iter = iter.getNext();
            }
            builder.append(iter.toIRString());
            return builder.toString();
        } else {
            return builder.toString() + "Nothing\n";
        }
    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
