/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv.converter.generate;

import adt.XArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Lim Sai Keat
 */
public class AutoGenerateEnumConverter {

    private StringBuilder sb = new StringBuilder();
    private StringBuilder sb_all = new StringBuilder();
    private String _package = "xenum.";
    private final String DIR = "C:\\Users\\ITSUKA KOTORI\\Documents\\GitHub\\DSA-Group-3-Assignment\\WebApplication\\src\\java\\xenum";
    private final String DIR_SAVE = "C:\\Users\\ITSUKA KOTORI\\Documents\\GitHub\\DSA-Group-3-Assignment\\WebApplication\\data\\code";
    XArrayList<String> data = new XArrayList<String>();

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        AutoGenerateEnumConverter x = new AutoGenerateEnumConverter();
        x.getData();
        x.getContents();
        System.out.println(x.toString());
    }

    private void getData() {
        File directoryPath;
        directoryPath = new File(DIR);
        String contents[] = directoryPath.list();
        for (String x : contents) {
            if (x.contains(".java")) {
                System.out.println(x);
                data.add(x.split(".java")[0]);
            }
        }
    }

    private void getContents() throws ClassNotFoundException {
        for (String x : data) {
            Class<?> _class = Class.forName(_package + x);
            getContent(_class);
        }
    }

    private void getContent(Class<?> cls) {

        sb = new StringBuilder();

        sb.append("\n\n");
        sb.append("/*Auto generated code by class ").append(this.getClass().getSimpleName()).append("*/\n");
        sb.append("package csv.converter;\n");
        sb.append("import adt.*;\n");
        sb.append("import xenum.*;\n");
        sb.append("import com.opencsv.bean.*;\n");
        sb.append("import com.opencsv.exceptions.*;\n");
        sb.append("/**\n *\n * @author Lim sai Keat\n */\n");
        sb.append("public class ").append(cls.getSimpleName()).append("Converter extends AbstractBeanField {\n\n");
        sb.append("\n");
        sb.append("\t@Override\n");
        sb.append("\tprotected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {\n");
        sb.append("\t\t").append(cls.getSimpleName()).append(" obj = (").append(cls.getSimpleName()).append(") value;\n");
        sb.append("\t\treturn obj.getCode();\n");
        sb.append("\t\t\n");
        sb.append("\t}\n");
        sb.append("\n");
        sb.append("\t@Override\n");
        sb.append("\tprotected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {\n");
        sb.append("\t\t\n");
        sb.append("\t\treturn ").append(cls.getSimpleName()).append(".valueOf(string);\n");
        sb.append("\t\t\n");
        sb.append("\t}\n");
        sb.append("}\n");
        sb.append("\n\n");

        try {
            FileWriter myWriter = new FileWriter(DIR_SAVE + "\\" + cls.getSimpleName() + "Converter.java");
            myWriter.write(sb.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        sb_all.append(sb);
    }

    @Override
    public String toString() {
        return sb_all.toString();
    }

}
