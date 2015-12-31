package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;

public interface IUserService {

    RestApiResponse<UserResponse> addUser(UserRequest userRequest);

}
