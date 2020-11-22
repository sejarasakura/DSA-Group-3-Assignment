/*Auto generated code by class AutoGenerateEnumConverter*/
package csv.converter;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import xenum.*;

/**
 *
 * @author Lim sai Keat
 */
public class MemberShipConverter extends AbstractBeanField {

    @Override
    protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        MemberShip obj = (MemberShip) value;
        if (obj != null) {
            return obj.getDatabaseCode();
        }
        return "";

    }

    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        return MemberShip.getValue(string);

    }
}
