package ch.smartlinksa.intern.interfaces.validate.constraint;


import com.springapp.mvc.constant.MessageCodeConstant;
import com.springapp.mvc.validate.validator.GenderValidator;

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
