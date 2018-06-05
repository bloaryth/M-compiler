package Compiler2018.IR.IRValue;

public class Register extends AbstractValue {
    private static Integer Id = 0;
    private final Integer vId;

    public Register() {
        this.vId = Id;
        Id += 1;
    }

    public Integer getvId() {
        return vId;
    }

    @Override
    public String toIRString() {
        return "$" + vId.toString();
    }

    public String toIRString(boolean star) {
        if (star) {
            return "[$" + vId.toString() + "]";
        } else {
            return toIRString();
        }
    }

    private Integer stackOffset; // for register in stack

    public Integer getStackOffset() {
        return stackOffset;
    }

    public void setStackOffset(Integer stackOffset) {
        this.stackOffset = stackOffset;
    }

    // allocation
    public enum PysicalRegister {
        RAX, RBX, RCX, RDX, RDI, RSI, RBP, RSP,
        R8, R9, R10, R11, R12, R13, R14, R15;
    }

    private PysicalRegister allocatedRegister = null;  // Pysical Reg Allocated

    public PysicalRegister getAllocatedRegister() {
        return allocatedRegister;
    }

    public void setAllocatedRegister(PysicalRegister allocatedRegister) {
        this.allocatedRegister = allocatedRegister;
    }



}
