package Compiler2018.FrontEnd;

import Compiler2018.AST.SourcePosition;
import javafx.util.Pair;

import java.util.LinkedHashMap;
import java.util.Map;

public class CompilationError{
    private Map<Integer, Pair<SourcePosition, String>> errors = new LinkedHashMap<>();

    public void addErrors(SourcePosition pos, String errorInfo){
        errors.put(errors.size(), new Pair<>(pos, errorInfo));
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%d errors found.\n", errors.size()));
        errors.forEach((x, y) -> builder.append(String.format("%dst error: %s\n\t %s\n", x + 1, y.getKey(), y.getValue())));
        return builder.toString();
    }

//    public static void main(String[] args) {
//        CompilationError error = new CompilationError ();
//        error.addErrors (new SourcePosition (0,0), "test0");
//        error.addErrors (new SourcePosition (1,0), "test1");
//        System.out.println (error);
//    }

}
