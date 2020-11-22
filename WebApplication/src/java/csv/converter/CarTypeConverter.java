/*Auto generated code by class AutoGenerateEnumConverter*/
package csv.converter;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import xenum.*;

/**
 *
 * @author Lim sai Keat
 */
public class CarTypeConverter extends AbstractBeanField {

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        CarType obj = (CarType) value;
        return obj.getCode();

    }

    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        return CarType.getValue(string);

    }
}
