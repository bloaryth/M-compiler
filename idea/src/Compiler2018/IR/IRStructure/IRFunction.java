package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRInstruction.BinaryCalc;
import Compiler2018.IR.IRValue.Register;

import java.util.*;

public class IRFunction {
    private final String processedName;  // processed processedName
    private final String className; // used to find the IRClass
    private BasicBlock startBlock;
    private BasicBlock endBlock;
    private final Register thisRegister;
    private final List<Register> registerList = new LinkedList<>(); // contains parameter Register
    // some registers are in VarSymbol, some need to be calc manually.
    private Integer totalOffset = 0;
    private final Map<Register, Integer> stackOffsetMap = new LinkedHashMap<>(); // Register "equals" is not overrided.

    public IRFunction(String processedName, String className) {
        this.processedName = processedName;
        this.className = className;
        startBlock = new BasicBlock(this, processedName + ".entry");

        if (className != null) {
            thisRegister = new Register();
        } else {
            thisRegister = null;
        }
    }

    public String getProcessedName() {
        return processedName;
    }

    public String getClassName() {
        return className;
    }

    public BasicBlock getStartBlock() {
        return startBlock;
    }

    public void setStartBlock(BasicBlock startBlock) {
        this.startBlock = startBlock;
    }

    public BasicBlock getEndBlock() {
        return endBlock;
    }

    public void setEndBlock(BasicBlock endBlock) {
        this.endBlock = endBlock;
    }

    public Register getThisRegister() {
        return thisRegister;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void addRegister(Register register){
        registerList.add(register);
    }

    public Integer getStackOffset(Register reg){
        return stackOffsetMap.get(reg);
    }

    public void addStackOffset(Register reg){
        stackOffsetMap.put(reg, totalOffset);
        totalOffset -= 8;   // all 8 bytes long
    }

    public void addStackOffset(BinaryCalc binaryCalc) {
//        addStackOffset(binaryCalc.getDestination());
//        addStackOffset(binaryCalc, );
        // FIXME
    }

    private Set<BasicBlock> basicBlockSet = new LinkedHashSet<>();

    public Set<BasicBlock> getBasicBlockSet() {
        return basicBlockSet;
    }

    public void putBasicBlock(BasicBlock basicBlock) {
        basicBlockSet.add(basicBlock);
    }

    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

}
