package ch.smartlinksa.intern.interfaces.validate.constraint;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.validator.GreaterThanZeroValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GreaterThanZeroValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GreaterThanZero {

    String message() default MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
