package ch.smartlinksa.intern.interfaces.validate.constraint;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.validator.BirthDayValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDayValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDayValidate {
    String message() default MessageCodeConstant.ERROR_INVALID_FORMAT_BIRTHDAY;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
