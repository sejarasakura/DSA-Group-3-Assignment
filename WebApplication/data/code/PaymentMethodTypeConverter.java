

/*Auto generated code by class AutoGenerateEnumConverter*/
package csv.converter;
import adt.*;
import xenum.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
/**
 *
 * @author Lim sai Keat
 */
public class PaymentMethodTypeConverter extends AbstractBeanField {


	@Override
	protected String convertToWrite(Object value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		PaymentMethodType obj = (PaymentMethodType) value;
		return obj.getCode();
		
	}

	@Override
	protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		
		return PaymentMethodType.valueOf(string);
		
	}
}


