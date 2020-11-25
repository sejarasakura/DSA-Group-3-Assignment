/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenum;

import com.opencsv.bean.CsvBindByName;
import java.util.Arrays;

/**
 *
 * @author ITSUKA KOTORI
 */
public enum MemberShip implements AbstractEnum, Comparable<MemberShip> {
    /*Example  (code, seq_code/power, name, decription)*/
    NORMAL("NO", 0, "Normals", "Normal membership is all the defualt registered customer.", 0),
    PREMIUM("PE", 1, "Premium", "Premium membershiip is all the normal paid member", 0.08),
    GOLD("AU", 2, "Gold", "Gold membership is the paid member with VIP class", 0.20);
    //<editor-fold defaultstate="collapsed" desc="Enumerator(MembershipLevel) properties">

    /**
     * MemberShip code
     */
    @CsvBindByName(column = "member_code")
    private final String code;
    /**
     * Seq Code for array
     */
    private final int seq_code;
    /**
     * MemberShip title
     */
    private final String name;
    /**
     * MemberShip Description
     */
    private final String description;
    /**
     * MemberShip Discount Amount
     */
    private final double discount;
    /**
     * MemberShip Values
     */
    private final static MemberShip[] vs = MemberShip.values();

    MemberShip(String code, int seq_code, String name, String description, double discount) {
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

    public double getDiscount() {
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
    public static int length() {
        return 3;
    }

    public static MemberShip getValue(String string) {
        for (MemberShip v : vs) {
            if (Arrays.asList(v.code.toLowerCase(), v.name.toLowerCase()).contains(string.toLowerCase())) {
                return v;
            }
        }
        return null;
    }

    public static MemberShip getValue(Object member_level) {
        if (member_level instanceof MemberShip) {
            return (MemberShip) member_level;
        } else if (member_level instanceof String) {
            return MemberShip.getValue((String) member_level);
        } else {
            return null;
        }
    }
    //</editor-fold>

    public static void main(String[] args) {
        System.out.println(MemberShip.getValue("AU"));
    }

    @Override
    public String getStringCode() {
        return this.getDatabaseCode();
    }

    @Override
    public AbstractEnum setMyValue(Object string) {
        return getValue(string);
    }

    @Override
    public int compare(AbstractEnum x) {
        return this.code.compareTo(((MemberShip) x).code);
    }
}
