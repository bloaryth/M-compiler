package Compiler2018.IR.IRInstruction;

import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.AbstractValue;

import java.util.LinkedList;
import java.util.List;

public class Call extends AbstractIRInstruction {
    private final String name;
    private final List<AbstractValue> args;

    public Call(BasicBlock basicBlock, String name, List<AbstractValue> args) {
        super(basicBlock);
        this.name = name;
        this.args = args;
    }

    public static class Builder {
        private BasicBlock basicBlock;
        private String name;
        private List<AbstractValue> args = new LinkedList<>();

        public void setBasicBlock(BasicBlock basicBlock) {
            this.basicBlock = basicBlock;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addArgs(AbstractValue arg){
            args.add(arg);
        }

        public Call build(){
            return new Call(basicBlock, name, args);
        }
    }

    public String getName() {
        return name;
    }

    public List<AbstractValue> getArgs() {
        return args;
    }
}
