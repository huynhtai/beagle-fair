package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.validate.constraint.ExistedUser;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistedUserValidator implements ConstraintValidator<ExistedUser, String>{

    @Autowired
    private UserRepository userRepository;
    public void initialize(ExistedUser existedUser) {

    }

    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return isExistUser(userName);
    }
    private boolean isExistUser(String userName){
        List<User> users = userRepository.findByUserName(userName);
        if(users != null && users.size() != 0){
            return false;
        }else{
            return true;
        }
    }
}
