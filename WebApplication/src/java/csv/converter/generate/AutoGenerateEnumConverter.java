/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv.converter.generate;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ITSUKA KOTORI
 */
public class AutoGenerateEnumConverter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            String content = getContent
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
