/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cilent;

import adt.XArrayList;
import entity.AbstractEntity;
import java.lang.reflect.Field;

/**
 *
 * @author Lim Sai Keat
 * @param <T>
 */
public class FieldSeperator<T extends AbstractEntity> {

    private XArrayList<Field> fields;
    private T x;

    public FieldSeperator() {
        Class cls = x.getClass();
        fields = new XArrayList(cls.getDeclaredFields());
        Class<?> current = cls;
        while (current.getSuperclass() != null) {
            current = current.getSuperclass();
            fields.addAll(current.getDeclaredFields());
        }
    }

    public XArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(XArrayList<Field> fields) {
        this.fields = fields;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }
}
