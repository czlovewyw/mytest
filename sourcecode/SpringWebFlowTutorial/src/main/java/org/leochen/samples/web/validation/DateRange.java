package org.leochen.samples.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

/**
 * User: leochen
 * Date: 11-12-15
 * Time: 上午10:52
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface DateRange {
    String message() default "hahahhahahha";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String date();
    String futureDate();

}
