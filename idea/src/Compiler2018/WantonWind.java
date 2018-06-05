package Compiler2018;

import Compiler2018.AST.Program;
import Compiler2018.BackEnd.*;
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

import java.io.*;

public class WantonWind {
    private static String readTestFile(String filePath) {
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
            PreRegisterAllocator preRegisterAllocator = new PreRegisterAllocator();
            NaiveRegisterAllocator naiveRegisterAllocator = new NaiveRegisterAllocator();
            irProgram.accept(preRegisterAllocator);
            irProgram.accept(naiveRegisterAllocator);

            // NASM generation
//            NASMTranslater nasmTranslater = new NASMTranslater();
//            irProgram.accept(nasmTranslater);
//            System.out.println(nasmTranslater.getBuilder().toString());
            NasmM2M nasmM2M = new NasmM2M();
            irProgram.accept(nasmM2M);
            System.out.println(nasmM2M.getBuilder().toString());

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
