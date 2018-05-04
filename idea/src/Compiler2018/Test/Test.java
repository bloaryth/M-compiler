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

public class Test{
    public static String getTxt(String filePath){
        StringBuilder str = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(Test.class.getResourceAsStream(filePath));
            BufferedReader buffReader = new BufferedReader(reader);
            String strTmp;
            while ((strTmp = buffReader.readLine()) != null) {
                str.append(strTmp + '\n');
            }
            buffReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println (str.toString ());
        return str.toString();
    }

    public static String getFilePath(){
        return "./TestCase/class.Mx";
//        return "./TestCase/newVoidArray.Mx";
//        return "./TestCase/newVoid.Mx";
//        return "./TestCase/Size.Mx";
//        return "./TestCase/returnTest.Mx";
//        return "./TestCase/recoverScope.Mx";
//        return "./TestCase/tbyArrayTest.Mx";
//        return "./TestCase/myTest.Mx";
//        return "./TestCase/sepa.Mx";
//        return "./TestCase/last.Mx";
//        return "./TestCase/xzjTest.Mx";
//        return "./TestCase/zlmNew.Mx";
//        return "./TestCase/testThis2.Mx";
//        return "./TestCase/testThis.Mx";
//        return "./TestCase/ljnCstr.Mx";
//        return "./TestCase/cstr.Mx";
//        return "./TestCase/zzkNaive.Mx";
//        return "./TestCase/if2.Mx";
//        return "./TestCase/duplicateVar.Mx";
//        return "./TestCase/voidMain.Mx";
//        return "./TestCase/empty.Mx";
//        return "./TestCase/if.Mx";
//        return "./TestCase/param.Mx";
//        return "./TestCase/Builg.Mx";
//        return "./TestCase/basicRule.Mx";
//        return "./TestCase/vector.Mx";
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

    public static void main(String[] args) throws Exception{
        try {
            String str = getTxt(getFilePath());

            CharStream input = CharStreams.fromString(str);
            MLexer lexer = new MLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MParser parser = new MParser(tokens);

            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTBuilder astBuilder = new ASTBuilder();
            walker.walk(astBuilder, tree);
            Program program = astBuilder.getProgram();

            ASTPrinter astPrinter = new ASTPrinter();
            TopTable topTable = new TopTable(null);
            ClassScanner classScanner = new ClassScanner(topTable);
            FuncScanner funcScanner = new FuncScanner(topTable);
            ClassVarScanner classVarScanner = new ClassVarScanner(topTable);
            StmtScanner stmtScanner = new StmtScanner(topTable);

            program.accept(astPrinter);
            program.accept(classScanner);
            program.accept(funcScanner);
            program.accept(classVarScanner);
            program.accept(stmtScanner);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
