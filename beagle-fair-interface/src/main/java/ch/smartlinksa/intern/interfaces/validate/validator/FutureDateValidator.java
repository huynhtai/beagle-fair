package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.interfaces.util.DateFormatUtil;
import ch.smartlinksa.intern.interfaces.validate.constraint.BirthDayValidate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FutureDateValidator implements ConstraintValidator<BirthDayValidate, String> {

    private DateFormatUtil dateFormatUtil;

    public void initialize(BirthDayValidate birthDayValidate) {

    }

    public boolean isValid(String inputBirthday, ConstraintValidatorContext constraintValidatorContext) {
        return dateFormatUtil.isFutureDate(inputBirthday);
    }
}
