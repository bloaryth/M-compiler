package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class ClassCstrDecl extends AbstractClassItem {
    private final String name;
    private final List<VarDecl> parameters;   // can contain nothing
    private final BlockStmt block;

    public static class Builder{
        private String name;
        private List<VarDecl> parameters = new LinkedList<> ();
        private BlockStmt block;

        public void setName (String name) {
            this.name = name;
        }

        public void setBlock (BlockStmt block) {
            this.block = block;
        }

        public void addParameter (VarDecl parameter){
            parameters.add(parameter);
        }

        public ClassCstrDecl build(){
            return new ClassCstrDecl (name, parameters, block);
        }
    }

    private ClassCstrDecl (String name, List<VarDecl> parameters, BlockStmt block) {
        this.name = name;
        this.parameters = parameters;
        this.block = block;
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

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
