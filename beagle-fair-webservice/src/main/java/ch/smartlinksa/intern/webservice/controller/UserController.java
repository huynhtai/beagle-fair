package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RestApiResponse<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @RequestMapping(value = "/listUser", method = RequestMethod.GET)
    public RestApiResponse<List<UserResponse>> listUser(){
        return new RestApiResponse<List<UserResponse>>(null);
    }

}
