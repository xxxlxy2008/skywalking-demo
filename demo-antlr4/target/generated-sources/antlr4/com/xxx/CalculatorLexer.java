// Generated from com/xxx/Calculator.g4 by ANTLR 4.7.1
package com.xxx;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, WS=7, FLOAT=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "WS", "FLOAT", "DIGIT", 
		"EXPONET"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'*'", "'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "WS", "FLOAT"
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


	public CalculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calculator.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\nW\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\6\b%\n\b\r\b\16"+
		"\b&\3\b\3\b\3\t\6\t,\n\t\r\t\16\t-\3\t\3\t\7\t\62\n\t\f\t\16\t\65\13\t"+
		"\3\t\5\t8\n\t\3\t\3\t\6\t<\n\t\r\t\16\t=\3\t\5\tA\n\t\3\t\6\tD\n\t\r\t"+
		"\16\tE\3\t\5\tI\n\t\5\tK\n\t\3\n\3\n\3\13\3\13\5\13Q\n\13\3\13\6\13T\n"+
		"\13\r\13\16\13U\2\2\f\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\2\25\2\3\2"+
		"\5\5\2\13\f\17\17\"\"\4\2GGgg\4\2--//\2`\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\3\27\3\2\2\2\5\31\3\2\2\2\7\33\3\2\2\2\t\35\3\2\2\2\13\37\3\2\2\2\r!"+
		"\3\2\2\2\17$\3\2\2\2\21J\3\2\2\2\23L\3\2\2\2\25N\3\2\2\2\27\30\7*\2\2"+
		"\30\4\3\2\2\2\31\32\7+\2\2\32\6\3\2\2\2\33\34\7,\2\2\34\b\3\2\2\2\35\36"+
		"\7\61\2\2\36\n\3\2\2\2\37 \7-\2\2 \f\3\2\2\2!\"\7/\2\2\"\16\3\2\2\2#%"+
		"\t\2\2\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'(\3\2\2\2()\b\b\2\2"+
		")\20\3\2\2\2*,\5\23\n\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2./\3\2"+
		"\2\2/\63\7\60\2\2\60\62\5\23\n\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2"+
		"\2\2\63\64\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\668\5\25\13\2\67\66\3\2"+
		"\2\2\678\3\2\2\28K\3\2\2\29;\7\60\2\2:<\5\23\n\2;:\3\2\2\2<=\3\2\2\2="+
		";\3\2\2\2=>\3\2\2\2>@\3\2\2\2?A\5\25\13\2@?\3\2\2\2@A\3\2\2\2AK\3\2\2"+
		"\2BD\5\23\n\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GI\5\25"+
		"\13\2HG\3\2\2\2HI\3\2\2\2IK\3\2\2\2J+\3\2\2\2J9\3\2\2\2JC\3\2\2\2K\22"+
		"\3\2\2\2LM\4\62;\2M\24\3\2\2\2NP\t\3\2\2OQ\t\4\2\2PO\3\2\2\2PQ\3\2\2\2"+
		"QS\3\2\2\2RT\5\23\n\2SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2V\26\3\2"+
		"\2\2\16\2&-\63\67=@EHJPU\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}