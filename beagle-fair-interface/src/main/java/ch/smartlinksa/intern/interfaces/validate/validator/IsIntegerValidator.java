package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.interfaces.validate.constraint.IsInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsIntegerValidator implements ConstraintValidator<IsInteger, Number>{

    public void initialize(IsInteger isInteger) {

    }

    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if(number == null){
            return true;
        }else{
            return number instanceof Integer;
        }
    }
}
