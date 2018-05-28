package Compiler2018.IR.IRStructure;

import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class Function {
    private final IRProgram program;
    private final String name;
    private final List<BasicBlock> basicBlocks = new LinkedList<>();
    private final List<Register> registers = new LinkedList<>();

    public Function(IRProgram program, String name) {
        this.program = program;
        this.name = name;
    }

    public IRProgram getProgram() {
        return program;
    }

    public String getName() {
        return name;
    }

    public void addBasicBlock(BasicBlock basicBlock){
        basicBlocks.add(basicBlock);
    }

    public List<BasicBlock> getBasicBlocks() {
        return basicBlocks;
    }

    public void addRegister(Register register){
        registers.add(register);
    }

    public List<Register> getRegisters() {
        return registers;
    }
}
