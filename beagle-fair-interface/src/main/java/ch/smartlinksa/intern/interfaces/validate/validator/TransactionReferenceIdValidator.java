package ch.smartlinksa.intern.interfaces.validate.validator;

import com.springapp.mvc.request.UserRequest;
import com.springapp.mvc.validate.constraint.TransactionReferenceId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionReferenceIdValidator implements ConstraintValidator<TransactionReferenceId, UserRequest> {

    @Override
    public boolean isValid(UserRequest userRequest, ConstraintValidatorContext constraintValidatorContext) {
        return !("".equals(userRequest.getReferenceId()) && "".equals(userRequest.getTransactionId()));
    }

    @Override
    public void initialize(TransactionReferenceId constraintAnnotation) {
    }


}
