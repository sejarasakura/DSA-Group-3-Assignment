/*Auto generated code by class AutoGenerateEnumConverter*/
package csv.converter;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import xenum.*;

/**
 *
 * @author Lim sai Keat
 */
public class PaymentStatusConverter extends AbstractBeanField {

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        PaymentStatus obj = (PaymentStatus) value;
        return obj.getStringCode();

    }

    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        return PaymentStatus.getValue(string);

    }
}
