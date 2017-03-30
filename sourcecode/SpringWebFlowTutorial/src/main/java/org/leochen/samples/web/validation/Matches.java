package org.leochen.samples.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: leochen
 * Date: 11-12-8
 * Time: 下午11:31
 */

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public @interface Matches {
    String message() default "{constraint.not.matches}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();
    String verifyField();
}
