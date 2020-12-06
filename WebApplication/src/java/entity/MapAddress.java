/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.opencsv.bean.CsvBindByName;
import java.util.Objects;
import main.Functions;

/**
 *
 * @author ITSUKA KOTORI
 */
public final class MapAddress extends AbstractEntity {

    @CsvBindByName
    private String address_id;

    @CsvBindByName
    private String full_address;

    public MapAddress() {
    }

    public MapAddress(String address_id, String s_address) {
        this.address_id = address_id;
        this.setFull_address(full_address);
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getFull_address() {
        return full_address;
    }

    public String get_display_address() {
        return Functions.unhash_csv_record(full_address);
    }

    public void setFull_address(String full_address) {
        this.full_address = Functions.hash_csv_record(full_address);
    }

    @Override
    public String getId() {
        return this.address_id;
    }

    @Override
    public boolean isNotNull() {
        return this.address_id != null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.address_id);
        return hash;
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
        final MapAddress other = (MapAddress) obj;
        if (!Objects.equals(this.address_id, other.address_id)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.equals(obj);
    }

    @Override
    public String toString() {
        return "MapAddress{" + "address_id=" + address_id + ", s_address=" + full_address + '}';
    }

    @Override
    public int compareTo(Object t) {
        return this.address_id.compareTo(((MapAddress) t).address_id);
    }

    public boolean updateAddressUsing_API() {

        return true;
    }
}
