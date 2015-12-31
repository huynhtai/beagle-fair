package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.util.EncryptMD5;
import ch.smartlinksa.intern.dao.constant.Gender;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.constant.FormatConstant;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
import ch.smartlinksa.intern.interfaces.util.DateFormatUtil;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserService_addUserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void shouldGetUserResponseLikeUserRequest(){
        UserRequest userRequest = prepareUserRequest();
        Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(prepareUserEntity(userRequest));
        RestApiResponse<UserResponse> response = userServiceImpl.addUser(userRequest);
        UserResponse userResponse = response.getBody();
        Assertions.assertThat(userResponse.getId()).isNotNull();
        Assertions.assertThat(userRequest.getAddress()).isEqualToIgnoringCase(userResponse.getAddress());
        Assertions.assertThat(userRequest.getFirstName()).isEqualToIgnoringCase(userResponse.getFirstName());
        Assertions.assertThat(userRequest.getLastName()).isEqualToIgnoringCase(userResponse.getLastName());
        Assertions.assertThat(userRequest.getPhoneNumber()).isEqualToIgnoringCase(userResponse.getPhoneNumber());
        Assertions.assertThat(userRequest.getUserName()).isEqualToIgnoringCase(userResponse.getUserName());
        Assertions.assertThat(Gender.valueOfKey(userRequest.getGender())).isEqualTo(Gender.valueOfKey(userResponse.getGender()));
    }

    private User prepareUserEntity(UserRequest userRequest) {
        User userEntity = new User();

        userEntity.setUserName(userRequest.getUserName());
        userEntity.setPassword(EncryptMD5.convertToMD5(userRequest.getPassword()));
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setBirthday(DateFormatUtil.convertStringToDate(userRequest.getBirthday(), FormatConstant.BIRTHDAY_FORMAT));
        userEntity.setGender(Gender.FEMALE);
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setAddress(userRequest.getAddress());

        return userEntity;
    }

    private UserRequest prepareUserRequest(){
        UserRequest userRequest = new UserRequest();

        userRequest.setUserName("userNameTest");
        userRequest.setPassword("1234567ajskhdKJH");
        userRequest.setFirstName("Hoa");
        userRequest.setLastName("Phuong");
        userRequest.setBirthday("22/12/1998");
        userRequest.setGender("0");
        userRequest.setAddress("Da Nang");
        userRequest.setPhoneNumber("+763491320380980");

        return userRequest;
    }
}
