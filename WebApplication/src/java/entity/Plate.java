/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.XArrayList;
import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author ITSUKA KOTORI
 */
public class Plate extends AbstractEntity<Plate> {

    /*
    * Car plate id
     */
    @CsvBindByName
    private String plate_id;

    /*
    * Car plate alphablate
     */
    @CsvBindByName
    private String plateAlpha;

    /*
    * Car plate number
     */
    @CsvBindByName
    private String plateNumber;

    public Plate() {
    }

    public Plate(String plateAlpha, String plateNumber) {
        this.plateAlpha = plateAlpha;
        this.plateNumber = plateNumber;
    }

    public String getPlate_id() {
        return plate_id;
    }

    public void setPlate_id(String plate_id) {
        this.plate_id = plate_id;
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

    public String getFullPlateNumber() {
        return this.plateAlpha + " " + this.plateNumber;
    }

    public static Plate getPlate(String full_plate, String seperator) {

        XArrayList<String> split = new XArrayList(full_plate.split(seperator));
        if (split.size() < 2) {
            return null;
        }

        return new Plate(split.get(0), split.get(1));

    }

    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.plate_id.equals(((Plate) obj).plate_id);
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Plate{" + "plate_id=" + plate_id + ", plateAlpha=" + plateAlpha + ", plateNumber=" + plateNumber + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return this.plate_id;
    }
}
