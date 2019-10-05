/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package size_complexity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.spm.model.Method;

/**
 *
 * @author Perera
 */
public class SupportFunctions {
    
    private int lineCs;
    private List<String> variableArr = new ArrayList<>();
    private ArrayList<String> complexityFactor;
    
    int i=1;
    boolean isDataType = false;
    boolean isMethod = false;
    boolean isFoundMethod = false;
    boolean isArrayMethod = false;

    String previousWord = "";
    String methodName = "";
    int scopeBracketCount=0;
    int firstLine = 0;
    int lastLine = 0;
    
    
    public SupportFunctions(){
        this.lineCs = 0;
        complexityFactor = new ArrayList<>();
    }

    public ArrayList<String> getComplexityFactor() {
        return this.complexityFactor;
    }

    public int getLineCs() {
        return this.lineCs;
    }
    
    private boolean matchPattern(String word, String operator) {
        Matcher m = setMatcher(word, operator);
        return m.find();
    }
    
    private Matcher setMatcher(String word, String operator) {
        Pattern p = Pattern.compile(operator);
        return p.matcher(word);
    }
    
    private String getMatchedPattern(String word, String operator) {
        Matcher m = setMatcher(word, operator);

        while (m.find()) {
            this.lineCs++;
            String replaced = word.replaceFirst(operator, "");
            getMatchedPattern(replaced, operator);
            return m.group();
        }
        return null;
    }
    
    private void operatorHas(String word, String operator) {
        if (matchPattern(word, operator)) {
            String token = getMatchedPattern(word, operator);
            complexityFactor.add(token.trim());
            System.out.println("    Token for operatorHas(): " + token.trim());
        }
    }
    
    private void hasSpecialKeyWord(String word, String operator) {
        if (matchPattern(word, operator)) {
            String token = getMatchedPattern(word, operator);
            complexityFactor.add(token.trim());
            System.out.println("    Token for hasSpecialKeyWord(): " + token.trim());
            this.lineCs++;
        }
    }
    
    public boolean isLineEmpty(String statement) {
          if(statement.trim().isEmpty()){
              System.out.println("statement empty");
              return true;
          }
          else
              return false;
        
//        return statement.isEmpty();
    }
    
    public String isSingleLineComment(String statement) {
        String str = "\\/\\/(.*)";
        if (matchPattern(statement, str)) {
            statement = statement.replaceAll(str, "");
        }
        return statement;
    }
    
    public String isBlockComment(String statement) {
        String str = "\\/\\*(.*)\\*\\/";
        if (matchPattern(statement, str)) {
            statement = statement.replaceAll(str, "");
        }
        return statement;
    }
    
    public boolean multiLineCommentStart(String statement) {
        return statement.contains("/*");
    }

    public boolean multiLineCommentEnd(String statement) {
        return statement.contains("*/");
    }
    
    public String isString(String statement) {
        
        String str = "\"(.*)\"";
        if (matchPattern(statement, str)) {
            String textInString = getMatchedPattern(statement, str);
            statement = statement.replaceAll(str, "");
            complexityFactor.add(textInString.trim());
            System.out.print("    String for isString(): " + textInString);
        }
        return statement;
    }
        
    public void isMethodCall(String statement) {
      String methodCall = null;
      if (matchPattern(statement, SizeComplexityConstants.methodIdentifier)) {
          methodCall = getMatchedPattern(statement, SizeComplexityConstants.methodIdentifier);
          complexityFactor.add(methodCall.trim());
          System.out.print("    Method for isMethodCall: " + methodCall);
      }
    }
    
    public String isVarDeclaration(String statement) {
        if ( matchPattern(statement, SizeComplexityConstants.variableIdentifier)) {
            String matchedVariable = getMatchedPattern(statement, SizeComplexityConstants.variableIdentifier);
            if (matchedVariable != null) {
                variableArr.add(matchedVariable);
                complexityFactor.add(matchedVariable.trim());
//                statement = statement.replaceAll(matchedVariable, "");
                System.out.print("    Variable for isVarDeclaration(): " + matchedVariable.trim());
            }
        }
        return statement;
    }
    
