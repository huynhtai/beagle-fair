package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;

import java.util.List;

public interface IUserService {

    RestApiResponse<UserResponse> addUser(UserRequest userRequest);

    User getCurrentUser();
}
