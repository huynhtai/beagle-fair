package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.interfaces.validate.constraint.Gender;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    private static final List<String> GENDER_SUPPORTED = Arrays.asList("0","1");

    public void initialize(Gender gender) {
    }

    public boolean isValid(String inputGender, ConstraintValidatorContext constraintValidatorContext) {
        return  !"".equals(inputGender) ? GENDER_SUPPORTED.contains(inputGender) : true;
    }
}
