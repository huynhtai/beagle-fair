package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.interfaces.validate.constraint.Gender;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    public void initialize(Gender gender) {
    }

    public boolean isValid(String inputGender, ConstraintValidatorContext constraintValidatorContext) {
        ch.smartlinksa.intern.dao.constant.Gender gender = ch.smartlinksa.intern.dao.constant.Gender.valueOf(inputGender);
        if(isEmpty(inputGender)) {
            return true;
        }
        return gender != null ;
    }
    private boolean isEmpty(String str){
        return  "".equals(str);
    }
}
