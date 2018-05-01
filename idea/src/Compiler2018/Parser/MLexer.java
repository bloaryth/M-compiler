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
		LBracket=43, RBracket=44, LBrace=45, RBrace=46, BoolConst=47, NumConst=48, 
		StrConst=49, NullConst=50, Identifier=51, WhiteSpace=52, LineComment=53, 
		BlockComment=54;
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
		"RBrace", "BoolConst", "NumConst", "StrConst", "NullConst", "Identifier", 
		"WhiteSpace", "LineComment", "BlockComment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'bool'", "'int'", "'string'", "'void'", "'if'", "'else'", "'for'", 
		"'while'", "'break'", "'continue'", "'return'", "'new'", "'class'", "'this'", 
		"'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'<='", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'~'", "'|'", "'^'", "'&'", 
		"'='", "'++'", "'--'", "';'", "','", "'.'", "'('", "')'", "'['", "']'", 
		"'{'", "'}'", null, null, null, "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Bool", "Int", "String", "Void", "If", "Else", "For", "While", "Break", 
		"Continue", "Return", "New", "Class", "This", "Add", "Sub", "Mul", "Div", 
		"Mod", "LT", "GT", "LE", "GE", "EQ", "NE", "And", "Or", "Not", "LShift", 
		"RShift", "BNot", "BOr", "BXor", "BAnd", "Assign", "AddAdd", "SubSub", 
		"Semi", "Comma", "Dot", "LParen", "RParen", "LBracket", "RBracket", "LBrace", 
		"RBrace", "BoolConst", "NumConst", "StrConst", "NullConst", "Identifier", 
		"WhiteSpace", "LineComment", "BlockComment"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\28\u014e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3)\3"+
		")\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\5\60\u010f\n\60\3\61\6\61\u0112\n\61\r\61\16\61\u0113\3"+
		"\62\3\62\3\62\3\62\7\62\u011a\n\62\f\62\16\62\u011d\13\62\3\62\3\62\3"+
		"\63\3\63\3\63\3\63\3\63\3\64\3\64\7\64\u0128\n\64\f\64\16\64\u012b\13"+
		"\64\3\65\6\65\u012e\n\65\r\65\16\65\u012f\3\65\3\65\3\66\3\66\3\66\3\66"+
		"\7\66\u0138\n\66\f\66\16\66\u013b\13\66\3\66\3\66\3\66\3\66\3\67\3\67"+
		"\3\67\3\67\7\67\u0145\n\67\f\67\16\67\u0148\13\67\3\67\3\67\3\67\3\67"+
		"\3\67\5\u011b\u0139\u0146\28\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61"+
		"a\62c\63e\64g\65i\66k\67m8\3\2\7\3\2\62;\b\2$$^^ddppttvv\4\2C\\c|\6\2"+
		"\62;C\\aac|\5\2\13\f\17\17\"\"\2\u0155\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2"+
		"[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\3o\3\2\2\2\5t\3\2\2\2\7x\3\2\2"+
		"\2\t\177\3\2\2\2\13\u0084\3\2\2\2\r\u0087\3\2\2\2\17\u008c\3\2\2\2\21"+
		"\u0090\3\2\2\2\23\u0096\3\2\2\2\25\u009c\3\2\2\2\27\u00a5\3\2\2\2\31\u00ac"+
		"\3\2\2\2\33\u00b0\3\2\2\2\35\u00b6\3\2\2\2\37\u00bb\3\2\2\2!\u00bd\3\2"+
		"\2\2#\u00bf\3\2\2\2%\u00c1\3\2\2\2\'\u00c3\3\2\2\2)\u00c5\3\2\2\2+\u00c7"+
		"\3\2\2\2-\u00c9\3\2\2\2/\u00cc\3\2\2\2\61\u00cf\3\2\2\2\63\u00d2\3\2\2"+
		"\2\65\u00d5\3\2\2\2\67\u00d8\3\2\2\29\u00db\3\2\2\2;\u00dd\3\2\2\2=\u00e0"+
		"\3\2\2\2?\u00e3\3\2\2\2A\u00e5\3\2\2\2C\u00e7\3\2\2\2E\u00e9\3\2\2\2G"+
		"\u00eb\3\2\2\2I\u00ed\3\2\2\2K\u00f0\3\2\2\2M\u00f3\3\2\2\2O\u00f5\3\2"+
		"\2\2Q\u00f7\3\2\2\2S\u00f9\3\2\2\2U\u00fb\3\2\2\2W\u00fd\3\2\2\2Y\u00ff"+
		"\3\2\2\2[\u0101\3\2\2\2]\u0103\3\2\2\2_\u010e\3\2\2\2a\u0111\3\2\2\2c"+
		"\u0115\3\2\2\2e\u0120\3\2\2\2g\u0125\3\2\2\2i\u012d\3\2\2\2k\u0133\3\2"+
		"\2\2m\u0140\3\2\2\2op\7d\2\2pq\7q\2\2qr\7q\2\2rs\7n\2\2s\4\3\2\2\2tu\7"+
		"k\2\2uv\7p\2\2vw\7v\2\2w\6\3\2\2\2xy\7u\2\2yz\7v\2\2z{\7t\2\2{|\7k\2\2"+
		"|}\7p\2\2}~\7i\2\2~\b\3\2\2\2\177\u0080\7x\2\2\u0080\u0081\7q\2\2\u0081"+
		"\u0082\7k\2\2\u0082\u0083\7f\2\2\u0083\n\3\2\2\2\u0084\u0085\7k\2\2\u0085"+
		"\u0086\7h\2\2\u0086\f\3\2\2\2\u0087\u0088\7g\2\2\u0088\u0089\7n\2\2\u0089"+
		"\u008a\7u\2\2\u008a\u008b\7g\2\2\u008b\16\3\2\2\2\u008c\u008d\7h\2\2\u008d"+
		"\u008e\7q\2\2\u008e\u008f\7t\2\2\u008f\20\3\2\2\2\u0090\u0091\7y\2\2\u0091"+
		"\u0092\7j\2\2\u0092\u0093\7k\2\2\u0093\u0094\7n\2\2\u0094\u0095\7g\2\2"+
		"\u0095\22\3\2\2\2\u0096\u0097\7d\2\2\u0097\u0098\7t\2\2\u0098\u0099\7"+
		"g\2\2\u0099\u009a\7c\2\2\u009a\u009b\7m\2\2\u009b\24\3\2\2\2\u009c\u009d"+
		"\7e\2\2\u009d\u009e\7q\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7v\2\2\u00a0"+
		"\u00a1\7k\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7w\2\2\u00a3\u00a4\7g\2\2"+
		"\u00a4\26\3\2\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7"+
		"v\2\2\u00a8\u00a9\7w\2\2\u00a9\u00aa\7t\2\2\u00aa\u00ab\7p\2\2\u00ab\30"+
		"\3\2\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7y\2\2\u00af"+
		"\32\3\2\2\2\u00b0\u00b1\7e\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7c\2\2\u00b3"+
		"\u00b4\7u\2\2\u00b4\u00b5\7u\2\2\u00b5\34\3\2\2\2\u00b6\u00b7\7v\2\2\u00b7"+
		"\u00b8\7j\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7u\2\2\u00ba\36\3\2\2\2\u00bb"+
		"\u00bc\7-\2\2\u00bc \3\2\2\2\u00bd\u00be\7/\2\2\u00be\"\3\2\2\2\u00bf"+
		"\u00c0\7,\2\2\u00c0$\3\2\2\2\u00c1\u00c2\7\61\2\2\u00c2&\3\2\2\2\u00c3"+
		"\u00c4\7\'\2\2\u00c4(\3\2\2\2\u00c5\u00c6\7>\2\2\u00c6*\3\2\2\2\u00c7"+
		"\u00c8\7@\2\2\u00c8,\3\2\2\2\u00c9\u00ca\7>\2\2\u00ca\u00cb\7?\2\2\u00cb"+
		".\3\2\2\2\u00cc\u00cd\7@\2\2\u00cd\u00ce\7?\2\2\u00ce\60\3\2\2\2\u00cf"+
		"\u00d0\7?\2\2\u00d0\u00d1\7?\2\2\u00d1\62\3\2\2\2\u00d2\u00d3\7#\2\2\u00d3"+
		"\u00d4\7?\2\2\u00d4\64\3\2\2\2\u00d5\u00d6\7(\2\2\u00d6\u00d7\7(\2\2\u00d7"+
		"\66\3\2\2\2\u00d8\u00d9\7~\2\2\u00d9\u00da\7~\2\2\u00da8\3\2\2\2\u00db"+
		"\u00dc\7#\2\2\u00dc:\3\2\2\2\u00dd\u00de\7>\2\2\u00de\u00df\7>\2\2\u00df"+
		"<\3\2\2\2\u00e0\u00e1\7@\2\2\u00e1\u00e2\7@\2\2\u00e2>\3\2\2\2\u00e3\u00e4"+
		"\7\u0080\2\2\u00e4@\3\2\2\2\u00e5\u00e6\7~\2\2\u00e6B\3\2\2\2\u00e7\u00e8"+
		"\7`\2\2\u00e8D\3\2\2\2\u00e9\u00ea\7(\2\2\u00eaF\3\2\2\2\u00eb\u00ec\7"+
		"?\2\2\u00ecH\3\2\2\2\u00ed\u00ee\7-\2\2\u00ee\u00ef\7-\2\2\u00efJ\3\2"+
		"\2\2\u00f0\u00f1\7/\2\2\u00f1\u00f2\7/\2\2\u00f2L\3\2\2\2\u00f3\u00f4"+
		"\7=\2\2\u00f4N\3\2\2\2\u00f5\u00f6\7.\2\2\u00f6P\3\2\2\2\u00f7\u00f8\7"+
		"\60\2\2\u00f8R\3\2\2\2\u00f9\u00fa\7*\2\2\u00faT\3\2\2\2\u00fb\u00fc\7"+
		"+\2\2\u00fcV\3\2\2\2\u00fd\u00fe\7]\2\2\u00feX\3\2\2\2\u00ff\u0100\7_"+
		"\2\2\u0100Z\3\2\2\2\u0101\u0102\7}\2\2\u0102\\\3\2\2\2\u0103\u0104\7\177"+
		"\2\2\u0104^\3\2\2\2\u0105\u0106\7v\2\2\u0106\u0107\7t\2\2\u0107\u0108"+
		"\7w\2\2\u0108\u010f\7g\2\2\u0109\u010a\7h\2\2\u010a\u010b\7c\2\2\u010b"+
		"\u010c\7n\2\2\u010c\u010d\7u\2\2\u010d\u010f\7g\2\2\u010e\u0105\3\2\2"+
		"\2\u010e\u0109\3\2\2\2\u010f`\3\2\2\2\u0110\u0112\t\2\2\2\u0111\u0110"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114"+
		"b\3\2\2\2\u0115\u011b\7$\2\2\u0116\u0117\7^\2\2\u0117\u011a\t\3\2\2\u0118"+
		"\u011a\13\2\2\2\u0119\u0116\3\2\2\2\u0119\u0118\3\2\2\2\u011a\u011d\3"+
		"\2\2\2\u011b\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011e\3\2\2\2\u011d"+
		"\u011b\3\2\2\2\u011e\u011f\7$\2\2\u011fd\3\2\2\2\u0120\u0121\7p\2\2\u0121"+
		"\u0122\7w\2\2\u0122\u0123\7n\2\2\u0123\u0124\7n\2\2\u0124f\3\2\2\2\u0125"+
		"\u0129\t\4\2\2\u0126\u0128\t\5\2\2\u0127\u0126\3\2\2\2\u0128\u012b\3\2"+
		"\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012ah\3\2\2\2\u012b\u0129"+
		"\3\2\2\2\u012c\u012e\t\6\2\2\u012d\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\b\65"+
		"\2\2\u0132j\3\2\2\2\u0133\u0134\7\61\2\2\u0134\u0135\7\61\2\2\u0135\u0139"+
		"\3\2\2\2\u0136\u0138\13\2\2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2"+
		"\u0139\u013a\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139"+
		"\3\2\2\2\u013c\u013d\7\f\2\2\u013d\u013e\3\2\2\2\u013e\u013f\b\66\2\2"+
		"\u013fl\3\2\2\2\u0140\u0141\7\61\2\2\u0141\u0142\7,\2\2\u0142\u0146\3"+
		"\2\2\2\u0143\u0145\13\2\2\2\u0144\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146"+
		"\u0147\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0149\3\2\2\2\u0148\u0146\3\2"+
		"\2\2\u0149\u014a\7,\2\2\u014a\u014b\7\61\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u014d\b\67\2\2\u014dn\3\2\2\2\13\2\u010e\u0113\u0119\u011b\u0129\u012f"+
		"\u0139\u0146\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}