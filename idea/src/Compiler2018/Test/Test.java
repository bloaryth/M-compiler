package Compiler2018.Test;

import Compiler2018.AST.Program;
import Compiler2018.FrontEnd.*;
import Compiler2018.Parser.MLexer;
import Compiler2018.Parser.MParser;
import Compiler2018.Symbol.TopTable;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {
    public static String getTxt(String filePath){
        StringBuilder str = new StringBuilder ();
        try{
            InputStreamReader reader = new InputStreamReader(Test.class.getResourceAsStream(filePath));
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp;
            while((strTmp = buffReader.readLine ())!=null){
                str.append (strTmp+'\n');
            }
            buffReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println (str.toString ());
        return str.toString ();
    }

    public static String getFilePath(){
        return "./TestCase/vector.Mx";
//        return "./TestCase/hashMap.Mx";
//        return "./TestCase/stringTest.Mx";
//        return "./TestCase/returnCheck.Mx";
//        return "./TestCase/comment.Mx";
//        return "./TestCase/Wall.Mx";
//        return "./TestCase/returnVal.Mx";
//        return "./TestCase/main.Mx";
//        return "./TestCase/duplicateClass.Mx";
//        return "./TestCase/check.Mx";
//        return "./TestCase/strShift.Mx";
//        return "./TestCase/void.Mx";
//        return "./TestCase/declare.Mx";
//        return "./TestCase/array.Mx";
//        return "./TestCase/a+b.Mx";
//        return "./TestCase/member.Mx";
    }

    public static void main(String[] args) throws Exception {
        try {
//            System.out.println ((ASTBuilder) null);

            String str = getTxt(getFilePath());

            CharStream input = CharStreams.fromString (str);
//            ANTLRInputStream input = new ANTLRInputStream (str);
            MLexer lexer = new MLexer (input);
            CommonTokenStream tokens = new CommonTokenStream (lexer);
            MParser parser = new MParser (tokens);

            ParseTree tree = parser.program ();
            ParseTreeWalker walker = new ParseTreeWalker ();
            ASTBuilder astBuilder = new ASTBuilder ();
            walker.walk (astBuilder, tree);
            Program program = astBuilder.getProgram ();

            ASTPrinter astPrinter = new ASTPrinter ();
            TopTable topTable = new TopTable (null);
            ClassScanner classScanner = new ClassScanner (topTable);
            FuncScanner funcScanner = new FuncScanner (topTable);
            ClassContentScanner classContentScanner = new ClassContentScanner (topTable);
            SemanticChecker semanticChecker = new SemanticChecker (topTable);

            program.accept (astPrinter);
            program.accept (classScanner);
            program.accept (funcScanner);
            program.accept (classContentScanner);
            program.accept (semanticChecker);

//        CompilationError ce = new CompilationError();
//        GlobalSymbolTable sym = new GlobalSymbolTable();
//        StructSymbolScanner structSymbolScanner = new StructSymbolScanner(sym, ce);
//        StructFunctionDeclarator structFunctionDeclarator = new StructFunctionDeclarator(sym, ce);
//        SemanticChecker semanticChecker = new SemanticChecker(sym, ce);

//        program.accept(structSymbolScanner);
//        program.accept(structFunctionDeclarator);
//        program.accept(semanticChecker);
            //program.accept(printer);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit (1);
        }
    }
}
