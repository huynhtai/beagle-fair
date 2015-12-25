package ch.smartlinksa.intern.interfaces.validate.constraint;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.validator.ExistedUserValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistedUserValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistedUser {
    String message() default MessageCodeConstant.ERROR_EXIST_USERNAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
