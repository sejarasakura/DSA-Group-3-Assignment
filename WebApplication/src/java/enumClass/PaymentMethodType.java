package enumClass;
//<editor-fold defaultstate="collapsed" desc="Imported documents.">
import java.util.Arrays;

//</editor-fold>
public enum PaymentMethodType {
    
    EWALLET("EW", 0, "Ewallet"),
    CASH("CH", 0.01, "Cash"),
    CARD("CD", 0.03, "Credit / Debitcard"),
    GPAY("MP",0, "Mobile Pay");
    //<editor-fold defaultstate="collapsed" desc="Enumerator(PaymentMethodType) properties">
    private final String code;
    private final double charge;
    private final String name;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Enumerator(PaymentMethodType) constructor">
    private PaymentMethodType(String code, double charge, String name) {
        this.code = code;
        this.charge = charge;
        this.name = name;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Enumerator(PaymentMethodType) Getter">
    public String getCode() {
        return code;
    }
    public double getCharge() {
        return charge;
    }
    public String getName() {
        return name;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Enumerator(PaymentMethodType) Convertor">
    public static PaymentMethodType getValue(String string) {
        try {
            if (Arrays.asList(EWALLET.code, EWALLET.name).contains(string)) {
                return EWALLET;
            } else if (Arrays.asList(CARD.code, CARD.name).contains(string)) {
                return CARD;
            } else if (Arrays.asList(CASH.code, CASH.name).contains(string)) {
                return CASH;
            } else if (Arrays.asList(GPAY.code, GPAY.name).contains(string)) {
                return GPAY;
            } else {
                try {return valueOf(string.toUpperCase());} catch (Exception ex) {return null;}
            }
        } catch (Exception e) {
            try {
                return PaymentMethodType.valueOf(string.toUpperCase());
            } catch (Exception ex) {
                return null;
            }
        }
    }
     public static PaymentMethodType getValue(Object pay_method_type){
         if(pay_method_type instanceof PaymentMethodType)
            return (PaymentMethodType)pay_method_type;
        else if(pay_method_type instanceof String)
            return PaymentMethodType.getValue((String)pay_method_type);
        else{
            return null;
        }
     }
    //</editor-fold>
}
