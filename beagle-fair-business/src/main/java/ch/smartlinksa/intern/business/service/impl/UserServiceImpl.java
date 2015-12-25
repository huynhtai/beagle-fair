package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.dao.constant.Gender;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponseHeaders;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
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
    public RestApiResponse<?> add(UserRequest userRequest) throws IllegalFormatException{
        try {
            User userEntity = convertToUserEntity(userRequest);
            System.out.println("userEntity: " + userEntity.getId() + " " + userEntity.getFirstName());
            userRepository.save(userEntity);
            return makeAddUserResponse(userEntity);
        }catch (IllegalFormatException e) {
            throw e;
        }
    }
    private  RestApiResponse<?> makeAddUserResponse(User userEntity){
        RestApiResponse<UserResponse> addUserResponse = new RestApiResponse<UserResponse>();
        addUserResponse.setBody(convertUserEntityToUserResponse(userEntity));
        return addUserResponse;
    }

    private  UserResponse convertUserEntityToUserResponse(User userEntity){
            UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUserName(userEntity.getUserName());
        userResponse.setPhoneNumber(userEntity.getPhoneNumber());
        userResponse.setAddress(userEntity.getAddress());
        userResponse.setBirthday(userEntity.getBirthday().toString());
        userResponse.setFirstName(userEntity.getFirstName());
        userResponse.setLastName(userEntity.getLastName());
        return userResponse;
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

//        userEntity.setGender(Gender.valueOf(userRequest.getGender()));
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());
        return userEntity;
    }
}

