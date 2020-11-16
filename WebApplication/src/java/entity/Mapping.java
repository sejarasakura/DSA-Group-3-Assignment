/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.*;

/**
 * Mapping class to store mapping details include booking destination and source and fetch date
 * @Mapping
 * @author ITSUKA KOTORI
 */
public class Mapping extends AbstractEntity<Mapping>{
    
    /**
     * Primary key of the map
     */
    @CsvBindByName
    private String map_id;
    
    /**
     * Form position id format follow by Google map API
     */
    @CsvBindByName
    private String source_id;
    
    /**
     * To position id format follow by qq map API
     */
    @CsvBindByName
    private String destination_id;
    
    /**
     * Customer fetching date
     */
    @CsvBindByName
    @CsvDate("dd.MM.yyyy.hh.mm.ss")
    private Date fetch_date;

    /**
     * Empty constructor
     */
    public Mapping() {
    }
    
    /**
     * Parameter constructor
     * @param map_id
     * @param source_id
     * @param destination_id
     * @param fetch_date
     */
    public Mapping(String map_id, String source_id, String destination_id, Date fetch_date) {
        this.map_id = map_id;
        this.source_id = source_id;
        this.destination_id = destination_id;
        this.fetch_date = fetch_date;
    }

    public String getMap_id() {
        return map_id;
    }

    public void setMap_id(String map_id) {
        this.map_id = map_id;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public Date getFetch_date() {
        return fetch_date;
    }

    public void setFetch_date(Date fetch_date) {
        this.fetch_date = fetch_date;
    }
    
    @Override
    public boolean isNotNull() {
        return this.map_id == null ? false: !this.map_id.isEmpty();
    }

    @Override
    public String toString() {
        return "Mapping{" + "map_id=" + map_id + ", source_id=" + source_id + ", destination_id=" + destination_id + ", fetch_date=" + fetch_date + '}';
    }
    
    @Override
    public boolean id_equals(Object obj) {
        return ((Mapping)obj).map_id.equals(this.map_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mapping other = (Mapping) obj;
           
        return Objects.equals(this.map_id, other.map_id);
    }

    @Override
    public int compareTo(Object t) {
        return ((Mapping)t).map_id.compareTo(this.map_id);
    }
    
    public String loadGoogleMap(){
        return null;
    }
    
    public Map getDestination(){
        Map map = new HashMap();
        return map;
    }
}
