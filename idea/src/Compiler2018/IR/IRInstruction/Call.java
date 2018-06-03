package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class Call extends AbstractIRInstruction {
    private final String processedName;
    private Register ret; // be set
    private final List<Register> args;

    public Call(BasicBlock basicBlock, String processedName, Register ret, List<Register> args) {
        super(basicBlock);
        this.processedName = processedName;
        this.ret = ret;
        this.args = args;
    }

    public static class Builder {
        private BasicBlock basicBlock;
        private String processedName;
        private Register ret;
        private List<Register> args = new LinkedList<>();

        public void setBasicBlock(BasicBlock basicBlock) {
            this.basicBlock = basicBlock;
        }

        public void setProcessedName(String processedName) {
            this.processedName = processedName;
        }

        public void setRet(Register ret) {
            this.ret = ret;
        }

        public void addArgs(Register arg){
            args.add(arg);
        }

        public Call build(){
            return new Call(basicBlock, processedName, ret, args);
        }
    }

    public String getProcessedName() {
        return processedName;
    }

    public Register getRet() {
        return ret;
    }

    public void setRet(Register ret) {
        this.ret = ret;
    }

    public List<Register> getArgs() {
        return args;
    }

    @Override
    public String toIRString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\tCALL ").append(processedName).append(" ");
        if (ret != null) {
            builder.append(ret.toIRString());
        } else {
            builder.append("void");
        }
        builder.append(" <- ");
        if (args.size() == 0) {
            builder.append("void");
        }
        args.forEach(x -> builder.append(x.toIRString()).append(" "));
        builder.append("\n");
        return builder.toString();
    }

    @Override
    void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
