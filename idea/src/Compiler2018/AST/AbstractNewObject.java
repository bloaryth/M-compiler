package Compiler2018.AST;

import Compiler2018.IR.IRValue.Register;

public abstract class AbstractNewObject extends AbstractASTNode {
    private final ClassType type;

    public AbstractNewObject(ClassType type) {
        this.type = type;
    }

    // prepare for IR Generation
    private Register register = null;

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public ClassType getType() {
        return type;
    }
}
