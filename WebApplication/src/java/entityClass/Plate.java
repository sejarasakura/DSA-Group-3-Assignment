/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClass;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Plate extends AbstractEntity{

    /*
    * Car pplate alphablate
    */
    @CsvBindByName
    private String plateAlpha;
    
    /*
    * Car plate number
    */
    @CsvBindByName
    private String plateNumber;

    public Plate(String plateAlpha, String plateNumber) {
        this.plateAlpha = plateAlpha;
        this.plateNumber = plateNumber;
    }

    public String getPlateAlpha() {
        return plateAlpha;
    }

    public void setPlateAlpha(String plateAlpha) {
        this.plateAlpha = plateAlpha;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    
    public String getFullPlateNumber(){
        return this.plateAlpha + " " + this.plateNumber;
    }
    
    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean split(String rowData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
