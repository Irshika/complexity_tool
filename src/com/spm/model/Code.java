/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spm.model;

import java.util.ArrayList;

/**
 *
 * @author Perera
 */
public class Code {

    private int lineNo;
    
    private String statement;
    
    //to store key words of complexity type 1
    private ArrayList<String> complexFactor;
    
    private int totalComplexity;
    
    public Code(){
        complexFactor = new ArrayList<>();
        totalComplexity = 0;
    }
    
    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }
    
    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setComplexFactor(ArrayList<String> complexFactor){
        this.complexFactor = complexFactor;
    }
    
    public ArrayList<String> getComplexFactor() {
        return complexFactor;
    }

    public void setTotalComplexity(int totalCs){
        totalComplexity = totalCs;
    }
    
    public int getTotalComplexity() {        
        return totalComplexity;
    }    
}
