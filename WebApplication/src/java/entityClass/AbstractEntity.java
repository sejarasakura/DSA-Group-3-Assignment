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
import enumClass.OutputColor;
import java.io.IOException;
import java.io.*;
import java.util.Iterator;
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
     * Check is same or not
     *
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Check id is same or not
     *
     * @param obj
     * @return
     */
    public abstract boolean id_equals(Object obj);

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

    /**
     * To get the storage file path
     *
     * @return
     */
    public String getStorageFile() {
        return STORING_DIR + "\\" + this.getClass().getSimpleName() + ".csv";
    }

    /**
     * Adding listed new record to CSV file.
     *
     * @param datas
     * @return
     */
    public static boolean addDataToCsv(ArrList<? extends AbstractEntity> datas) {
    //<editor-fold defaultstate="collapsed" desc="add record">
        boolean result = true;

        /* block lenght less than */
        if (datas.size() <= 0) {
            return false;
        }

        /* read data from file */
        ArrList<AbstractEntity> data = new ArrList(AbstractEntity.privateReadDataFormCsv(datas.get(0), false));

        boolean depucate_record, all_d_r = true;
        /* add new data to array list */
        for (AbstractEntity x : datas) {

            depucate_record = data.find_AbstractEntity(x);
            all_d_r &= depucate_record;

            if (depucate_record) {
                System.out.println(OutputColor.TEXT_YELLOW
                        + "[adding the depucate record] is denyed"
                        + OutputColor.TEXT_RESET);
            } else {
                data.add(x);
                System.out.println(OutputColor.TEXT_GREEN
                        + "added record : " + x
                        + OutputColor.TEXT_RESET);
            }
        }

        result &= AbstractEntity.privateReWriteAllDataToCsv(data, false);

        if (result && !all_d_r) {
            System.out.println(OutputColor.TEXT_GREEN
                    + "added record to file : " + datas.get(0).getStorageFile()
                    + OutputColor.TEXT_RESET);
        } else {
            System.out.println(OutputColor.TEXT_RED
                    + "adding record unsucessful in file : " + datas.get(0).getStorageFile()
                    + OutputColor.TEXT_RESET);
        }

        /* rewrite data to csv */
        return result;

    }
    //</editor-fold>
    
    
    /**
     * Reading all record form CSV file.
     *
     * @param reference
     * @return
     */
    public static Iterator<? extends AbstractEntity> readDataFormCsv(AbstractEntity reference) {
    //<editor-fold defaultstate="collapsed" desc="read record">
        return privateReadDataFormCsv(reference, true);
    }
    
    private static Iterator<? extends AbstractEntity> privateReadDataFormCsv(AbstractEntity c, boolean show_e) {
        try {

            ArrList<AbstractEntity> entity;

            FileReader fr = new FileReader(c.getStorageFile());

            entity = new ArrList<AbstractEntity>(
                    new CsvToBeanBuilder(fr).withType(c.getClass()).build().parse());

            if (show_e) {
                System.out.println(OutputColor.TEXT_GREEN
                        + "readed record : " + c.getStorageFile()
                        + " [ " + entity.size() + " Record found]"
                        + OutputColor.TEXT_RESET);
            }

            return entity.iterator();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AbstractEntity.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (show_e) {
            System.out.println(OutputColor.TEXT_RED
                    + "noting readed in directory : " + c.getStorageFile()
                    + OutputColor.TEXT_RESET);
        }
        return null;
    }


    //</editor-fold>
    
    /**
     * Rewrite all record to CSV file.
     *
     * @param datas
     * @return
     */
    public static boolean reWriteAllDataToCsv(ArrList<? extends AbstractEntity> datas) {
    //<editor-fold defaultstate="collapsed" desc="write record">
        return privateReWriteAllDataToCsv(datas, true);
    }
    
    public static boolean privateReWriteAllDataToCsv(ArrList<? extends AbstractEntity> it, boolean show_e) {

        if (it.size() <= 0) {
            return false;
        }
        try {
            try (FileWriter writer = new FileWriter(it.get(0).getStorageFile())) {
                StatefulBeanToCsv<AbstractEntity> csvWriter
                        = new StatefulBeanToCsvBuilder<AbstractEntity>(writer)
                                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                                .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                                .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                                .withOrderedResults(false)
                                .build();

                csvWriter.write((Iterator<AbstractEntity>) it.iterator());
                if (show_e) {
                    System.out.println(OutputColor.TEXT_GREEN
                            + "rewrited record to file : " + it.get(0).getStorageFile()
                            + OutputColor.TEXT_RESET);
                }
                return true;
            }
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            Logger.getLogger(AbstractEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (show_e) {
            System.out.println(OutputColor.TEXT_RED
                    + "rewrite record unsucessful in file : " + it.get(0).getStorageFile()
                    + OutputColor.TEXT_RESET);
        }
        return false;

    }
    //</editor-fold>
    
    /**
     * Update the listed record
     *
     * @param datas
     * @return
     */
    public static boolean updateDataToCsv(ArrList<? extends AbstractEntity> datas) {
    //<editor-fold defaultstate="collapsed" desc="update record">

        /* block error */
        if (datas.size() <= 0) {
            return false;
        }

        /* read data from file */
        ArrList<AbstractEntity> allrecord = new ArrList(AbstractEntity.privateReadDataFormCsv(datas.get(0), false));
        boolean updated, allUpdate = true;
        for (AbstractEntity data : datas) {
            updated = false;
            for (int i = 0; i < allrecord.size() && updated == false; i++) {
                if (data.id_equals(allrecord.get(i))) {
                    System.out.println(OutputColor.TEXT_GREEN
                            + "updated record :" + data + OutputColor.TEXT_RESET);
                    allrecord.set(i, data);
                    updated = true;
                }
            }
            allUpdate &= updated;
            if (updated == false) {
                System.out.println(OutputColor.TEXT_RED
                        + "fail updated :" + data + OutputColor.TEXT_RESET);
            }
        }
        allUpdate &= privateReWriteAllDataToCsv(datas, false);

        return allUpdate;
    }

    //</editor-fold>
    
    /**
     * Delete the listed record
     *
     * @param datas
     * @return
     */
    public static boolean deleteDataToCsv(ArrList<? extends AbstractEntity> datas) {
    //<editor-fold defaultstate="collapsed" desc="delete record">

        /* block error */
        if (datas.size() <= 0) {
            return false;
        }

        /* read data from file */
        ArrList<AbstractEntity> allrecord = new ArrList(AbstractEntity.privateReadDataFormCsv(datas.get(0), false));
        boolean deleted, alldelete = true;
        for (AbstractEntity data : datas) {
            deleted = false;
            for (int i = 0; i < allrecord.size() && deleted == false; i++) {
                if (data.id_equals(allrecord.get(i))) {
                    System.out.println(OutputColor.TEXT_GREEN
                            + "deleted record :" + data + OutputColor.TEXT_RESET);
                    allrecord.remove(i);
                    deleted = true;
                }
            }
            alldelete &= deleted;
            if (deleted == false) {
                System.out.println(OutputColor.TEXT_RED
                        + "fail delete :" + data + OutputColor.TEXT_RESET);
            }
        }
        alldelete &= privateReWriteAllDataToCsv(datas, false);

        return alldelete;
    }

    //</editor-fold>
    
    /*
    * for large amount
    * Un efficent method is need update faster use AbstractEntity.updateDataToCsv for large amount
     */
    public boolean updateThisToCsv() {
        ArrList<AbstractEntity> ref = new ArrList<AbstractEntity>();
        ref.add(this);
        return updateDataToCsv(ref);
    }

    /*
    * for large amount
    * Un efficent method is need read faster use AbstractEntity.addDataToCsv for large amount
     */
    public boolean addThisToCsv() {
        ArrList<AbstractEntity> ref = new ArrList<AbstractEntity>();
        ref.add(this);
        return AbstractEntity.addDataToCsv(ref);
    }

    /*
    * for large amount
    * Un efficent method is need update faster use AbstractEntity.updateDataToCsv for large amount
     */
    public boolean deleteThisToCsv() {
        ArrList<AbstractEntity> ref = new ArrList<AbstractEntity>();
        ref.add(this);
        return AbstractEntity.deleteDataToCsv(ref);
    }
    
}
