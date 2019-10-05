/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package size_complexity;

import com.spm.model.Code;
import com.spm.model.Method;
import complexity_calculation.Files;
import java.util.ArrayList;

/**
 *
 * @author Perera
 */
public class SizeComplexity {

    private int lineComp;
    private String tokens;
    private ArrayList<String> tokens_;

    public void CalC(String line) {

        String extension = Files.getFileExtension();

        //to store the statemnt number
        int lineNo = 0;

        if ("java".equals(extension) || "cpp".equals(extension)) {

            System.out.println("\nReading file...");

            //to store code statement during each newReader iteration
            String statement = line;

            //to store total complexity of statement
            int lineComplexity;

            //used to decide validity of statement
            boolean skipStatement = false;

            //to store Code objects (statement objects)
            ArrayList<Code> lineList = new ArrayList<>();
            ArrayList<Method> recursiveMethodList = new ArrayList<>();
            ArrayList<Method> methodList = new ArrayList<>();
            ArrayList<String> statementsList = new ArrayList();

            Code newCode = new Code();

            SupportFunctions supportFunc = new SupportFunctions();

            //skips empty lines
            if (supportFunc.isLineEmpty(statement)) {
                //continue;
            }

            //check lines for single-line comments
            statement = supportFunc.isSingleLineComment(statement);
            if (supportFunc.isLineEmpty(statement)) {
                //continue;
            }

            //checks for block comments
            statement = supportFunc.isBlockComment(statement);
            if (supportFunc.isLineEmpty(statement)) {
                //continue;
            }

            //checks a block of lines for multi-line comments       
            if (supportFunc.multiLineCommentStart(statement)) {
                skipStatement = true;
                //continue;
            }
            if (supportFunc.multiLineCommentEnd(statement) && skipStatement) {
                skipStatement = false;
                //continue;
            }
            //by this point all lines of comments are discarded

            if (skipStatement) {
                //continue;
            }

            // check statement for strings
            statement = supportFunc.isString(statement);
            skipStatement = true;
            if (supportFunc.isLineEmpty(statement) && skipStatement) {
                //continue;
            }

            //adds line to statements arraylist
            statementsList.add(statement);

            //check statement for method calls
            supportFunc.isMethodCall(statement);

            //check statement for variable declarations
            statement = supportFunc.isVarDeclaration(statement);

            //check statement for multiple variable declarations
            statement = supportFunc.isMultipleVarDeclaration(statement);

            String[] wordsArr = statement.split(" ");

            supportFunc.isOperator(wordsArr);

            newCode.setTotalComplexity(supportFunc.getLineCs());

            //add statement number to newCode object
            newCode.setLineNo(++lineNo);

            //adds new statement to newCode object
            newCode.setStatement(statement);

            //adds list of complexity factors to an ArrayList
            //newCode.setComplexFactor(supportFunc.getComplexityFactor()); //token
            tokens_ = supportFunc.getComplexityFactor();
            
            //adds newCode object to lineList
            lineList.add(newCode);

            SupportFunctions supFuncNew = new SupportFunctions();

            methodList = supFuncNew.getMethods(statementsList);
            recursiveMethodList = supFuncNew.detectRecursion(methodList, statementsList);

            for (Method method1 : recursiveMethodList) {
                System.out.println("Method: " + method1.getMethodName());
                System.out.println("StartLine: " + method1.getFirstLine());
                System.out.println("Lastline: " + method1.getLastLine());
            }

            for (Code code : lineList) {

                //displays line number
                System.out.println("\nstatement no.: " + code.getLineNo());

                //displays statement
                System.out.println("\nstatement: " + code.getStatement());

                System.out.println("\nComplexity factors: ");
                for (String comFactor : code.getComplexFactor()) {
//                                displays complexity factors of each statement
                    System.out.print(comFactor + ", ");
                }

                //displays statement complexity
                //System.out.println("\n\nstatement complexity: " + code.getTotalComplexity()); //line comp
                lineComp = code.getTotalComplexity();
            }

        } else {
            System.out.println("File type not supported. Please insert file of types; .java or .cpp");
        }

    }

    public int getLineComp() {
        return lineComp;
    }

    public void setLineComp(int lineComp) {
        this.lineComp = lineComp;
    }

    public String getTokens() {
        String tempLine = "";
        for(String token_ : tokens_){
            tempLine = tempLine.concat(", "+token_);
        }
        return tempLine;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

}
