package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class NewNonArray extends AbstractNewObject {
    private final List<AbstractExpr> parameters;

    public static class Builder{
        private ClassType type;
        private List<AbstractExpr> parameters = new LinkedList<> ();

        public void setType (ClassType baseType) {
            this.type = baseType;
        }

        public void addParameter (AbstractExpr parameter){
            parameters.add(parameter);
        }

        public NewNonArray build(){
            return new NewNonArray (type, parameters);
        }
    }

    public NewNonArray (ClassType type, List<AbstractExpr> parameters) {
        super (type);
        this.parameters = parameters;
    }

    public List<AbstractExpr> getParameters () {
        return parameters;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
