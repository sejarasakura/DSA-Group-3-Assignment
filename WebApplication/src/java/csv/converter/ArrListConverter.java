/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv.converter;

import adt.XArrayList;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 *
 * @author Lim sai Keat
 */
public class ArrListConverter extends AbstractBeanField {

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        XArrayList<String> o = (XArrayList<String>) value;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (o.size() - 1); i++) {
            sb.append(o.get(i)).append("%");
        }
        sb.append(o.get(o.size() - 1));
        return sb.toString();
    }

    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (!string.contains("%")) {
            XArrayList<String> o = new XArrayList<String>();
            o.add(string);
            return o;
        }
        String[] split = string.split("%");
        XArrayList<String> o = new XArrayList<String>(split);
        return o;
    }
}
