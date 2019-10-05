/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity_calculation;

import constants.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blitz
 */
public class Utilities implements IUtilities {

    private static int comp = 0;
    private String parentClass;
    private String childClass;
    private String fileType;

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public void identifyParentJava() {
        BufferedReader buffR = Files.loadFile();
        String currentLine;
        String[] words;
        String parent = null;
        int index;

        try {
            while ((currentLine = buffR.readLine()) != null) {
                words = currentLine.split("[ {}]");

                if (currentLine.contains(Constants.EXTENDS)) {
                    for (String word : words) {
                        if (word.equals(Constants.EXTENDS)) {
                            index = Arrays.asList(words).indexOf(Constants.EXTENDS);
                            parent = words[index + 1];
                        }
                    }
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex);
        } finally {
            Files.unloadFile();
        }
        this.setParentClass(parent);
    }

    @Override
    public void identifyChildJava() {
        BufferedReader buffR = Files.loadFile();
        String currentLine;
        String[] words;
        String child = null;
        int index;

        try {
            while ((currentLine = buffR.readLine()) != null) {
                words = currentLine.split("[ {}]");

                if (currentLine.contains(Constants.EXTENDS)) {
                    for (String word : words) {
                        if (word.equals(Constants.CLASS)) {
                            index = Arrays.asList(words).indexOf(word);
                            child = words[index + 1];
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            Files.unloadFile();
        }

        this.setChildClass(child);
    }

    @Override
    public void identifyParentCpp() {
        BufferedReader buffR = Files.loadFile();
        String currentLine;
        String[] words;
        String parent = null;
        int index;

        try {
            while ((currentLine = buffR.readLine()) != null) {
                words = currentLine.split("[ :{}]");

                for (String word : words) {
                    if (word.equalsIgnoreCase(Constants.CLASS)) {
                        index = Arrays.asList(words).indexOf(word);
                        parent = words[index + 3];
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Files.unloadFile();
        }
        this.setParentClass(parent);
    }

    @Override
    public void identifyChildCpp() {
        BufferedReader buffR = Files.loadFile();
        String currentLine;
        String[] words;
        String child = null;
        int index;

        try {
            while ((currentLine = buffR.readLine()) != null) {
                words = currentLine.split("[ :{}]");

                for (String word : words) {
                    if (word.equalsIgnoreCase(Constants.CLASS)) {
                        index = Arrays.asList(words).indexOf(word);
                        child = words[index + 1];
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Files.unloadFile();
        }
        this.setChildClass(child);
    }

    public String setJavaFilePath(String className) {
        String currentPath = Files.getFilePath();
        String words[];
        String newPath = new String();

        currentPath = currentPath.replace("\\", "/");
        words = currentPath.split("/");

        for (String word : words) {
            if (!word.contains(Constants.JAVA_EXT)) {

                newPath = newPath.concat(word + "/");
            } else {
                identifyParentJava();
                newPath = newPath.concat(this.getParentClass() + Constants.JAVA_EXT);
            }
        }
        return newPath;
    }

    public String setCppFilePath(String className) {
        String currentPath = Files.getFilePath();
        String words[];
        String newPath = new String();

        currentPath = currentPath.replace("\\", "/");
        words = currentPath.split("/");

        for (String word : words) {
            if (!word.contains(Constants.CPP_EXT)) {
                newPath = newPath.concat(word + "/");
            } else {
                identifyParentJava();
                newPath = newPath.concat(this.getParentClass() + Constants.CPP_EXT);
            }
        }

        return newPath;
    }

    public void identifyFileType(String path) {
        String[] words;
        words = path.split("[ ./]");

        if (Constants.JAVA_EXT.contains(words[words.length - 1])) {
            setFileType(Constants.FILE_TYPE_JAVA);
        } else if (Constants.CPP_EXT.contains(words[words.length - 1])) {
            setFileType(Constants.FILE_TYPE_CPP);
        }
    }

    public int getComplexity() {
        String currParent;
        identifyFileType(Files.getFilePath());

        if (getFileType().equals(Constants.FILE_TYPE_JAVA)) {
            identifyParentJava();
            identifyChildJava();
            comp = 0;

            currParent = getParentClass();

            while (getParentClass() != null) {
                comp++;
                Files.unloadFile();
                Files.setFilePath(setJavaFilePath(currParent));
                Files.loadFile();
                identifyParentJava();
                currParent = getParentClass();
            }
        } else if (getFileType().equals(Constants.FILE_TYPE_CPP)) {
            identifyParentCpp();
            identifyChildCpp();
            comp = 0;

            currParent = getParentClass();

            while (getParentClass() != null) {
                comp++;
                Files.unloadFile();
                Files.setFilePath(setCppFilePath(currParent));
                Files.loadFile();
                identifyParentCpp();
                currParent = getParentClass();
            }
        }
        return comp;
    }

    public static void writeFile(String content) {
        try (FileWriter writer = new FileWriter("recent_files.log", true);
                BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(content);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
