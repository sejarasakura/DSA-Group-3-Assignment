/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

import adt.ArrList;
import com.opencsv.bean.CsvBindByName;
import entity.AbstractEntity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author ITSUKA KOTORI
 * @param <T>
 */
public class FieldSeperator<T extends AbstractEntity> {

    private ArrList<Field> fields;
    private T x;
    
    public FieldSeperator() {
        Class cls = x.getClass();
        fields = new ArrList(cls.getDeclaredFields());
        Class<?> current = cls;
        while (current.getSuperclass() != null) {
            current = current.getSuperclass();
            fields.addAll(current.getDeclaredFields());
        }
    }

    public ArrList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrList<Field> fields) {
        this.fields = fields;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
    
    
    
}
