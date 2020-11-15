/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;
import java.util.Objects;

/**
 *
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
     * To position id format follow by Google map API
     */
    @CsvBindByName
    private String destination_id;
    
    /**
     * Customer fetching date
     */
    @CsvBindByName
    @CsvDate("dd.MM.yyyy.hh.mm.ss")
    private Date fetch_date;

    @Override
    public boolean isNotNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Mapping{" + "map_id=" + map_id + ", source_id=" + source_id + ", destination_id=" + destination_id + ", fetch_date=" + fetch_date + '}';
    }
    
    @Override
    public boolean id_equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
