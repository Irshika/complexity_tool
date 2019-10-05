/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table_model;

/**
 *
 * @author msillk
 */
public class TableModel {

    private String lineNo;
    private String prgStmt;
    private String tokens;
    private String Cs;
    private String Cnc;
    private String Ci;
    private String Ctc;
    private String TW;
    private String Cps;
    private String Cr;

    public TableModel() {

    }

    public TableModel(String lineNo, String prgStmt, String tokens, String Cs, String Cnc, String Ci, String Ctc, String TW, String Cps, String Cr) {
        this.lineNo = lineNo;
        this.prgStmt = prgStmt;
        this.tokens = tokens;
        this.Cs = Cs;
        this.Cnc = Cnc;
        this.Ci = Ci;
        this.Ctc = Ctc;
        this.TW = TW;
        this.Cps = Cps;
        this.Cr = Cr;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getPrgStmt() {
        return prgStmt;
    }

    public void setPrgStmt(String prgStmt) {
        this.prgStmt = prgStmt;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getCs() {
        return Cs;
    }

    public void setCs(String Cs) {
        this.Cs = Cs;
    }

    public String getCnc() {
        return Cnc;
    }

    public void setCnc(String Cnc) {
        this.Cnc = Cnc;
    }

    public String getCi() {
        return Ci;
    }

    public void setCi(String Ci) {
        this.Ci = Ci;
    }

    public String getCtc() {
        return Ctc;
    }

    public void setCtc(String Ctc) {
        this.Ctc = Ctc;
    }

    public String getTW() {
        return TW;
    }

    public void setTW(String TW) {
        this.TW = TW;
    }

    public String getCps() {
        return Cps;
    }

    public void setCps(String Cps) {
        this.Cps = Cps;
    }

    public String getCr() {
        return Cr;
    }

    public void setCr(String Cr) {
        this.Cr = Cr;
    }

}
