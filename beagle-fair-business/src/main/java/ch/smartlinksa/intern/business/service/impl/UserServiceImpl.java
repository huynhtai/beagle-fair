package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.dao.constant.Gender;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.interfaces.constant.FormatConstant;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
import ch.smartlinksa.intern.interfaces.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ch.smartlinksa.intern.business.util.EncryptMD5;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public RestApiResponse<UserResponse> addUser(UserRequest userRequest) {
        User userEntity = convertToUserEntity(userRequest);
        userEntity = userRepository.save(userEntity);
        return sendResponseWhenAddUserSuccessfully(userEntity, userRequest);
    }

    private  RestApiResponse<UserResponse> sendResponseWhenAddUserSuccessfully(User userEntity, UserRequest userRequest){
        RestApiResponse<UserResponse> addUserResponse = new RestApiResponse<UserResponse>();
        addUserResponse.setBody(convertUserEntityToUserResponse(userEntity, userRequest));
        return addUserResponse;
    }

    private  UserResponse convertUserEntityToUserResponse(User userEntity, UserRequest userRequest){
        UserResponse userResponse = new UserResponse();

        userResponse.setId(userEntity.getId());
        userResponse.setUserName(userRequest.getUserName());
        userResponse.setPhoneNumber(userRequest.getPhoneNumber());
        userResponse.setAddress(userRequest.getAddress());
        userResponse.setBirthday(userRequest.getBirthday());
        userResponse.setFirstName(userRequest.getFirstName());
        userResponse.setLastName(userRequest.getLastName());
        userResponse.setGender(userRequest.getGender());

        return userResponse;
    }

    private User convertToUserEntity(UserRequest userRequest){
        User userEntity = new User();

        userEntity.setUserName(userRequest.getUserName());
        userEntity.setPassword(EncryptMD5.convertToMD5(userRequest.getPassword()));
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setBirthday(DateFormatUtil.convertStringToDate(userRequest.getBirthday(), FormatConstant.BIRTHDAY_FORMAT));
        userEntity.setGender(Gender.valueOfKey(userRequest.getGender()));
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());

        return userEntity;
    }

}

