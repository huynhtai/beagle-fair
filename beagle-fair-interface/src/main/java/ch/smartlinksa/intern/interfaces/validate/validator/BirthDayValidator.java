package ch.smartlinksa.intern.interfaces.validate.validator;



import ch.smartlinksa.intern.interfaces.util.DateFormat;
import ch.smartlinksa.intern.interfaces.validate.constraint.BirthDayValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDayValidator implements ConstraintValidator<BirthDayValidate, String> {

    private DateFormat dateFormat;
    public void initialize(BirthDayValidate birthDayValidate) {

    }

    public boolean isValid(String inputBirthday, ConstraintValidatorContext constraintValidatorContext) {
        return dateFormat.isFormatDate(inputBirthday);
    }
}
