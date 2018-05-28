package Compiler2018;

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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WantonWind {
    private static String readTestFile(String filePath) {
        //        StringBuilder str = new StringBuilder ();
        //        try{
        //            InputStreamReader reader = new
        // InputStreamReader(Test.class.getResourceAsStream(filePath));
        //            BufferedReader buffReader = new BufferedReader(reader);
        //            String strTmp;
        //            while((strTmp = buffReader.readLine ()) != null){
        //                str.append (strTmp+'\n');
        //            }
        //            buffReader.close();
        //        }
        //        catch (Exception e){
        //            e.printStackTrace();
        //        }
        //        return str.toString ();

        StringBuilder ans = new StringBuilder();
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                ans.append(tempString).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans.toString();
    }

    public static void run(String prog) {
        try {
            CharStream input = CharStreams.fromString(prog);
            MLexer lexer = new MLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MParser parser = new MParser(tokens);

            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            ASTBuilder astBuilder = new ASTBuilder();
            walker.walk(astBuilder, tree);
            Program program = astBuilder.getProgram();

            ASTPrinter astPrinter = new ASTPrinter();
            TopTable topTable = new TopTable(null, "");
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

    public static void main(String[] args) {
        String program;
        if (args.length == 1) program = readTestFile(args[0]);
        else program = readTestFile("program.txt");
        run(program);
    }
}
