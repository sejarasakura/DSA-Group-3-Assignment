/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Local project import
 */
import main.*;
import cilent.*;
import cilent.filter.*;
import cilent.pages.*;
import cilent.servlet.*;
import entity.*;
import adt.*;
import adt.node.*;
import adt.interfaces.*;
import com.google.gson.Gson;
import csv.converter.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.regex.Pattern;
import xenum.*;

/**
 *
 * @author Lim Sai Keat
 */
public class LatLng {

    /**
     * To convert latitude and Longitude saving format format
     * [Latitude]_[Longitude]
     */
    private String latlng;

    /**
     * The real full latitude number
     */
    private double lat;

    /**
     * The real full longitude number
     */
    private double lng;

    public LatLng() {
    }

    public LatLng(String latlng, double lat, double lng) {
        this.latlng = latlng;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return this.lat + "_" + this.lng;
    }

    public static String DOUBLE_PATTERN = "^[\\+\\-]{0,1}[0-9]+[\\.]{1}[0-9]+[_]{1}[\\+\\-]{0,1}[0-9]+[\\.]{1}[0-9]+$";

    public boolean extract(String latlng) {
        if (!latlng.contains("_")) {
            return false;
        }
        if (!latlng.matches(DOUBLE_PATTERN)) {
            return false;
        }
        String[] x = latlng.split("_");
        this.latlng = latlng;
        this.lat = Double.parseDouble(x[0]);
        this.lng = Double.parseDouble(x[1]);
        return true;
    }

    public boolean extractJSON(String latlng) {

        Gson gson = new Gson();

        InterDictionary result = new XHashedDictionary(
                gson.fromJson(latlng, WebConfig.WRITING_CLASS)
        );
        try {

            this.lat = (double) result.getValue("lat");
            this.lng = (double) result.getValue("lng");
            this.latlng = lat + "_" + lng;

            return true;

        } catch (Exception e) {

            return false;

        }
    }

}
