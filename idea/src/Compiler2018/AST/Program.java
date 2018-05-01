package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class Program extends AbstractASTNode {
    private final List<AbstractDecl> sections;

    public static class Builder{
        private List<AbstractDecl> sections = new LinkedList<> ();

        public void addSection(AbstractDecl section){
            sections.add(section);
        }
        public Program build(){
            return new Program (sections);
        }
    }

    private Program (List<AbstractDecl> sections) {
        this.sections = sections;
    }

    public List<AbstractDecl> getSections () {
        return sections;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
