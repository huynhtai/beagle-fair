package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.dao.constant.Gender;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    public String add(UserRequest userRequest) {
        try {
            ch.smartlinksa.intern.dao.entity.User userEntity = convertToUserEntity(userRequest);
            userRepository.save(userEntity);
            return  "Add user successfully";
        }catch (Exception e){
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
        userEntity.setBirthday(convertStringToDate(userRequest.getBirthday(), "dd/MM/yyyy"));
        userEntity.setGender(Gender.valueOf(convertGender(userRequest.getGender())));
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());
        return userEntity;
    }

<<<<<<< HEAD
        // Birthday
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            Date bithday = df.parse(userRequest.getBirthday());
//            userEntity.setBirthday(bithday);
//            System.out.println(bithday);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        userEntity.setGender(Gender.valueOf(userRequest.getGender()));
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());
        return userEntity;
=======
    private Date convertStringToDate(String dateString, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private  String convertGender(String gender){
        return "1".equalsIgnoreCase(gender)?"MALE":"FEMALE";
>>>>>>> 69b02483cfe33cec04aa17ae0e1cc61597915c47
    }
}

