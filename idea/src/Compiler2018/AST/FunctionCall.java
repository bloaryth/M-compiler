package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class FunctionCall extends AbstractExpr {
    private final AbstractExpr name;
    private final List<AbstractExpr> parameters; // can contain nothing
    private final SourcePosition posName;
    private final List<SourcePosition> posParameters; // can contain nothing

    public static class Builder {
        private AbstractExpr name;
        private List<AbstractExpr> parameters = new LinkedList<>();
        private SourcePosition posName;
        private List<SourcePosition> posParameters = new LinkedList<>();

        public void setName(AbstractExpr name) {
            this.name = name;
        }

        public void addParameter(AbstractExpr parameter) {
            parameters.add(parameter);
        }

        public void setPosName(SourcePosition posName) {
            this.posName = posName;
        }

        public void addPosParameter(SourcePosition posParameter) {
            posParameters.add(posParameter);
        }

        public FunctionCall build() {
            return new FunctionCall(name, parameters, posName, posParameters);
        }
    }

    private FunctionCall(
            AbstractExpr name,
            List<AbstractExpr> parameters,
            SourcePosition posName,
            List<SourcePosition> posParameters) {
        this.name = name;
        this.parameters = parameters;
        this.posName = posName;
        this.posParameters = posParameters;
    }

    public AbstractExpr getName() {
        return name;
    }

    public List<AbstractExpr> getParameters() {
        return parameters;
    }

    public SourcePosition getPosName() {
        return posName;
    }

    public List<SourcePosition> getPosParameters() {
        return posParameters;
    }

    // prepare for IR Generation
    private String processedName;

    public String getProcessedName() {
        return processedName;
    }   // FIXME

    public void setProcessedName(String processedName) {
        this.processedName = processedName;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
