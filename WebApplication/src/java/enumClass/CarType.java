/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumClass;

import com.opencsv.bean.CsvBindByName;
import java.util.Arrays;

/**
 *
 * @author ITSUKA KOTORI
 */
public enum CarType  implements AbstractEnum{
    
    JUSTRENT("JQ", "Just Rent", 4, 2.0F, 5.0F, 0.95F, 0.3f,"Find a nerest QuicKar now !!"),
    TAXI_rent("TR", "Taxi Rent", 4, 3.0F, 10.0F, 1.45F, 0.45f,"FInd a nearest taxi now !!"),
    PREMRENT("PQ", "Premium Rent", 4, 4.0F, 15.0F, 1.95F, 0.55f,"Premium car drivers all are top rate driver !!"),
    RENT_6SEAT("SS", "Just Rent (6-Seat)", 6, 3.00f, 12.00f, 1.75f, 0.55f,"It is larger venicles that can fit up to 6 passengers !!"),
    TAXI_RENT_6SEAT("TS", "Six seat taxi", 6, 4.00f, 15.00f, 2.15f, 0.65f,"It is larger taxi that can fit up to 6 passengers !!");
    
    /**
     * Car Type Saving ID
     */
    @CsvBindByName
    private final String code;
    /**
     * Car Type Name
     */
    private final String name;
    
    /**
     * Car Type Descriptions
     */
    private final String decription;
    
    /**
     * Car Type Seat Number
     */
    private final int seat_number; 
    
    /**
     * Car Type Values
     */
    private final float base_fair_price, minimum_price, price_per_km, price_per_min;
    
    private final static CarType[] vs = CarType.values();
    private CarType(String code, String name, int seat_number, float base_fair_price, float min_price, float price_per_km, float price_per_min, String desc) {
        this.code = code;
        this.name = name;
        this.seat_number = seat_number;
        this.base_fair_price = base_fair_price;
        this.minimum_price = min_price;
        this.price_per_km = price_per_km;
        this.decription = desc;
        this.price_per_min = price_per_min;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDecription() {
        return decription;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public float getBase_fair_price() {
        return base_fair_price;
    }

    public float getMinimum_price() {
        return minimum_price;
    }

    public float getPrice_per_km() {
        return price_per_km;
    }
    
    public float getPrice_per_min() {
        return price_per_min;
    }
    
    public static CarType getValue(String string) {
        for(CarType v: vs){
            if(Arrays.asList(v.code.toLowerCase(),v.name.toLowerCase()).contains(string.toLowerCase()))
                return v;
        }
        return null;
    }
     public static CarType getValue(Object quickar_type){
         if(quickar_type instanceof CarType)
            return (CarType)quickar_type;
        else if(quickar_type instanceof String)
            return CarType.getValue((String)quickar_type);
        else{
            return null;
        }
     }
    @Override
    public String getStringCode() {
        return this.getCode();
    }
    public static void main(String []args){
        System.out.println(CarType.getValue("Find a nerest QuicKar now !!"));
        System.out.println(CarType.getValue("Find a nerest QuicKar "));
        System.out.println(CarType.getValue("Just Rent"));
        System.out.println(CarType.getValue("FInd a nearest taxi now !!"));
        System.out.println(CarType.getValue("Find a QuicKar now !!"));
    }
}
