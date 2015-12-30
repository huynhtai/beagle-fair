package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.interfaces.validate.constraint.GreaterThanZero;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class GreaterThanZerValidator implements ConstraintValidator<GreaterThanZero, Number>{

    public void initialize(GreaterThanZero greaterThanZero) {
    }

    public boolean isValid(Number inputNumber, ConstraintValidatorContext constraintValidatorContext) {
        if(inputNumber == null) {
            return true;
        }else{
            Integer zero = new Integer(0);
            return (inputNumber.intValue() > zero);
        }
    }

}
