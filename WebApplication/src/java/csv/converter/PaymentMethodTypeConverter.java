/*Auto generated code by class AutoGenerateEnumConverter*/
package csv.converter;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import xenum.*;

/**
 *
 * @author Lim sai Keat
 */
public class PaymentMethodTypeConverter extends AbstractBeanField {

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        PaymentMethodType obj = (PaymentMethodType) value;
        if (obj != null) {
            return obj.getCode();
        }
        return "";

    }

    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        return PaymentMethodType.getValue(string);

    }
}
