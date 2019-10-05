package com.spm.model;

public abstract class SourceCode {
	protected String name;
	protected int linesOfCode;
	protected String remarks;
	protected ComplexityFactor complexity;
	protected String[] SCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLinesOfCode() {
		return linesOfCode;
	}
	public void setLinesOfCode(int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public ComplexityFactor getComplexity() {
		return complexity;
	}
	public void setComplexity(ComplexityFactor complexity) {
		this.complexity = complexity;
	}
	public String[] getSCode() {
		return SCode;
	}
	public void setSCode(String[] sCode) {
		SCode = sCode;
	}
	public abstract String getType();
}
