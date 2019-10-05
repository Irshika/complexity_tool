/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_complexity_ms;

import com.spm.model.ComplexityFactor;
import com.spm.model.JavaSourceCode;
import com.spm.model.SourceCode;
import complexity_calculation.Files;
import complexity_calculation.Utilities;
import complexity_calculation.ctrlstructure.ControlStructureController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import size_complexity.SizeComplexity;
import table_model.TableModel;

/**
 *
 * @author msillk
 */
public class FXMLDocumentController implements Initializable {

    Stage stage = new Stage();

    @FXML
    private Button file_chooser;

    @FXML
    private TableView<TableModel> table;

    @FXML
    private TableColumn<?, ?> colStmt;

    @FXML
    private TableColumn<?, ?> colCi;

    @FXML
    private TableColumn<?, ?> colCtc;

    @FXML
    private TableColumn<?, ?> colCs;

    @FXML
    private TableColumn<?, ?> colCr;

    @FXML
    private TableColumn<?, ?> colTokens;

    @FXML
    private TableColumn<?, ?> colTw;

    @FXML
    private TableColumn<?, ?> colCps;

    @FXML
    private TableColumn<?, ?> colLineNo;

    @FXML
    private Label rcF1;

    @FXML
    private Label rcF2;

    @FXML
    private Label rcF3;

    @FXML
    private Label rcF4;

    @FXML
    private Label rcF1Name;

    @FXML
    private Label rcF2Name;

    @FXML
    private Label rcF3Name;

    @FXML
    private Label rcF4Name;

    @FXML
    private Text totComp;

    @FXML
    private Text currFile;

    @FXML
    private Text currFileType;

    ObservableList<TableModel> list = null;
    int Ctc, tempCtc, Ci, Cnc, TW, Cps, Cs = 0;
    int totComplexity = 0;

    @FXML
    void rcF1Clicked(MouseEvent event) {
        Ci = 0;
        Cs = 0;
        tempCtc = 0;
        Ctc = 0;
        Cnc = 0;
        TW = 0;
        Cps = 0;
        totComplexity = 0;

        Utilities util = new Utilities();
        Files.setFilePath(rcF1.getText());
        System.out.println(rcF1.getText());
        util.identifyChildCpp();
        util.identifyFileType(Files.getFilePath());
        currFile.setText(util.getChildClass() + "." + util.getFileType());
        currFileType.setText(util.getFileType());

        colLineNo.setCellValueFactory(new PropertyValueFactory<>("lineNo"));
        colCs.setCellValueFactory(new PropertyValueFactory<>("Cs"));
        colStmt.setCellValueFactory(new PropertyValueFactory<>("prgStmt"));
        colCi.setCellValueFactory(new PropertyValueFactory<>("Ci"));
        colCtc.setCellValueFactory(new PropertyValueFactory<>("Ctc"));
        colTokens.setCellValueFactory(new PropertyValueFactory<>("tokens"));
        colTw.setCellValueFactory(new PropertyValueFactory<>("TW"));
        colCps.setCellValueFactory(new PropertyValueFactory<>("Cps"));

        totComplexity = 0;
        setValues();
        setTotalComplexity();
        table.setItems(list);
    }

    @FXML
    void rcF2Clicked(MouseEvent event) {
        Ci = 0;
        Cs = 0;
        tempCtc = 0;
        Ctc = 0;
        Cnc = 0;
        TW = 0;
        Cps = 0;
        totComplexity = 0;

        Utilities util = new Utilities();
        Files.setFilePath(rcF2.getText());

        util.identifyChildCpp();
        util.identifyFileType(Files.getFilePath());
        currFile.setText(util.getChildClass() + "." + util.getFileType());
        currFileType.setText(util.getFileType());

        colLineNo.setCellValueFactory(new PropertyValueFactory<>("lineNo"));
        colCs.setCellValueFactory(new PropertyValueFactory<>("Cs"));
        colStmt.setCellValueFactory(new PropertyValueFactory<>("prgStmt"));
        colCi.setCellValueFactory(new PropertyValueFactory<>("Ci"));
        colCtc.setCellValueFactory(new PropertyValueFactory<>("Ctc"));
        colTokens.setCellValueFactory(new PropertyValueFactory<>("tokens"));
        colTw.setCellValueFactory(new PropertyValueFactory<>("TW"));
        colCps.setCellValueFactory(new PropertyValueFactory<>("Cps"));

        totComplexity = 0;
        setValues();
        setTotalComplexity();
        table.setItems(list);
    }

