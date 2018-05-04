// Generated from D:/Coding/M-compiler/idea/src/Compiler2018\M.g4 by ANTLR 4.7
package Compiler2018.Parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MLexer extends Lexer{
    static{
        RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            Bool = 1, Int = 2, String = 3, Void = 4, If = 5, Else = 6, For = 7, While = 8, Break = 9,
            Continue = 10, Return = 11, New = 12, Class = 13, Add = 14, Sub = 15, Mul = 16, Div = 17,
            Mod = 18, LT = 19, GT = 20, LE = 21, GE = 22, EQ = 23, NE = 24, And = 25, Or = 26, Not = 27,
            LShift = 28, RShift = 29, BNot = 30, BOr = 31, BXor = 32, BAnd = 33, Assign = 34, AddAdd = 35,
            SubSub = 36, Semi = 37, Comma = 38, Dot = 39, LParen = 40, RParen = 41, LBracket = 42,
            RBracket = 43, LBrace = 44, RBrace = 45, BoolConst = 46, NumConst = 47, StrConst = 48,
            NullConst = 49, Identifier = 50, WhiteSpace = 51, LineComment = 52, BlockComment = 53;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    public static final String[] ruleNames = {
            "Bool", "Int", "String", "Void", "If", "Else", "For", "While", "Break",
            "Continue", "Return", "New", "Class", "Add", "Sub", "Mul", "Div", "Mod",
            "LT", "GT", "LE", "GE", "EQ", "NE", "And", "Or", "Not", "LShift", "RShift",
            "BNot", "BOr", "BXor", "BAnd", "Assign", "AddAdd", "SubSub", "Semi", "Comma",
            "Dot", "LParen", "RParen", "LBracket", "RBracket", "LBrace", "RBrace",
            "BoolConst", "NumConst", "StrConst", "NullConst", "Identifier", "WhiteSpace",
            "LineComment", "BlockComment"
    };

    private static final String[] _LITERAL_NAMES = {
            null, "'bool'", "'int'", "'string'", "'void'", "'if'", "'else'", "'for'",
            "'while'", "'break'", "'continue'", "'return'", "'new'", "'class'", "'+'",
            "'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='",
            "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'~'", "'|'", "'^'", "'&'", "'='",
            "'++'", "'--'", "';'", "','", "'.'", "'('", "')'", "'['", "']'", "'{'",
            "'}'", null, null, null, "'null'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, "Bool", "Int", "String", "Void", "If", "Else", "For", "While", "Break",
            "Continue", "Return", "New", "Class", "Add", "Sub", "Mul", "Div", "Mod",
            "LT", "GT", "LE", "GE", "EQ", "NE", "And", "Or", "Not", "LShift", "RShift",
            "BNot", "BOr", "BXor", "BAnd", "Assign", "AddAdd", "SubSub", "Semi", "Comma",
            "Dot", "LParen", "RParen", "LBracket", "RBracket", "LBrace", "RBrace",
            "BoolConst", "NumConst", "StrConst", "NullConst", "Identifier", "WhiteSpace",
            "LineComment", "BlockComment"
    };
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static{
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for(int i = 0; i < tokenNames.length; i++){
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if(tokenNames[i] == null){
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if(tokenNames[i] == null){
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames(){
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary(){
        return VOCABULARY;
    }


    public MLexer(CharStream input){
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName(){
        return "M.g4";
    }

    @Override
    public String[] getRuleNames(){
        return ruleNames;
    }

    @Override
    public String getSerializedATN(){
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames(){
        return channelNames;
    }

    @Override
    public String[] getModeNames(){
        return modeNames;
    }

    @Override
    public ATN getATN(){
        return _ATN;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\67\u0147\b\1\4\2" +
                    "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4" +
                    "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22" +
                    "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31" +
                    "\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t" +
                    " \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t" +
                    "+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64" +
                    "\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4" +
                    "\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3" +
                    "\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13" +
                    "\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3" +
                    "\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21" +
                    "\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27" +
                    "\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34" +
                    "\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3" +
                    "#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-" +
                    "\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u0108\n/\3\60\6\60\u010b\n\60\r\60" +
                    "\16\60\u010c\3\61\3\61\3\61\3\61\7\61\u0113\n\61\f\61\16\61\u0116\13\61" +
                    "\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\7\63\u0121\n\63\f\63\16" +
                    "\63\u0124\13\63\3\64\6\64\u0127\n\64\r\64\16\64\u0128\3\64\3\64\3\65\3" +
                    "\65\3\65\3\65\7\65\u0131\n\65\f\65\16\65\u0134\13\65\3\65\3\65\3\65\3" +
                    "\65\3\66\3\66\3\66\3\66\7\66\u013e\n\66\f\66\16\66\u0141\13\66\3\66\3" +
                    "\66\3\66\3\66\3\66\5\u0114\u0132\u013f\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17" +
                    "\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+" +
                    "\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+" +
                    "U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67\3\2\7\3\2\62;\b\2$$^^ddppttv" +
                    "v\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\2\u014e\2\3\3\2\2\2\2\5\3" +
                    "\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2" +
                    "\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3" +
                    "\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'" +
                    "\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63" +
                    "\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2" +
                    "?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3" +
                    "\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2" +
                    "\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2" +
                    "e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5r\3\2\2\2\7v\3" +
                    "\2\2\2\t}\3\2\2\2\13\u0082\3\2\2\2\r\u0085\3\2\2\2\17\u008a\3\2\2\2\21" +
                    "\u008e\3\2\2\2\23\u0094\3\2\2\2\25\u009a\3\2\2\2\27\u00a3\3\2\2\2\31\u00aa" +
                    "\3\2\2\2\33\u00ae\3\2\2\2\35\u00b4\3\2\2\2\37\u00b6\3\2\2\2!\u00b8\3\2" +
                    "\2\2#\u00ba\3\2\2\2%\u00bc\3\2\2\2\'\u00be\3\2\2\2)\u00c0\3\2\2\2+\u00c2" +
                    "\3\2\2\2-\u00c5\3\2\2\2/\u00c8\3\2\2\2\61\u00cb\3\2\2\2\63\u00ce\3\2\2" +
                    "\2\65\u00d1\3\2\2\2\67\u00d4\3\2\2\29\u00d6\3\2\2\2;\u00d9\3\2\2\2=\u00dc" +
                    "\3\2\2\2?\u00de\3\2\2\2A\u00e0\3\2\2\2C\u00e2\3\2\2\2E\u00e4\3\2\2\2G" +
                    "\u00e6\3\2\2\2I\u00e9\3\2\2\2K\u00ec\3\2\2\2M\u00ee\3\2\2\2O\u00f0\3\2" +
                    "\2\2Q\u00f2\3\2\2\2S\u00f4\3\2\2\2U\u00f6\3\2\2\2W\u00f8\3\2\2\2Y\u00fa" +
                    "\3\2\2\2[\u00fc\3\2\2\2]\u0107\3\2\2\2_\u010a\3\2\2\2a\u010e\3\2\2\2c" +
                    "\u0119\3\2\2\2e\u011e\3\2\2\2g\u0126\3\2\2\2i\u012c\3\2\2\2k\u0139\3\2" +
                    "\2\2mn\7d\2\2no\7q\2\2op\7q\2\2pq\7n\2\2q\4\3\2\2\2rs\7k\2\2st\7p\2\2" +
                    "tu\7v\2\2u\6\3\2\2\2vw\7u\2\2wx\7v\2\2xy\7t\2\2yz\7k\2\2z{\7p\2\2{|\7" +
                    "i\2\2|\b\3\2\2\2}~\7x\2\2~\177\7q\2\2\177\u0080\7k\2\2\u0080\u0081\7f" +
                    "\2\2\u0081\n\3\2\2\2\u0082\u0083\7k\2\2\u0083\u0084\7h\2\2\u0084\f\3\2" +
                    "\2\2\u0085\u0086\7g\2\2\u0086\u0087\7n\2\2\u0087\u0088\7u\2\2\u0088\u0089" +
                    "\7g\2\2\u0089\16\3\2\2\2\u008a\u008b\7h\2\2\u008b\u008c\7q\2\2\u008c\u008d" +
                    "\7t\2\2\u008d\20\3\2\2\2\u008e\u008f\7y\2\2\u008f\u0090\7j\2\2\u0090\u0091" +
                    "\7k\2\2\u0091\u0092\7n\2\2\u0092\u0093\7g\2\2\u0093\22\3\2\2\2\u0094\u0095" +
                    "\7d\2\2\u0095\u0096\7t\2\2\u0096\u0097\7g\2\2\u0097\u0098\7c\2\2\u0098" +
                    "\u0099\7m\2\2\u0099\24\3\2\2\2\u009a\u009b\7e\2\2\u009b\u009c\7q\2\2\u009c" +
                    "\u009d\7p\2\2\u009d\u009e\7v\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7p\2\2" +
                    "\u00a0\u00a1\7w\2\2\u00a1\u00a2\7g\2\2\u00a2\26\3\2\2\2\u00a3\u00a4\7" +
                    "t\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7w\2\2\u00a7\u00a8" +
                    "\7t\2\2\u00a8\u00a9\7p\2\2\u00a9\30\3\2\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac" +
                    "\7g\2\2\u00ac\u00ad\7y\2\2\u00ad\32\3\2\2\2\u00ae\u00af\7e\2\2\u00af\u00b0" +
                    "\7n\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7u\2\2\u00b3" +
                    "\34\3\2\2\2\u00b4\u00b5\7-\2\2\u00b5\36\3\2\2\2\u00b6\u00b7\7/\2\2\u00b7" +
                    " \3\2\2\2\u00b8\u00b9\7,\2\2\u00b9\"\3\2\2\2\u00ba\u00bb\7\61\2\2\u00bb" +
                    "$\3\2\2\2\u00bc\u00bd\7\'\2\2\u00bd&\3\2\2\2\u00be\u00bf\7>\2\2\u00bf" +
                    "(\3\2\2\2\u00c0\u00c1\7@\2\2\u00c1*\3\2\2\2\u00c2\u00c3\7>\2\2\u00c3\u00c4" +
                    "\7?\2\2\u00c4,\3\2\2\2\u00c5\u00c6\7@\2\2\u00c6\u00c7\7?\2\2\u00c7.\3" +
                    "\2\2\2\u00c8\u00c9\7?\2\2\u00c9\u00ca\7?\2\2\u00ca\60\3\2\2\2\u00cb\u00cc" +
                    "\7#\2\2\u00cc\u00cd\7?\2\2\u00cd\62\3\2\2\2\u00ce\u00cf\7(\2\2\u00cf\u00d0" +
                    "\7(\2\2\u00d0\64\3\2\2\2\u00d1\u00d2\7~\2\2\u00d2\u00d3\7~\2\2\u00d3\66" +
                    "\3\2\2\2\u00d4\u00d5\7#\2\2\u00d58\3\2\2\2\u00d6\u00d7\7>\2\2\u00d7\u00d8" +
                    "\7>\2\2\u00d8:\3\2\2\2\u00d9\u00da\7@\2\2\u00da\u00db\7@\2\2\u00db<\3" +
                    "\2\2\2\u00dc\u00dd\7\u0080\2\2\u00dd>\3\2\2\2\u00de\u00df\7~\2\2\u00df" +
                    "@\3\2\2\2\u00e0\u00e1\7`\2\2\u00e1B\3\2\2\2\u00e2\u00e3\7(\2\2\u00e3D" +
                    "\3\2\2\2\u00e4\u00e5\7?\2\2\u00e5F\3\2\2\2\u00e6\u00e7\7-\2\2\u00e7\u00e8" +
                    "\7-\2\2\u00e8H\3\2\2\2\u00e9\u00ea\7/\2\2\u00ea\u00eb\7/\2\2\u00ebJ\3" +
                    "\2\2\2\u00ec\u00ed\7=\2\2\u00edL\3\2\2\2\u00ee\u00ef\7.\2\2\u00efN\3\2" +
                    "\2\2\u00f0\u00f1\7\60\2\2\u00f1P\3\2\2\2\u00f2\u00f3\7*\2\2\u00f3R\3\2" +
                    "\2\2\u00f4\u00f5\7+\2\2\u00f5T\3\2\2\2\u00f6\u00f7\7]\2\2\u00f7V\3\2\2" +
                    "\2\u00f8\u00f9\7_\2\2\u00f9X\3\2\2\2\u00fa\u00fb\7}\2\2\u00fbZ\3\2\2\2" +
                    "\u00fc\u00fd\7\177\2\2\u00fd\\\3\2\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100" +
                    "\7t\2\2\u0100\u0101\7w\2\2\u0101\u0108\7g\2\2\u0102\u0103\7h\2\2\u0103" +
                    "\u0104\7c\2\2\u0104\u0105\7n\2\2\u0105\u0106\7u\2\2\u0106\u0108\7g\2\2" +
                    "\u0107\u00fe\3\2\2\2\u0107\u0102\3\2\2\2\u0108^\3\2\2\2\u0109\u010b\t" +
                    "\2\2\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c" +
                    "\u010d\3\2\2\2\u010d`\3\2\2\2\u010e\u0114\7$\2\2\u010f\u0110\7^\2\2\u0110" +
                    "\u0113\t\3\2\2\u0111\u0113\13\2\2\2\u0112\u010f\3\2\2\2\u0112\u0111\3" +
                    "\2\2\2\u0113\u0116\3\2\2\2\u0114\u0115\3\2\2\2\u0114\u0112\3\2\2\2\u0115" +
                    "\u0117\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u0118\7$\2\2\u0118b\3\2\2\2\u0119" +
                    "\u011a\7p\2\2\u011a\u011b\7w\2\2\u011b\u011c\7n\2\2\u011c\u011d\7n\2\2" +
                    "\u011dd\3\2\2\2\u011e\u0122\t\4\2\2\u011f\u0121\t\5\2\2\u0120\u011f\3" +
                    "\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123" +
                    "f\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\t\6\2\2\u0126\u0125\3\2\2\2" +
                    "\u0127\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a" +
                    "\3\2\2\2\u012a\u012b\b\64\2\2\u012bh\3\2\2\2\u012c\u012d\7\61\2\2\u012d" +
                    "\u012e\7\61\2\2\u012e\u0132\3\2\2\2\u012f\u0131\13\2\2\2\u0130\u012f\3" +
                    "\2\2\2\u0131\u0134\3\2\2\2\u0132\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133" +
                    "\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0136\7\f\2\2\u0136\u0137\3\2" +
                    "\2\2\u0137\u0138\b\65\2\2\u0138j\3\2\2\2\u0139\u013a\7\61\2\2\u013a\u013b" +
                    "\7,\2\2\u013b\u013f\3\2\2\2\u013c\u013e\13\2\2\2\u013d\u013c\3\2\2\2\u013e" +
                    "\u0141\3\2\2\2\u013f\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0142\3\2" +
                    "\2\2\u0141\u013f\3\2\2\2\u0142\u0143\7,\2\2\u0143\u0144\7\61\2\2\u0144" +
                    "\u0145\3\2\2\2\u0145\u0146\b\66\2\2\u0146l\3\2\2\2\13\2\u0107\u010c\u0112" +
                    "\u0114\u0122\u0128\u0132\u013f\3\2\3\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static{
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for(int i = 0; i < _ATN.getNumberOfDecisions(); i++){
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}