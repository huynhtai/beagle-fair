package ch.smartlinksa.intern.interfaces.validate.constraint;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.validator.FutureDateValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FutureDateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDate {

    String message() default MessageCodeConstant.ERROR_INVALID_BIRTHDAY_FUTURE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
