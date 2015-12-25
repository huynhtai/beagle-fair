package ch.smartlinksa.intern.interfaces.validate.validator;


import ch.smartlinksa.intern.interfaces.util.DateFormat;
import ch.smartlinksa.intern.interfaces.validate.constraint.BirthDayValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureDateValidator implements ConstraintValidator<BirthDayValidate, String> {

    private DateFormat dateFormat;
    public void initialize(BirthDayValidate birthDayValidate) {

    }

    public boolean isValid(String inputBirthday, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Date check: " + dateFormat.isFutureDate(inputBirthday));
        return dateFormat.isFutureDate(inputBirthday);
    }
}
