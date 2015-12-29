package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
import org.fest.assertions.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserController_addUserTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void a(){
//        UserRequest userRequest = prepareUserRequest();
//        Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(prepareUserEntity(userRequest));
//        RestApiResponse<UserResponse> response = userServiceImpl.add(userRequest);
//        UserResponse userResponse = response.getBody();
//        Assertions.assertThat(userRequest.getAddress()).isEqualToIgnoringCase(userResponse.getAddress());
    }

    private User prepareUserEntity(UserRequest userRequest) {
        User user = new User();
        return user;
    }

    private UserRequest prepareUserRequest(){
        UserRequest userRequest = new UserRequest();

        userRequest.setUserName("userNameTest");
        userRequest.setPassword("1234567ajskhdKJH");
        userRequest.setFirstName("Hoa");
        userRequest.setLastName("Phuong");
        userRequest.setBirthday("22/12/1998");
        userRequest.setGender("1");
        userRequest.setAddress("Da Nang");
        userRequest.setPhoneNumber("+763491320380980");

        return userRequest;
    }
}
