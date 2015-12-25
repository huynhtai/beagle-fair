package ch.smartlinksa.intern.interfaces.validate.validator;

import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.validate.constraint.ExistUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistUserValidator implements ConstraintValidator<ExistUser, String>{

    @Autowired
    private UserRepository userRepository;
    public void initialize(ExistUser existUser) {

    }

    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return isExistUser(userName);
    }
    private boolean isExistUser(String userName){
//        List<User> users = userRepository.findByUserName(userName);
//        if(users != null && users.size() != 0){
//            return false;
//        }else{
//            return true;
//        }
        return true;
    }
}
