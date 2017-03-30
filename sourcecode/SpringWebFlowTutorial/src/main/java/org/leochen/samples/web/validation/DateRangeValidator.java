package org.leochen.samples.web.validation;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * User: leochen
 * Date: 11-12-15
 * Time: 上午11:07
 */
public class DateRangeValidator implements ConstraintValidator<DateRange,Object>{
    private String dateField;
    private String futureDateField;

    public void initialize(DateRange dateRange) {
        dateField = dateRange.date();
        futureDateField = dateRange.futureDate();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {

        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(dateField,value.getClass());
            Method getDateFieldMethod = propertyDescriptor.getReadMethod();
            Date dateValue = (Date)getDateFieldMethod.invoke(value);

            PropertyDescriptor pd = new PropertyDescriptor(futureDateField,value.getClass());
            Method getFutureDateMethod = pd.getReadMethod();
            Date futureDateValue = (Date)getFutureDateMethod.invoke(value);

            if(dateValue!=null && futureDateValue!=null && futureDateValue.before(dateValue)){
                String messageTemplate = context.getDefaultConstraintMessageTemplate();
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(messageTemplate)
                        .addNode(futureDateField)
                        .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true ;
    }
}
