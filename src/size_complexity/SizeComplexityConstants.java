/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package size_complexity;

/**
 *
 * @author Perera
 */
public class SizeComplexityConstants {
    
    //path of the source code
//    public static final String FILE_PATH = "resources/newHava.java";
//    public static final String FILE_PATH = "resources/Source.cpp";
    public static final String FILE_PATH = "resources/newClass.java";
    
     private SizeComplexityConstants() {
    }

    public static final String[] arithmeticOperators = {
            "(?<!\\+)\\+(?![+=])",
            "(?<!-)-(?![-=>])",
            "\\*(?!=)",
            "(?<!\\/)\\/(?![=/*])",
            "%(?!=)",
            "\\+\\+",
            "--"
    };

    public static final String[] relationOperators = {
            "==",
            "!=",
            "(?<![->])>(?![>=])",
            "(?<![<])<(?![<=])",
            "(?<!>)>=",
            "(?<!<)<="
    };

    public static final String[] logicalOperators = {
            "&&",
            "\\|\\|",
            "!(?!=)"
    };

    public static final String[] bitwiseOperators = {
            "(?<!\\|)\\|((?![|=]))",
            "\\^(?!=)",
            "~",
            "(?<![<])<<(?![<=])",
            "(?<![>])>>(?![>=])",
            ">>>(?!=)",
            "<<<"
    };

    public static final String[] miscellaneousOperators = {
            "(?<![-+!%^&*<>=:/|~^.]),(?![-+!%^&*<>=:/|~^.])",
            "->",
            "(?<![-+!%^&*<>=:/|~^.])\\.(?![-+!%^&*<>=:/|~^.])",
            "::"
    };

    public static final String[] assignmentOperators = {
            "\\+=",
            "-=",
            "\\*=",
            "\\/=",
            "(?<!>)>>>=",
            "\\|=",
            "&=",
            "%=",
            "(?<!<)<<=",
            "(?<!>)>>=",
            "\\^=",
            "(?<![!=<^%&|/*+>-])=(?!=)"
    };

    public static final String[] keyWords = {
            "\\bvoid\\b",
            "\\bboolean\\b",
            "\\bbool\\b",
            "\\blong\\b",
            "\\bbyte\\b",
            "\\bshort\\b",
            "\\bdouble\\b",
            "\\bint\\b",
            "\\bfloat\\b",
            "\\bstring\\b",
            "\\bString\\b",
            "\\bchar\\b",
            "\\bprint\\b",
            "\\bprintf\\b",
            "\\bSystem\\b",
            "\\bout\\b",
            "\\bprintln\\b",
            "\\bcout\\b",
            "\\bcin\\b",
            "\\bif\\b",
            "\\bfor\\b",
            "(?<!})\\bwhile\\b",
            "\\bdo\\b",
            "\\bswitch\\b",
            "\\bcase\\b",
            "\\bmain\\b"
    };

    public static final String[] manipulators = {
            "\\bendl\\b",
            "\\\\n"
    };

    public static final String[] specialKeyWords = {  /* Cs should be incremented by 2 */
            "\\bnew\\b",
            "\\bdelete\\b",
            "\\bthrow\\b",
            "\\bthrows\\b"
    };

    public static final String numericCharacters = "(\\d.*)";

    public static final String classKeyWord = "\\bclass\\b";

    public static final String referenceOperator = "(?<!&)&(?![=&])";

    public static final String arrayNames = "((\\w*) |(\\w*))\\[(.*)\\]";

    public static final String[] specialOperators = {
            numericCharacters,
            classKeyWord,
            referenceOperator,
            arrayNames
    };
    
    public static final String methodIdentifier = "((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\w\\s\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*";
//            "((\\w*)(?<!((while)|(while )))(?<!((if)|(if )))(?<!((switch)|(switch )))(?<!((for)|(for ))))\\((.*)\\)(?![;).,])";
//    "((\\w*)(?<!((while)|(while )))(?<!((if)|(if )))(?<!((switch)|(switch )))(?<!((for)|(for ))))\\((.*)\\)(?![;).,])";

    public static final String variableIdentifier = "(?<=((\\bboolean\\s\\b)|(\\bbool\\s\\b)|(\\blong\\s\\b)|(\\bbyte\\s\\b)|(\\bshort\\s\\b)|(\\bdouble\\s\\b)|(\\bint\\s\\b)|(\\bfloat\\s\\b)|(\\bstring\\s\\b)|(\\bString\\s\\b)|(\\bchar\\s\\b)))(\\w*)";

    public static final String multiVariableIdentifier = "(?<=((\\bboolean\\s\\b)|(\\bbool\\s\\b)|(\\blong\\s\\b)|(\\bbyte\\s\\b)|(\\bshort\\s\\b)|(\\bdouble\\s\\b)|(\\bint\\s\\b)|(\\bfloat\\s\\b)|(\\bstring\\s\\b)|(\\bString\\s\\b)|(\\bchar\\s\\b)))(\\w*(,(.*)))(?=;)";
}
