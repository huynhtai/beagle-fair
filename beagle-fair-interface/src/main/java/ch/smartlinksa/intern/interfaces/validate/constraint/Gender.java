package ch.smartlinksa.intern.interfaces.validate.constraint;




import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.validator.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GenderValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {

    String message() default MessageCodeConstant.ERROR_GENDER;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