    @FXML
    void rcF3Clicked(MouseEvent event) {
        Ci = 0;
        Cs = 0;
        tempCtc = 0;
        Ctc = 0;
        Cnc = 0;
        TW = 0;
        Cps = 0;
        totComplexity = 0;

        Utilities util = new Utilities();
        Files.setFilePath(rcF3.getText());

        util.identifyChildCpp();
        util.identifyFileType(Files.getFilePath());
        currFile.setText(util.getChildClass() + "." + util.getFileType());
        currFileType.setText(util.getFileType());

        colLineNo.setCellValueFactory(new PropertyValueFactory<>("lineNo"));
        colCs.setCellValueFactory(new PropertyValueFactory<>("Cs"));
        colStmt.setCellValueFactory(new PropertyValueFactory<>("prgStmt"));
        colCi.setCellValueFactory(new PropertyValueFactory<>("Ci"));
        colCtc.setCellValueFactory(new PropertyValueFactory<>("Ctc"));
        colTokens.setCellValueFactory(new PropertyValueFactory<>("tokens"));
        colTw.setCellValueFactory(new PropertyValueFactory<>("TW"));
        colCps.setCellValueFactory(new PropertyValueFactory<>("Cps"));

        totComplexity = 0;
        setValues();
        setTotalComplexity();
        table.setItems(list);
    }

