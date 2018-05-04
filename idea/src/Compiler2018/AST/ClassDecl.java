package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class ClassDecl extends AbstractDecl{
    private final String name;
    private final List<AbstractClassItem> items;

    public static class Builder{
        private String name;
        private List<AbstractClassItem> items = new LinkedList<>();

        public void setName(String name){
            this.name = name;
        }

        public void addItem(AbstractClassItem item){
            items.add(item);
        }

        public ClassDecl build(){
            return new ClassDecl(name, items);
        }
    }

    private ClassDecl(String name, List<AbstractClassItem> items){
        this.name = name;
        this.items = items;
    }

    public String getName(){
        return name;
    }

    public List<AbstractClassItem> getItems(){
        return items;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
