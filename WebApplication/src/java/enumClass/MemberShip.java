/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumClass;

import java.util.Arrays;

/**
 *
 * @author ITSUKA KOTORI
 */
public enum MemberShip {
    //<editor-fold defaultstate="collapsed" desc="Enumerator(MembershipLevel) list">
    /*Example  (code, seq_code/power, name, decription)*/
    NORMAL      ("NO", 0, "Normals", "Normal membership is all the defualt registered customer.", 0),
    PREMIUM        ("PE", 1, "Premium",  "Premium membershiip is all the normal paid member", 0.08),
    GOLD          ("AU", 2, "Gold",  "Gold membership is the paid member with VIP class", 0.20);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Enumerator(MembershipLevel) properties">
    private final String code;
    private final int seq_code;
    private final String name;
    private final String description;
    private final double discount;    
    private final static MemberShip[] vs = MemberShip.values();
    private MemberShip(String code, int seq_code, String name, String description, double discount) {
        this.code = code;
        this.seq_code = seq_code;
        this.name = name;
        this.description = description;
        this.discount = discount;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Enumerator(MembershipLevel) Getter">
    public String getDatabaseCode() {
        return code;
    }
    
    public double getDiscount(){
        return discount;
    }

    public int getSequenceCode() {
        return seq_code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Enumerator(MembershipLevel) Convertor">
    public static int length(){
        return 3;
    }
     public static MemberShip getValue(String string) {
        for(MemberShip v: vs){
            if(Arrays.asList(v.code.toLowerCase(),v.name.toLowerCase()).contains(string.toLowerCase()))
                return v;
        }
        return null;
    }
     public static MemberShip getValue(Object member_level){
        if(member_level instanceof MemberShip)
            return (MemberShip)member_level;
        else if(member_level instanceof String)
            return MemberShip.getValue((String)member_level);
        else{
            return null;
        }
     }
    //</editor-fold>
     
     public static void main(String[] args){
         System.out.println(MemberShip.getValue("AU"));
     }
}
