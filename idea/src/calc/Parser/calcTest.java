package Parser;//package calc;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class calcTest {

    public static void run(String expr) throws Exception{
        CharStream in = CharStreams.fromString (expr);
        calcLexer lexer = new calcLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        System.out.println (tokens);
        calcParser parser = new calcParser(tokens);
        ParseTree tree = parser.calc();

        System.out.println("LISP:");
        System.out.println(tree.toStringTree(parser));
        System.out.println();

//        System.out.println("Visitor:");
//        EvalVisitor evalByVisitor = new EvalVisitor();
//        evalByVisitor.visit(tree);
//        System.out.println();

        System.out.println("Listener:");
        ParseTreeWalker walker = new ParseTreeWalker();
        Evaluator evalByListener = new Evaluator();
        walker.walk(evalByListener, tree);
        System.out.println (evalByListener.vars);
    }

    public static void main(String[] args) throws Exception{

        String[] testStr={
                "a=2\nb=a+1\n",
                "a=a+b+3\nb=a+b+3\n",
                "a=(a-b)+3\n",
                "a=a+(b*3\n"
        };

        for (String s:testStr){
            System.out.println("Input expr:"+s);
            run(s);
        }

    }
}