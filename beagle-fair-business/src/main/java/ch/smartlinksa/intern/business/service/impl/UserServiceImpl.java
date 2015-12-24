package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.interfaces.request.User;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserRepository userRepository;
    public boolean add(User user) {
        try {
            ch.smartlinksa.intern.dao.entity.User userEntity = convertToUserEntity(user);
            System.out.println("userEntity: " + userEntity.getId() + " " + userEntity.getFirstName());
            userRepository.save(userEntity);
            return  true;
        }catch (Exception e){
            System.out.println("Error message: ");
            e.printStackTrace();
            return false;
        }
    }

    private ch.smartlinksa.intern.dao.entity.User convertToUserEntity(User user){
        ch.smartlinksa.intern.dao.entity.User userEntity = new ch.smartlinksa.intern.dao.entity.User();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setGender(user.getGender());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setAddress(user.getAddress());
        userEntity.setBalance(user.getBalance());
        return userEntity;
    }
}

