package Compiler2018.Test;

import Compiler2018.AST.Program;
import Compiler2018.BackEnd.IRPrinter;
import Compiler2018.BackEnd.NASMTranslater;
import Compiler2018.BackEnd.NaiveRegisterAllocator;
import Compiler2018.BackEnd.RegisterOffsetResolver;
import Compiler2018.FrontEnd.*;
import Compiler2018.FrontEnd.IRBuilder.IRClassBuilder;
import Compiler2018.FrontEnd.IRBuilder.IRFuncParamBuilder;
import Compiler2018.FrontEnd.IRBuilder.IRInstructionBuilder;
import Compiler2018.FrontEnd.Semantic.ClassScanner;
import Compiler2018.FrontEnd.Semantic.ClassVarScanner;
import Compiler2018.FrontEnd.Semantic.FuncScanner;
import Compiler2018.FrontEnd.Semantic.StmtScanner;
import Compiler2018.IR.IRStructure.IRProgram;
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
    public static String getTxt(String filePath) {
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

    public static String getFilePath() {
        // IR Generation
//        return "./CodeGenTest/ClassTest.Mx";
//        return "./CodeGenTest/FunctionTest.Mx";
//        return "./CodeGenTest/Random.Mx";
        return "./CodeGenTest/ZZK.Mx";

        // Semantic

//        return "./SemanticTest/notThatBad.Mx";
        //        return "./SemanticTest/InFunc.Mx";
        //        return "./SemanticTest/class.Mx";
        //        return "./SemanticTest/newVoidArray.Mx";
        //        return "./SemanticTest/newVoid.Mx";
        //        return "./SemanticTest/Size.Mx";
        //        return "./SemanticTest/returnTest.Mx";
        //        return "./SemanticTest/recoverScope.Mx";
        //        return "./SemanticTest/tbyArrayTest.Mx";
        //        return "./SemanticTest/myTest.Mx";
        //        return "./SemanticTest/sepa.Mx";
        //        return "./SemanticTest/last.Mx";
        //        return "./SemanticTest/xzjTest.Mx";
        //        return "./SemanticTest/zlmNew.Mx";
        //        return "./SemanticTest/testThis2.Mx";
        //        return "./SemanticTest/testThis.Mx";
        //        return "./SemanticTest/ljnCstr.Mx";
        //        return "./SemanticTest/cstr.Mx";
        //        return "./SemanticTest/zzkNaive.Mx";
        //        return "./SemanticTest/if2.Mx";
        //        return "./SemanticTest/duplicateVar.Mx";
        //        return "./SemanticTest/voidMain.Mx";
        //        return "./SemanticTest/empty.Mx";
        //        return "./SemanticTest/if.Mx";
        //        return "./SemanticTest/param.Mx";
        //        return "./SemanticTest/Builg.Mx";
        //        return "./SemanticTest/basicRule.Mx";
        //        return "./SemanticTest/vector.Mx";
        //        return "./SemanticTest/hashMap.Mx";
        //        return "./SemanticTest/stringTest.Mx";
        //        return "./SemanticTest/returnCheck.Mx";
        //        return "./SemanticTest/comment.Mx";
        //        return "./SemanticTest/Wall.Mx";
        //        return "./SemanticTest/returnVal.Mx";
        //        return "./SemanticTest/main.Mx";
        //        return "./SemanticTest/duplicateClass.Mx";
        //        return "./SemanticTest/check.Mx";
        //        return "./SemanticTest/strShift.Mx";
        //        return "./SemanticTest/void.Mx";
        //        return "./SemanticTest/declare.Mx";
        //        return "./SemanticTest/array.Mx";
        //        return "./SemanticTest/a+b.Mx";
        //        return "./SemanticTest/member.Mx";
    }

    public static void main(String[] args) throws Exception {
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

            // Semantic
            ASTPrinter astPrinter = new ASTPrinter();
            TopTable topTable = new TopTable(null, "");
            ClassScanner classScanner = new ClassScanner(topTable);
            FuncScanner funcScanner = new FuncScanner(topTable);
            ClassVarScanner classVarScanner = new ClassVarScanner(topTable);
            StmtScanner stmtScanner = new StmtScanner(topTable);

//            program.accept(astPrinter);
            program.accept(classScanner);
            program.accept(funcScanner);
            program.accept(classVarScanner);
            program.accept(stmtScanner);

            // IR Generation
            IRProgram irProgram = new IRProgram();
            IRClassBuilder irClassBuilder = new IRClassBuilder(irProgram);
            IRFuncParamBuilder irFuncParamBuilder = new IRFuncParamBuilder(irProgram);
            IRInstructionBuilder irInstructionBuilder = new IRInstructionBuilder(irProgram);

            program.accept(irClassBuilder);
            program.accept(irFuncParamBuilder);
            program.accept(irInstructionBuilder);

            // Code Generation
            IRPrinter irPrinter = new IRPrinter();
            RegisterOffsetResolver registerOffsetResolver = new RegisterOffsetResolver();
//            irProgram.accept(irPrinter);
            irProgram.accept(registerOffsetResolver);

            // Register Allocation
            NaiveRegisterAllocator naiveRegisterAllocator = new NaiveRegisterAllocator();
            irProgram.accept(naiveRegisterAllocator);

            // NASM generation
            NASMTranslater nasmTranslater = new NASMTranslater();
            irProgram.accept(nasmTranslater);
            System.out.println(nasmTranslater.getBuilder().toString());
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}
