package org.leochen.samples.web.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * User: leochen
 * Date: 11-12-8
 * Time: 下午11:39
 */
public class MatchesValidator implements ConstraintValidator<Matches,Object>{
    private String field;
    private String verifyField;

    public void initialize(Matches matches) {
        this.field = matches.field();
        this.verifyField = matches.verifyField();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String fieldValue= BeanUtils.getProperty(value,field);
            String verifyFieldValue = BeanUtils.getProperty(value,verifyField);
            boolean valid = (fieldValue == null) && (verifyFieldValue == null);
            if(valid){
                return true;
            }

            boolean match = (fieldValue!=null) && fieldValue.equals(verifyFieldValue);
            if(!match){
                String messageTemplate = context.getDefaultConstraintMessageTemplate();
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(messageTemplate)
                        .addNode(verifyField)
                        .addConstraintViolation();
            }
            return match;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }
}
