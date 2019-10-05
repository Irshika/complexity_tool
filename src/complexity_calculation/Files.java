/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity_calculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blitz
 */
public class Files {

    private static String filePath;
    private static BufferedReader buffR = null;

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        Files.filePath = filePath;
    }

    public static BufferedReader loadFile() {
        File file = new File(filePath);

        try {
            buffR = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }

        return buffR;
    }

    public static void unloadFile() {
        try {
            buffR.close();
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int getLineCount() {
        int count = 0;

        if (buffR == null) {
            return 0;
        } else {
            try {
                while (buffR.readLine() != null) {
                    count++;
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

        return count;
    }

    public static String getFileExtension() {
        String extension = "";
        int i = filePath.lastIndexOf('.');
        int p = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));

        if (i > p) {
            return extension = filePath.substring(i + 1);
        } else {
            System.out.println("Please check the directory and file name.");
            return extension;
        }
    }
}
