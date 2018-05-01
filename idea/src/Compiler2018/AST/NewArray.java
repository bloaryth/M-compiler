package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class NewArray extends AbstractNewObject {
    private final List<AbstractExpr> lens;

    public static class Builder{
        private ClassType type;
        private List<AbstractExpr> lens = new LinkedList<> ();

        public void setType (ClassType type) {
            this.type = type;
        }

        public void addLens(AbstractExpr len){
            lens.add(len);
        }

        public NewArray build(){
            return new NewArray (type, lens);
        }
    }

    public NewArray (ClassType type, List<AbstractExpr> lens) {
        super (type);
        this.lens = lens;
    }

    public List<AbstractExpr> getLens () {
        return lens;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
