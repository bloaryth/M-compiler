package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class FuncDecl extends AbstractDecl {
    private final ClassType returnType;
    private final String name;
    private final List<VarDecl> parameters;   // can contain nothing
    private final BlockStmt block;
    private final SourcePosition posType;
    private final SourcePosition posName;

    public static class Builder{
        private ClassType type;
        private String name;
        private List<VarDecl> parameters = new LinkedList<> ();
        private BlockStmt block;
        private SourcePosition posType;
        private SourcePosition posName;

        public void setType (ClassType type) {
            this.type = type;
        }

        public void setName (String name) {
            this.name = name;
        }

        public void setBlock (BlockStmt block) {
            this.block = block;
        }

        public void setPosType (SourcePosition posType) {
            this.posType = posType;
        }

        public void setPosName (SourcePosition posName) {
            this.posName = posName;
        }

        public void addParameter (VarDecl parameter){
            parameters.add(parameter);
        }

        public FuncDecl build(){
            return new FuncDecl (type, name, parameters, block, posType, posName);
        }
    }

    public FuncDecl (ClassType returnType, String name, List<VarDecl> parameters, BlockStmt block, SourcePosition posType, SourcePosition posName) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.block = block;
        this.posType = posType;
        this.posName = posName;
    }

    public ClassType getReturnType () {
        return returnType;
    }

    public String getName () {
        return name;
    }

    public List<VarDecl> getParameters () {
        return parameters;
    }

    public BlockStmt getBlock () {
        return block;
    }

    public SourcePosition getPosType () {
        return posType;
    }

    public SourcePosition getPosName () {
        return posName;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
