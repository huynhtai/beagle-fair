package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    public String add(UserRequest userRequest) {
        try {
            ch.smartlinksa.intern.dao.entity.User userEntity = convertToUserEntity(userRequest);
            System.out.println("userEntity: " + userEntity.getId() + " " + userEntity.getFirstName());
            userRepository.save(userEntity);
            return  "Add user successfully";
        }catch (Exception e){
            System.out.println("Error message: ");
            e.printStackTrace();
            return "Have some errors";
        }
    }

    private ch.smartlinksa.intern.dao.entity.User convertToUserEntity(UserRequest userRequest){
        ch.smartlinksa.intern.dao.entity.User userEntity = new ch.smartlinksa.intern.dao.entity.User();
        userEntity.setUserName(userRequest.getUserName());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());

        // Birthday
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date bithday = df.parse(userRequest.getBirthday());
            userEntity.setBirthday(bithday);
            System.out.println(bithday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userEntity.setGender(userRequest.getGender());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());
        return userEntity;
    }
}

