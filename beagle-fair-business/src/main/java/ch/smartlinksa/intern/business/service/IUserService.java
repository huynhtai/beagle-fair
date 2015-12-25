package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;

public interface IUserService {
    public RestApiResponse<?> add(UserRequest userRequest);
}
