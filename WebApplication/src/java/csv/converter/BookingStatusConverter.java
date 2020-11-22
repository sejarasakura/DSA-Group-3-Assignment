/*Auto generated code by class AutoGenerateEnumConverter*/
package csv.converter;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import xenum.*;

/**
 *
 * @author Lim sai Keat
 */
public class BookingStatusConverter extends AbstractBeanField {

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        BookingStatus obj = (BookingStatus) value;
        return obj.getCode();

    }

    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        return BookingStatus.getValue(string);

    }
}
