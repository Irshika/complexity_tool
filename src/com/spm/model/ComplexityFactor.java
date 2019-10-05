package com.spm.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

public class ComplexityFactor {
	private String type;
	private int forCStructCount=0;
	private boolean insideContrStruct=false;
	private double complexity;
	private double sTotalComplexity;
	private double sComplexity_Size;
	private double sComplexity_ContrStruct;
	private double sComplexity_NestContrStruct;
	private double sComplexity_Inherit;
	private double complexity_Recursion;
	private double[] statementComplexityArr;
	private String[] summaryComplexity;
	private int inline = 0;
	String[] arry = {"for" , "if" ,"switch" , "do" , "while"};
	int firstNestedCStruct =0;
	 int mostOuter;
	
	
	public ComplexityFactor(String[] SC,String type,String filename) {
		this.setType(type);
		double totalStatSizeComplexity=0;
	
		this.setComplexity(0);
		this.setsTotalComplexity(0);
		this.statementComplexityArr = new double[SC.length];
		this.summaryComplexity = new String[SC.length]; 
		//loop to go through each statement while calculating complexity
		for(int x=0;x<SC.length;x++) {
			totalStatSizeComplexity = totalStatSizeComplexity+ this.calc_sComplexity_Size(SC[x],x);
		}
		
		for(int x=0;x<SC.length;x++) {
			this.calc_sComplexity_ContrStructNest(SC[x],x ,arry);
		}
		

			PrintWriter writer;
		try {
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			String formatted = filename.replace(".", "-");
			String Filename=formatted+"-"+ts+".txt";
			String FilenameMod=Filename.replace(":","-");
			File f = new File("COMPLEXITY-LOG-FILES");
			f.mkdirs();
			writer = new PrintWriter("COMPLEXITY-LOG-FILES/"+FilenameMod, "UTF-8");
			for(int k=0;k<this.summaryComplexity.length;k++) {
				writer.println(this.summaryComplexity[k]);
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(this.summaryComplexity.length>0) {
				System.out.println("Size complexity calculated");
		}
		
	}
	

	public void calc_sComplexity_ContrStructNest(String statement,int line , String[] arry) {
			
		if(statement.contains("(" ) || statement.contains(" (" ) ){
			if(statement.contains(arry[0])) {
				this.CheckControlStructure(statement,"for", line);
			}else if(statement.contains(arry[1])){
				this.CheckControlStructure(statement,"if", line);
			}else if(statement.contains(arry[2])){
				this.CheckControlStructure(statement,"switch", line);
			}else if(statement.contains(arry[3])){
				this.CheckControlStructure(statement,"do", line);
			}else if(statement.contains(arry[4])){
				this.CheckControlStructure(statement,"while", line);
			}
			
		}else if(statement.contains("}" ) ){
			this.CheckControlStructure(statement,"", line);
		}
		
		
	}
	
	
	public boolean CheckControlStructure(String statement,String structType ,int line) {

		
		boolean result = false;
		if(statement.contains(structType+"(") || statement.contains(structType+" (")) {

			System.out.println(structType+" key word is found in "+(line+1));
				if(statement.contains("}") && statement.contains(structType+"(")) {
					
					if(statement.indexOf("}") <  statement.indexOf(structType+"(")) {
						if(this.forCStructCount == 0) {
							
							this.sComplexity_NestContrStruct = this.sComplexity_NestContrStruct+1;

						}
						if(this.forCStructCount!=0) {
							this.sComplexity_NestContrStruct = this.sComplexity_NestContrStruct+(this.forCStructCount+1);
							//this.forCStructCount--;
						}
					}else if((statement.indexOf("}")>statement.indexOf(structType+"("))||(statement.indexOf("}")>statement.indexOf(structType+" ("))) {
						System.out.println("Inline control structure");
						
						inline++;
						if(firstNestedCStruct== 0) {
				
				
						this.sComplexity_NestContrStruct= this.sComplexity_NestContrStruct+1;
						
						}
						else 
							this.sComplexity_NestContrStruct= this.sComplexity_NestContrStruct+(forCStructCount)+2;
				
						result=false;
					}
					
			}else {
				System.out.println("heretwo "+(line+1)+"Count"+this.forCStructCount);
				firstNestedCStruct++; 
				 if(firstNestedCStruct==1) {
					  mostOuter = 1; 
					 System.out.println("complexity of most outer nesting is 1 ");
					 this.sComplexity_NestContrStruct= this.sComplexity_NestContrStruct+mostOuter;
				 }
				
				result=true;

				if(this.insideContrStruct) {				
					this.forCStructCount++;
					this.inline++;
					
					if(this.forCStructCount==1) {

							
							this.sComplexity_NestContrStruct = this.sComplexity_NestContrStruct+2;
							
							System.out.println("complexity of 1st inner nesting is 2");
							System.out.println("Found the 1 nesting level");
							

							System.out.println(this.sComplexity_NestContrStruct);
					}else if(this.forCStructCount>1) {

							this.sComplexity_NestContrStruct = this.sComplexity_NestContrStruct + (forCStructCount+1);

							System.out.println("tot" +sComplexity_NestContrStruct);
						System.out.println(" Found the "+forCStructCount+ " nesting level");
					}
					System.out.println(this.forCStructCount+":<-Structure count Complexity->:"+this.sComplexity_NestContrStruct);
				}

				this.insideContrStruct=true;
				
			}
	
			}else {
			if(statement.contains("}") && this.insideContrStruct){
			
			System.out.println("here three "+(line+1));
			
			System.out.println("kk");

			result=false;
			if(this.forCStructCount == 0) {
				firstNestedCStruct=0;
				this.insideContrStruct=false;
			}
			if(this.forCStructCount!=0) {
				this.forCStructCount--;
			}
			System.out.println("Structure Closed");
			
		}
		}
		System.out.println("total complexity :" + this.sComplexity_NestContrStruct);
		return result;
		

	}
	public double calc_sComplexity_Size(String statement,int line) {
		int Cs = 0;
		String str = "Complexity summary: Statement Size, line: "+line;
		
		if(statement.contains("*") && this.getType()!="C++"){
			str=str+"\n * detected";
			String sub = "*";
			String temp = statement.replace(sub, "");
			int occ = (statement.length() - temp.length()) / sub.length();
			
			if(Cs == 0) {
				Cs = 2*occ;
			}else {
				Cs=Cs+(2*occ);
			}
		}
		if(statement.contains("&")){
			str=str+"\n & detected";
			String sub = "&";
			String temp = statement.replace(sub, "");
			int occ = (statement.length() - temp.length()) / sub.length();
			
			if(Cs == 0) {
				Cs = 2*occ;
			}else {
				Cs=Cs+(2*occ);
			}
		}
		if(statement.contains("new")){
			str=str+"\n 'new' keyword detected";
			String sub = "new";
			String temp = statement.replace(sub, "");
			int occ = (statement.length() - temp.length()) / sub.length();
			
			if(Cs == 0) {
				Cs = 2*occ;
			}else {
				Cs=Cs+(2*occ);
			}
		}
		if(statement.contains("delete")){
			str=str+"\n 'delete' keyword detected";
			String sub = "delete";
			String temp = statement.replace(sub, "");
			int occ = (statement.length() - temp.length()) / sub.length();
			
			if(Cs == 0) {
				Cs = 2*occ;
			}else {
				Cs=Cs+(2*occ);
			}
		}
		if(statement.contains("throw")){
			str=str+"\n 'throw' keyword detected";
			String sub = "throw";
			String temp = statement.replace(sub, "");
			int occ = (statement.length() - temp.length()) / sub.length();
			
			if(Cs == 0) {
				Cs = 2*occ;
			}else {
				Cs=Cs+(2*occ);
			}
		}
		if(statement.contains("+") || statement.contains("-") || statement.contains("*") || statement.contains("/") || statement.contains("++")  || statement.contains("%")|| statement.contains("--")){
			str=str+"\n arithmetic operator(s) detected";
			int occ=0;
			String sub = "+";
			String temp = statement.replace(sub, "");
			occ =occ+((statement.length() - temp.length()) / sub.length());
			 sub = "-";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 sub = "*";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 sub = "/";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 sub = "%";
			 temp = statement.replace(sub, "");
			occ = occ +(statement.length() - temp.length()) / sub.length();
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		
		if(statement.contains("==") || statement.contains("!=") || statement.contains(">") || statement.contains("<") || statement.contains(">=")  || statement.contains("<=")){
			str=str+"\n relation operator(s) detected";
			int occ =0;
			String sub = ">";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "==";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = ">=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "!=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("&&") || statement.contains("||") || statement.contains("!")){
			str=str+"\n logical operator(s) detected";
			int occ =0;
			String sub = "!";
			String temp = statement.replace(sub, "");
			occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "<";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "==";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("==") || statement.contains("!=") || statement.contains(">") || statement.contains("<") || statement.contains(">=")  || statement.contains("<=")){
			str=str+"\n relation operator(s) detected";
			int occ =0;
			String sub = ">";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "==";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = ">=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "!=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("|") || statement.contains("^") || statement.contains("~") || statement.contains("<<") || statement.contains(">>")  || statement.contains("<<<") || statement.contains(">>>")){
			str=str+"\n bitwise operator(s) detected";
			int occ =0;
			String sub = ">>";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<<";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = ">>>";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<<<";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "|";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "^";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "~";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains(",") || statement.contains("::") || statement.contains("->") || statement.contains(".")){
			str=str+"\n misc operator(s) detected";
			int occ =0;
			String sub = ",";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "::";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "->";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = ".";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("+=") || statement.contains("-=") || statement.contains("*=") || statement.contains("/=") || statement.contains("=")  || statement.contains("|=") || statement.contains("&=") || statement.contains("%=")  || statement.contains("<<=") || statement.contains(">>>=")  || statement.contains(">>=") || statement.contains("^=")){
			str=str+"\n assignment operator(s) detected";
			int occ =0;
			String sub = "+=";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "-=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "*=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "/=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "|=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "&=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "%=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = ">>=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "<<=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = ">>>=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "^=";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("void") || statement.contains("int") || statement.contains("double") || statement.contains("float") || statement.contains("String")  || statement.contains("printf") || statement.contains("println") || statement.contains("cout")  || statement.contains("cin") || statement.contains("if")  || statement.contains("for") || statement.contains("while")  || statement.contains("do")  || statement.contains("switch") || statement.contains("case")){
			str=str+"\n key word(s) detected";
			int occ =0;
			String sub = "void";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "int";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "float";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "double";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "String";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "printf";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "println";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "cout";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "cin";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "if";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "for";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "while";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());	 
			 
			 sub = "do";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "switch";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			
			 sub = "case";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("endl") || statement.contains("\n")){
			str=str+"\n manipulator(s) detected";
			int occ =0;
			String sub = "endl";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "\n";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
		
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("\"")){
			str=str+"\n double quotes detected";
			int occ =0;
			String sub = "\"";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
		
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		if(statement.contains("class") || statement.contains("Class") || statement.contains("()") || statement.contains("[]")){
			str=str+"\n other types(class/methods/attributes/arrays) detected";
			int occ =0;
			String sub = "class";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			 sub = "()";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
		
			 sub = "[]";
			 temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		
		if(statement.matches(".*\\d.*")){
			str=str+"\n number(s) detected";
			int occ =0;
			String sub = ".*\\d.*";
			String temp = statement.replace(sub, "");
			 occ =occ+((statement.length() - temp.length()) / sub.length());
			 
			if(Cs == 0) {
				Cs = 1*occ;
			}else {
				Cs=Cs+(1*occ);
			}
		}
		this.summaryComplexity[line]=str;
		return Cs;
	}
	
	public double getComplexity() {
		return complexity;
	}
	public void setComplexity(double complexity) {
		this.complexity = complexity;
	}
	public double getsTotalComplexity() {
		return sTotalComplexity;
	}
	public void setsTotalComplexity(double sTotalComplexity) {
		this.sTotalComplexity = sTotalComplexity;
	}
	public double getsComplexity_Size() {
		return sComplexity_Size;
	}
	public void setsComplexity_Size(double sComplexity_Size) {
		this.sComplexity_Size = sComplexity_Size;
	}
	public double getsComplexity_ContrStruct() {
		return sComplexity_ContrStruct;
	}
	public void setsComplexity_ContrStruct(double sComplexity_ContrStruct) {
		this.sComplexity_ContrStruct = sComplexity_ContrStruct;
	}
	public double getsComplexity_NestContrStruct() {
		return sComplexity_NestContrStruct;
	}
	public void setsComplexity_NestContrStruct(double sComplexity_NestContrStruct) {
		this.sComplexity_NestContrStruct = sComplexity_NestContrStruct;
	}
	public double getsComplexity_Inherit() {
		return sComplexity_Inherit;
	}
	public void setsComplexity_Inherit(double sComplexity_Inherit) {
		this.sComplexity_Inherit = sComplexity_Inherit;
	}
	public double getComplexity_Recursion() {
		return complexity_Recursion;
	}
	public void setComplexity_Recursion(double complexity_Recursion) {
		this.complexity_Recursion = complexity_Recursion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getSummaryComplexity() {
		return summaryComplexity;
	}

	public void setSummaryComplexity(String[] summaryComplexity) {
		this.summaryComplexity = summaryComplexity;
	}

}