    @FXML
    void rcF4Clicked(MouseEvent event) {
        Ci = 0;
        Cs = 0;
        tempCtc = 0;
        Ctc = 0;
        Cnc = 0;
        TW = 0;
        Cps = 0;
        totComplexity = 0;

        Utilities util = new Utilities();
        Files.setFilePath(rcF4.getText());

        util.identifyChildCpp();
        util.identifyFileType(Files.getFilePath());
        currFile.setText(util.getChildClass() + "." + util.getFileType());
        currFileType.setText(util.getFileType());

        colLineNo.setCellValueFactory(new PropertyValueFactory<>("lineNo"));
        colCs.setCellValueFactory(new PropertyValueFactory<>("Cs"));
        colStmt.setCellValueFactory(new PropertyValueFactory<>("prgStmt"));
        colCi.setCellValueFactory(new PropertyValueFactory<>("Ci"));
        colCtc.setCellValueFactory(new PropertyValueFactory<>("Ctc"));
        colTokens.setCellValueFactory(new PropertyValueFactory<>("tokens"));
        colTw.setCellValueFactory(new PropertyValueFactory<>("TW"));
        colCps.setCellValueFactory(new PropertyValueFactory<>("Cps"));

        totComplexity = 0;
        setValues();
        setTotalComplexity();
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilities util = new Utilities();
        final FileChooser fileChooser = new FileChooser();

        file_chooser.setOnAction((ActionEvent e) -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Files.setFilePath(file.getPath());
                Utilities.writeFile(file.getPath() + "\n");

                util.identifyChildCpp();
                util.identifyFileType(Files.getFilePath());
                currFile.setText(util.getChildClass() + "." + util.getFileType());
                currFileType.setText(util.getFileType());
            }

            colLineNo.setCellValueFactory(new PropertyValueFactory<>("lineNo"));
            colCs.setCellValueFactory(new PropertyValueFactory<>("Cs"));
            colStmt.setCellValueFactory(new PropertyValueFactory<>("prgStmt"));
            colCi.setCellValueFactory(new PropertyValueFactory<>("Ci"));
            colCtc.setCellValueFactory(new PropertyValueFactory<>("Ctc"));
            colTokens.setCellValueFactory(new PropertyValueFactory<>("tokens"));
            colTw.setCellValueFactory(new PropertyValueFactory<>("TW"));
            colCps.setCellValueFactory(new PropertyValueFactory<>("Cps"));

            SourceCode jsc = new JavaSourceCode();
            String line;
            int linecount = CountLines(file);
            String[] SCBuffer = new String[linecount];
            BufferedReader reader = Files.loadFile();
            int i = 0;

            try {
                while ((line = reader.readLine()) != null) {
                    SCBuffer[i] = line;
                    i++;
                }
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ComplexityFactor cf = new ComplexityFactor(SCBuffer, util.getFileType(), util.getChildClass());
            Cnc = (int) cf.getsComplexity_NestContrStruct();

            totComplexity = 0;
            setValues();
            setTotalComplexity();
            table.setItems(list);

            setRecentFies();
        });
        setRecentFies();
    }

    private void setValues() {
        ControlStructureController ctrStr = new ControlStructureController();
        SizeComplexity sizeC = new SizeComplexity();

        Utilities util = new Utilities();
        TableModel tabModel;
        list = FXCollections.observableArrayList();

        String currentLine;
        int lineNo;
        lineNo = 0;

        Ci = util.getComplexity();
        BufferedReader buffR = Files.loadFile();
        Ci = Ci + 1;

        try {
            while ((currentLine = buffR.readLine()) != null) {
                tabModel = new TableModel();

                sizeC.CalC(currentLine);
                if (currentLine.length() != 0) {
                    tempCtc = ctrStr.returnCtcValues(currentLine);

                    tabModel.setPrgStmt(currentLine);

                    //Control Structure
                    if (!String.valueOf(tempCtc).equals("0")) {
                        tabModel.setCtc(String.valueOf(tempCtc));
                    }

                    if (!currentLine.replaceAll("\\s+", "").equals("{") && !currentLine.replaceAll("\\s+", "").equals("}") && !currentLine.replaceAll("\\s+", "").startsWith("//") && !currentLine.replaceAll("\\s+", "").startsWith("/*") && !currentLine.replaceAll("\\s+", "").startsWith("*")) {
                        ++lineNo;
                        tabModel.setLineNo(String.valueOf(lineNo));

                        tabModel.setCi(String.valueOf(Ci));

                        if (currentLine.contains(constants.Constants.EXTENDS)) {
                            if (util.getFileType().equals(constants.Constants.FILE_TYPE_JAVA)) {
                                tabModel.setTokens("extends");
                            } else if (util.getFileType().equals(constants.Constants.FILE_TYPE_CPP)) {
                                tabModel.setTokens(" : ");
                            }
                        } else {
                            tabModel.setTokens(sizeC.getTokens());
                        }

                        TW = tempCtc + Ci + 1;
                        tabModel.setTW(String.valueOf(TW));

                        tabModel.setCps(String.valueOf(Cps));

                        tabModel.setCs(String.valueOf(sizeC.getLineComp()));
                        Cs = sizeC.getLineComp();

                        Cps = Cs * TW;
                        Ctc = Ctc + tempCtc;
                    }
                }

                list.add(tabModel);
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Files.unloadFile();
        }
    }

    private void setTotalComplexity() {
        totComplexity = Ctc + (Ci + 1) + Cnc;

        totComp.setText(String.valueOf(totComplexity));
    }

    private void setRecentFies() {
        File file = new File("recent_files.log");
        String[] paths = new String[4];
        BufferedReader buffR = null;

        try {
            buffR = new BufferedReader(new FileReader(file));
            int j = 0;
            String currentPath;
            while ((currentPath = buffR.readLine()) != null && j < paths.length) {
                if (!currentPath.equals(paths[j])) {
                    paths[j] = currentPath;
                }
                j++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffR.close();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < paths.length; i++) {
                if (paths[i] != null) {
                    paths[i] = paths[i].replace("\\", "/");
                }
            }

            String arr[] = new String[4];

            for (int i = 0; i < paths.length; i++) {
                if (paths[i] != null) {
                    String tempArr[] = paths[i].split("/");
                    arr[i] = tempArr[tempArr.length - 1];
                }
            }

            rcF1Name.setText(arr[0]);
            rcF1.setText(paths[0]);

            rcF2Name.setText(arr[1]);
            rcF2.setText(paths[1]);

            rcF3Name.setText(arr[2]);
            rcF3.setText(paths[2]);

            rcF4Name.setText(arr[3]);
            rcF4.setText(paths[3]);

        }
    }

    private static int CountLines(File f) {
        int count = 0;
        FileReader code;
        try {
            code = new FileReader(f);
            BufferedReader reader = new BufferedReader(code);
            while (reader.readLine() != null) {
                count++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