    public String isMultipleVarDeclaration(String statement) {
        if (matchPattern(statement, SizeComplexityConstants.multiVariableIdentifier)) {
            String matchedVariables = getMatchedPattern(statement, SizeComplexityConstants.multiVariableIdentifier);
            if (matchedVariables != null) {
                this.lineCs--;
                String[] splitElements = matchedVariables.trim().split(",");
                List<String> splitElementsList = Arrays.asList(splitElements);
                variableArr.addAll(splitElementsList);
                for (String element : splitElementsList) {
                    complexityFactor.add(element.trim());
                    System.out.print("    Variable for isMultipleVarDeclaration(): " + element.trim());
                    this.lineCs++;
                }
//                statement = statement.replaceAll(matchedVariables, "");
            }
        }
        return statement;
    }
    
    public void isOperator(String[] wordsArr) {
        for (String word : wordsArr) {
            for (String operator : SizeComplexityConstants.arithmeticOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.relationOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.logicalOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.bitwiseOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.miscellaneousOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.assignmentOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.keyWords) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.manipulators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.specialOperators) {
                operatorHas(word, operator);
            }
            for (String operator : SizeComplexityConstants.specialKeyWords) {
                hasSpecialKeyWord(word, operator);
            }
            for (String operator : variableArr) {
                operatorHas(word, operator);
            }
        }
    }
    
    //Support functions for Recursive method detection
    
    public ArrayList<Method> getMethods(ArrayList<String> statementsList){
        
        ArrayList<Method> methodList = new ArrayList<>();
        
        for(String statement: statementsList){
            String[] parts = statement.split("(?<!^)\\b");
            
            for (String part : parts) {
	            	
                String[] spaceSplit = part.split(" ");

                for (String spacePart: spaceSplit) {

                    String word = spacePart.replace(" ", "");
                    boolean dataType;

                    if(isArrayMethod) {
                        dataType = false;
                    }else {
                        dataType = this.getDataType(word);
                    }
                    if(dataType) {		            	
                        isDataType = true;	            		
                    }else if(isArrayMethod){
                        
                        if(previousWord.contains(">")) {
                            isArrayMethod = false;
                            isMethod = true;
                        }
                    }else {	            		
                        if(isDataType) {
                            
                            if (word.contains("<")) {
                                    isArrayMethod = true;
                            }else {
                                    isMethod = true;
                            }	            		
                        }else {	            			
                            if(isMethod) {
                                
                                if(word.contains("(")) {
                                    methodName = previousWord;
                                    firstLine = i;
                                    isFoundMethod = true;
                                }
                                isMethod = false;
                            }
                        }            					            			
                        isDataType = false;
                    }

                    if(isFoundMethod) {
                        
                        if(word.contains("{")) {

                                scopeBracketCount++;	            				
                        }
                        
                        if(word.contains("}")){	            				
                            scopeBracketCount--;
                            
                            if(scopeBracketCount==0) {
                                isFoundMethod = false;
                                lastLine = i;
                                
                                Method method = new Method();
                                method.setMethodName(methodName);
                                method.setFirstLine(firstLine);
                                method.setLastLine(lastLine);
                                
                                methodList.add(method);
                            }
                        }
                    }            			
                    previousWord = word;	            		
                }            		           	   
            }	         
            i++;
        }
        
        return methodList;
    }
    
    public boolean getDataType(String word) {
        String dataTypes[] = {"String","void","boolean","double","int","float","long","short","char","ArrayList","List","HashMap"};

        for(String str: dataTypes) {

            if(str.toLowerCase().equals(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Method> detectRecursion(ArrayList<Method> methods, ArrayList<String> lines){
        
        ArrayList<Method> recMethodList = new ArrayList<>();
        
        boolean recursiveStatus = false;
        
        for(Method method: methods){
            
            for(int i=method.getFirstLine();i<method.getLastLine();i++) {
                String[] splitLine = lines.get(i).split("(?<!^)\\b");
                
                for (String splitPart : splitLine){
                    String[] splitSpace = splitPart.split(" ");
                    
                    for (String atomicSplit: splitSpace){
                        String word = atomicSplit.replace(" ", "");
                        
                        if(recursiveStatus){
                            
                            if(word.contains("(")) {			
                                recMethodList.add(method);
                            }
                            
                            recursiveStatus = false;
                        }
                        
                        if(word.contains(method.getMethodName())){
                            
                            if(word.contains("(")){
                                recMethodList.add(method);
                            }
                            
                            else
                                recursiveStatus = true;
                        }
                    }
                }
            }
        }
        
        return recMethodList;
    }
    
}
