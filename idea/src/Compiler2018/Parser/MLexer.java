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
public class MLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Bool=1, Int=2, String=3, Void=4, If=5, Else=6, For=7, While=8, Break=9, 
		Continue=10, Return=11, New=12, Class=13, This=14, Add=15, Sub=16, Mul=17, 
		Div=18, Mod=19, LT=20, GT=21, LE=22, GE=23, EQ=24, NE=25, And=26, Or=27, 
		Not=28, LShift=29, RShift=30, BNot=31, BOr=32, BXor=33, BAnd=34, Assign=35, 
		AddAdd=36, SubSub=37, Semi=38, Comma=39, Dot=40, LParen=41, RParen=42, 
		LBracket=43, RBracket=44, LBrace=45, RBrace=46, Brackets=47, BoolConst=48, 
		NumConst=49, StrConst=50, NullConst=51, Identifier=52, WhiteSpace=53, 
		LineComment=54, BlockComment=55;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"Bool", "Int", "String", "Void", "If", "Else", "For", "While", "Break", 
		"Continue", "Return", "New", "Class", "This", "Add", "Sub", "Mul", "Div", 
		"Mod", "LT", "GT", "LE", "GE", "EQ", "NE", "And", "Or", "Not", "LShift", 
		"RShift", "BNot", "BOr", "BXor", "BAnd", "Assign", "AddAdd", "SubSub", 
		"Semi", "Comma", "Dot", "LParen", "RParen", "LBracket", "RBracket", "LBrace", 
		"RBrace", "Brackets", "BoolConst", "NumConst", "StrConst", "NullConst", 
		"Identifier", "WhiteSpace", "LineComment", "BlockComment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'bool'", "'int'", "'string'", "'void'", "'if'", "'else'", "'for'", 
		"'while'", "'break'", "'continue'", "'return'", "'new'", "'class'", "'this'", 
		"'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'<='", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'~'", "'|'", "'^'", "'&'", 
		"'='", "'++'", "'--'", "';'", "','", "'.'", "'('", "')'", "'['", "']'", 
		"'{'", "'}'", null, null, null, null, "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Bool", "Int", "String", "Void", "If", "Else", "For", "While", "Break", 
		"Continue", "Return", "New", "Class", "This", "Add", "Sub", "Mul", "Div", 
		"Mod", "LT", "GT", "LE", "GE", "EQ", "NE", "And", "Or", "Not", "LShift", 
		"RShift", "BNot", "BOr", "BXor", "BAnd", "Assign", "AddAdd", "SubSub", 
		"Semi", "Comma", "Dot", "LParen", "RParen", "LBracket", "RBracket", "LBrace", 
		"RBrace", "Brackets", "BoolConst", "NumConst", "StrConst", "NullConst", 
		"Identifier", "WhiteSpace", "LineComment", "BlockComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "M.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u0153\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3("+
		"\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\60\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0114\n\61\3\62\6\62\u0117\n\62"+
		"\r\62\16\62\u0118\3\63\3\63\3\63\3\63\7\63\u011f\n\63\f\63\16\63\u0122"+
		"\13\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\7\65\u012d\n\65\f"+
		"\65\16\65\u0130\13\65\3\66\6\66\u0133\n\66\r\66\16\66\u0134\3\66\3\66"+
		"\3\67\3\67\3\67\3\67\7\67\u013d\n\67\f\67\16\67\u0140\13\67\3\67\3\67"+
		"\3\67\3\67\38\38\38\38\78\u014a\n8\f8\168\u014d\138\38\38\38\38\38\5\u0120"+
		"\u013e\u014b\29\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\66k\67m8o9\3\2\7\3\2\62;\b\2$$^^ddppttvv\4\2C\\c|\6\2\62;C\\aac"+
		"|\5\2\13\f\17\17\"\"\2\u015a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
		"\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C"+
		"\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2"+
		"\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2"+
		"\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i"+
		"\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\3q\3\2\2\2\5v\3\2\2\2\7z\3\2"+
		"\2\2\t\u0081\3\2\2\2\13\u0086\3\2\2\2\r\u0089\3\2\2\2\17\u008e\3\2\2\2"+
		"\21\u0092\3\2\2\2\23\u0098\3\2\2\2\25\u009e\3\2\2\2\27\u00a7\3\2\2\2\31"+
		"\u00ae\3\2\2\2\33\u00b2\3\2\2\2\35\u00b8\3\2\2\2\37\u00bd\3\2\2\2!\u00bf"+
		"\3\2\2\2#\u00c1\3\2\2\2%\u00c3\3\2\2\2\'\u00c5\3\2\2\2)\u00c7\3\2\2\2"+
		"+\u00c9\3\2\2\2-\u00cb\3\2\2\2/\u00ce\3\2\2\2\61\u00d1\3\2\2\2\63\u00d4"+
		"\3\2\2\2\65\u00d7\3\2\2\2\67\u00da\3\2\2\29\u00dd\3\2\2\2;\u00df\3\2\2"+
		"\2=\u00e2\3\2\2\2?\u00e5\3\2\2\2A\u00e7\3\2\2\2C\u00e9\3\2\2\2E\u00eb"+
		"\3\2\2\2G\u00ed\3\2\2\2I\u00ef\3\2\2\2K\u00f2\3\2\2\2M\u00f5\3\2\2\2O"+
		"\u00f7\3\2\2\2Q\u00f9\3\2\2\2S\u00fb\3\2\2\2U\u00fd\3\2\2\2W\u00ff\3\2"+
		"\2\2Y\u0101\3\2\2\2[\u0103\3\2\2\2]\u0105\3\2\2\2_\u0107\3\2\2\2a\u0113"+
		"\3\2\2\2c\u0116\3\2\2\2e\u011a\3\2\2\2g\u0125\3\2\2\2i\u012a\3\2\2\2k"+
		"\u0132\3\2\2\2m\u0138\3\2\2\2o\u0145\3\2\2\2qr\7d\2\2rs\7q\2\2st\7q\2"+
		"\2tu\7n\2\2u\4\3\2\2\2vw\7k\2\2wx\7p\2\2xy\7v\2\2y\6\3\2\2\2z{\7u\2\2"+
		"{|\7v\2\2|}\7t\2\2}~\7k\2\2~\177\7p\2\2\177\u0080\7i\2\2\u0080\b\3\2\2"+
		"\2\u0081\u0082\7x\2\2\u0082\u0083\7q\2\2\u0083\u0084\7k\2\2\u0084\u0085"+
		"\7f\2\2\u0085\n\3\2\2\2\u0086\u0087\7k\2\2\u0087\u0088\7h\2\2\u0088\f"+
		"\3\2\2\2\u0089\u008a\7g\2\2\u008a\u008b\7n\2\2\u008b\u008c\7u\2\2\u008c"+
		"\u008d\7g\2\2\u008d\16\3\2\2\2\u008e\u008f\7h\2\2\u008f\u0090\7q\2\2\u0090"+
		"\u0091\7t\2\2\u0091\20\3\2\2\2\u0092\u0093\7y\2\2\u0093\u0094\7j\2\2\u0094"+
		"\u0095\7k\2\2\u0095\u0096\7n\2\2\u0096\u0097\7g\2\2\u0097\22\3\2\2\2\u0098"+
		"\u0099\7d\2\2\u0099\u009a\7t\2\2\u009a\u009b\7g\2\2\u009b\u009c\7c\2\2"+
		"\u009c\u009d\7m\2\2\u009d\24\3\2\2\2\u009e\u009f\7e\2\2\u009f\u00a0\7"+
		"q\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4"+
		"\7p\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7g\2\2\u00a6\26\3\2\2\2\u00a7\u00a8"+
		"\7t\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab\7w\2\2\u00ab"+
		"\u00ac\7t\2\2\u00ac\u00ad\7p\2\2\u00ad\30\3\2\2\2\u00ae\u00af\7p\2\2\u00af"+
		"\u00b0\7g\2\2\u00b0\u00b1\7y\2\2\u00b1\32\3\2\2\2\u00b2\u00b3\7e\2\2\u00b3"+
		"\u00b4\7n\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b7\7u\2\2"+
		"\u00b7\34\3\2\2\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7j\2\2\u00ba\u00bb\7"+
		"k\2\2\u00bb\u00bc\7u\2\2\u00bc\36\3\2\2\2\u00bd\u00be\7-\2\2\u00be \3"+
		"\2\2\2\u00bf\u00c0\7/\2\2\u00c0\"\3\2\2\2\u00c1\u00c2\7,\2\2\u00c2$\3"+
		"\2\2\2\u00c3\u00c4\7\61\2\2\u00c4&\3\2\2\2\u00c5\u00c6\7\'\2\2\u00c6("+
		"\3\2\2\2\u00c7\u00c8\7>\2\2\u00c8*\3\2\2\2\u00c9\u00ca\7@\2\2\u00ca,\3"+
		"\2\2\2\u00cb\u00cc\7>\2\2\u00cc\u00cd\7?\2\2\u00cd.\3\2\2\2\u00ce\u00cf"+
		"\7@\2\2\u00cf\u00d0\7?\2\2\u00d0\60\3\2\2\2\u00d1\u00d2\7?\2\2\u00d2\u00d3"+
		"\7?\2\2\u00d3\62\3\2\2\2\u00d4\u00d5\7#\2\2\u00d5\u00d6\7?\2\2\u00d6\64"+
		"\3\2\2\2\u00d7\u00d8\7(\2\2\u00d8\u00d9\7(\2\2\u00d9\66\3\2\2\2\u00da"+
		"\u00db\7~\2\2\u00db\u00dc\7~\2\2\u00dc8\3\2\2\2\u00dd\u00de\7#\2\2\u00de"+
		":\3\2\2\2\u00df\u00e0\7>\2\2\u00e0\u00e1\7>\2\2\u00e1<\3\2\2\2\u00e2\u00e3"+
		"\7@\2\2\u00e3\u00e4\7@\2\2\u00e4>\3\2\2\2\u00e5\u00e6\7\u0080\2\2\u00e6"+
		"@\3\2\2\2\u00e7\u00e8\7~\2\2\u00e8B\3\2\2\2\u00e9\u00ea\7`\2\2\u00eaD"+
		"\3\2\2\2\u00eb\u00ec\7(\2\2\u00ecF\3\2\2\2\u00ed\u00ee\7?\2\2\u00eeH\3"+
		"\2\2\2\u00ef\u00f0\7-\2\2\u00f0\u00f1\7-\2\2\u00f1J\3\2\2\2\u00f2\u00f3"+
		"\7/\2\2\u00f3\u00f4\7/\2\2\u00f4L\3\2\2\2\u00f5\u00f6\7=\2\2\u00f6N\3"+
		"\2\2\2\u00f7\u00f8\7.\2\2\u00f8P\3\2\2\2\u00f9\u00fa\7\60\2\2\u00faR\3"+
		"\2\2\2\u00fb\u00fc\7*\2\2\u00fcT\3\2\2\2\u00fd\u00fe\7+\2\2\u00feV\3\2"+
		"\2\2\u00ff\u0100\7]\2\2\u0100X\3\2\2\2\u0101\u0102\7_\2\2\u0102Z\3\2\2"+
		"\2\u0103\u0104\7}\2\2\u0104\\\3\2\2\2\u0105\u0106\7\177\2\2\u0106^\3\2"+
		"\2\2\u0107\u0108\7]\2\2\u0108\u0109\7_\2\2\u0109`\3\2\2\2\u010a\u010b"+
		"\7v\2\2\u010b\u010c\7t\2\2\u010c\u010d\7w\2\2\u010d\u0114\7g\2\2\u010e"+
		"\u010f\7h\2\2\u010f\u0110\7c\2\2\u0110\u0111\7n\2\2\u0111\u0112\7u\2\2"+
		"\u0112\u0114\7g\2\2\u0113\u010a\3\2\2\2\u0113\u010e\3\2\2\2\u0114b\3\2"+
		"\2\2\u0115\u0117\t\2\2\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119d\3\2\2\2\u011a\u0120\7$\2\2\u011b"+
		"\u011c\7^\2\2\u011c\u011f\t\3\2\2\u011d\u011f\13\2\2\2\u011e\u011b\3\2"+
		"\2\2\u011e\u011d\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u0121\3\2\2\2\u0120"+
		"\u011e\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\7$"+
		"\2\2\u0124f\3\2\2\2\u0125\u0126\7p\2\2\u0126\u0127\7w\2\2\u0127\u0128"+
		"\7n\2\2\u0128\u0129\7n\2\2\u0129h\3\2\2\2\u012a\u012e\t\4\2\2\u012b\u012d"+
		"\t\5\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e"+
		"\u012f\3\2\2\2\u012fj\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0133\t\6\2\2"+
		"\u0132\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135"+
		"\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\b\66\2\2\u0137l\3\2\2\2\u0138"+
		"\u0139\7\61\2\2\u0139\u013a\7\61\2\2\u013a\u013e\3\2\2\2\u013b\u013d\13"+
		"\2\2\2\u013c\u013b\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013f\3\2\2\2\u013e"+
		"\u013c\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141\u0142\7\f"+
		"\2\2\u0142\u0143\3\2\2\2\u0143\u0144\b\67\2\2\u0144n\3\2\2\2\u0145\u0146"+
		"\7\61\2\2\u0146\u0147\7,\2\2\u0147\u014b\3\2\2\2\u0148\u014a\13\2\2\2"+
		"\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u014c\3\2\2\2\u014b\u0149"+
		"\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7,\2\2\u014f"+
		"\u0150\7\61\2\2\u0150\u0151\3\2\2\2\u0151\u0152\b8\2\2\u0152p\3\2\2\2"+
		"\13\2\u0113\u0118\u011e\u0120\u012e\u0134\u013e\u014b\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}