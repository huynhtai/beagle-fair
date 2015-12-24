package ch.smartlinksa.intern.interfaces.validate.constraint;


import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.validator.TransactionReferenceIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TransactionReferenceIdValidator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionReferenceId {

    String message() default MessageCodeConstant.ERROR_TRANSACTION_REFERENCE_ID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
