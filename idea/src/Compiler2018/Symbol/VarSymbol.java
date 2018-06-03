package Compiler2018.Symbol;

import Compiler2018.AST.ClassType;
import Compiler2018.AST.VarDecl;
import Compiler2018.IR.IRValue.Register;

public class VarSymbol extends AbstractSymbol {
    private final ClassType type;
    private final String name;
    private final Register register;    // prepare for IR Generation, only useful when VarSymbol is in BlockTable

    public VarSymbol(AbstractSymbolTable belongTable, ClassType type, String name) {
        super(belongTable);
        this.type = type;
        this.name = name;

        if (belongTable instanceof TopTable) {
            register = null;
        } else if (belongTable instanceof BlockTable) {
            register = new Register();
            // built-in function can be redundant
        } else {
            register = null;
        }
    }

    public VarSymbol(AbstractSymbolTable belongTable, VarDecl decl){
        this(belongTable, decl.getType(), decl.getName());
    }

    public ClassType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Register getRegister() {
        return register;
    }

}
