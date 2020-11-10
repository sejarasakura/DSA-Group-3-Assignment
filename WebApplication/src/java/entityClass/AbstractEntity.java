/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import adtClass.ArrList;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.*;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public abstract class AbstractEntity<T extends AbstractEntity> implements Comparable, Serializable {

    /**
     * Data storing directory
     */
    public static final String STORING_DIR = System.getProperty("user.dir") + "\\data";

    /**
     *
     */
    public AbstractEntity() {

    }

    /**
     * Check is null or not
     *
     * @return
     */
    public abstract boolean isNotNull();

    /**
     * Split row of string to object
     *
     * @param rowData
     * @return
     */
    public abstract boolean split(String rowData);

    /**
     * Check is same or not
     *
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Hashing
     *
     * @return
     */
    @Override
    public abstract int hashCode();

    /**
     * Convert to string
     *
     * @return
     */
    @Override
    public abstract String toString();

    /**
     * To support comparable
     *
     * @param t
     * @return
     */
    @Override
    public abstract int compareTo(Object t);

    public String getStorageDir() {
        return STORING_DIR + "\\" + this.getClass().getSimpleName() + ".csv";
    }

    public static Iterator<? extends AbstractEntity> readDataToCsv(AbstractEntity c) {
        
        try {
            
            ArrList<AbstractEntity> entity;
            
            FileReader fr = new FileReader(c.getStorageDir());
            
            entity = new ArrList<AbstractEntity>(
                    new CsvToBeanBuilder(fr).withType(c.getClass()).build().parse());
            
            return entity.iterator();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AbstractEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static boolean addDataToCsv(ArrList<? extends AbstractEntity> it){
        
        // block error
        if(it.size() <= 0)
            return false;
        
        // read data from file
        ArrList<AbstractEntity> data = new ArrList(AbstractEntity.readDataToCsv(it.get(0)));
        // add new data to array list
        for(AbstractEntity x : it){
            data.add(x);
        }
        
        // rewrite data to csv
        return AbstractEntity.reWriteAllDataToCsv(data);
        
    }

    public static boolean reWriteAllDataToCsv(ArrList<? extends AbstractEntity> it) {

        if(it.size() == 0)
            return false;
        try {
            try (FileWriter writer = new FileWriter(it.get(0).getStorageDir())) {
                StatefulBeanToCsv<AbstractEntity> csvWriter = new StatefulBeanToCsvBuilder<AbstractEntity>(writer)
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                        .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                        .withOrderedResults(false)
                        .build();
                
                csvWriter.write((Iterator<AbstractEntity>) it.iterator());
                return true;
            }            
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            Logger.getLogger(AbstractEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    public boolean updateDataToCsv() {
        
        return true;
        
    }

    public boolean deleteDataToCsv() {

        return true;
    }
}
