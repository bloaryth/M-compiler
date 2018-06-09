package Compiler2018.IR.IRStructure;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRValue.Register;

import java.util.*;

public class IRFunction {
    private final String processedName;  // processed processedName
    private final String className; // used to find the IRClass
    private BasicBlock startBlock;
    private BasicBlock endBlock;
    // register used in function
    private final List<Register> parameterList = new LinkedList<>();
    private final Register thisRegister;
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

    public List<Register> getParameterList() {
        return parameterList;
    }

    public void addParameter(Register register) {
        parameterList.add(register);
        addStackOffset(register);
    }

    public Map<Register, Integer> getStackOffsetMap() {
        return stackOffsetMap;
    }

    public Integer getTotalOffset() {
        return totalOffset;
    }

    public Integer getStackOffset(Register reg){
        return stackOffsetMap.get(reg);
    }

    public void addStackOffset(Register reg){
        if (reg == null || stackOffsetMap.get(reg) != null) {
            return;
        }
        totalOffset -= 8;   // all 8 bytes long
        stackOffsetMap.put(reg, totalOffset);
        reg.setStackOffset(totalOffset);
    }

    // for IR Printer
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

    // Dataflow
    private List<BasicBlock> reversePreOrder = null;
    private Set<BasicBlock> visited = new LinkedHashSet<>();

    void dfsPreOrder(BasicBlock basicBlock) {
        if (visited.contains(basicBlock)) {
            return;
        }
        reversePreOrder.add(basicBlock);
        visited.add(basicBlock);
        basicBlock.getSucc().forEach(this::dfsPreOrder);
    }

    public List<BasicBlock> getReversePreOrder() {
        if (reversePreOrder == null) {
            reversePreOrder = new LinkedList<>();
            dfsPreOrder(startBlock);
            Collections.reverse(reversePreOrder);
        }
        return reversePreOrder;
    }

    // callee
    private final Set<Register.PysicalRegister> calleeUsed = new LinkedHashSet<>();

    public Set<Register.PysicalRegister> getCalleeUsed() {
        return calleeUsed;
    }
}
