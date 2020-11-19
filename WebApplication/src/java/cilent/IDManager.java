/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

import adt.ArrList;
import com.opencsv.bean.*;
import entity.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ITSUKA KOTORI
 */
public class IDManager extends AbstractEntity {

    @CsvBindByName
    private String className;

    @CsvBindByName
    private String formatString;

    @CsvBindByName
    private String dataType;

    @CsvBindByName
    private String identityName;

    @CsvBindByName
    private int lastSeqNumber;

    public IDManager(String className, String formatString, String dataType, int lastSeqNumber, String identityName) {
        this.className = className;
        this.formatString = formatString;
        this.dataType = dataType;
        this.lastSeqNumber = lastSeqNumber;
        this.identityName = identityName;
    }

    public IDManager() {
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFormatString() {
        return formatString;
    }

    public void setFormatString(String formatString) {
        this.formatString = formatString;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getLastSeqNumber() {
        return lastSeqNumber;
    }

    public void setLastSeqNumber(int lastSeqNumber) {
        this.lastSeqNumber = lastSeqNumber;
    }

    @Override
    public boolean isNotNull() {
        if (this.className == null) {
            return false;
        }
        return !this.className.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean id_equals(Object obj) {
        return this.className.equals(((IDManager) obj).className);
    }

    @Override
    public String toString() {
        return "IDManager{" + "className=" + className + ", formatString=" + formatString + ", dataType=" + dataType + ", identityName=" + identityName + ", lastSeqNumber=" + lastSeqNumber + '}';
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }

    public static Object generateId(AbstractEntity ref) {
        return generateId(ref, false);
    }

    public static Object generateId(AbstractEntity ref, boolean update) {
        String str;
        Iterator obj = AbstractEntity.readDataFormCsv(new IDManager());
        ArrList<IDManager> datas = (obj == null) ? new ArrList() : new ArrList(obj);
        boolean job = false;
        for (int i = 0; i < datas.size() || !job; i++) {
            if (datas.get(i).className.equals(ref.getClass().getName())) {
                try {
                    str = String.format(datas.get(i).formatString, datas.get(i).lastSeqNumber);
                    Object r;
                    if (datas.get(i).dataType.equals("java.lang.Integer")) {
                        r = Integer.parseInt(str);
                    } else {
                        r = str;
                    }
                    if (update) {
                        datas.get(i).lastSeqNumber++;
                        AbstractEntity.reWriteAllDataToCsv(datas);
                    }
                    return r;
                } catch (SecurityException | IllegalArgumentException ex) {
                    Logger.getLogger(IDManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(generateId(new Customer()));
        System.out.println(generateId(new Driver()));
        System.out.println(generateId(new Booking()));
    }

    public static void main2(String[] args) {
        ArrList<IDManager> im = new ArrList();
        im.add(new IDManager(Customer.class.getName(), "C%05d", String.class.getName(), 1, "user_id"));
        im.add(new IDManager(Driver.class.getName(), "D%05d", String.class.getName(), 1, "user_id"));
        im.add(new IDManager(Admin.class.getName(), "A%05d", String.class.getName(), 1, "user_id"));
        im.add(new IDManager(Plate.class.getName(), "P%05d", String.class.getName(), 1, "plate_id"));
        im.add(new IDManager(Withdraw.class.getName(), "W%09d", String.class.getName(), 1, "withdraw_id"));
        im.add(new IDManager(Payment.class.getName(), "P%09d", String.class.getName(), 1, "paymentNumber"));
        im.add(new IDManager(Booking.class.getName(), "B%09d", String.class.getName(), 1, "booking_id"));
        im.add(new IDManager(Mapping.class.getName(), "M%09d", String.class.getName(), 1, "map_id"));
        im.add(new IDManager(Chats.class.getName(), "CS-%09d", String.class.getName(), 1, "chats_id"));
        im.add(new IDManager(Chat.class.getName(), "CD-%09d", String.class.getName(), 1, "chat_details_id"));
        AbstractEntity.addDataToCsv(im);
    }
}
