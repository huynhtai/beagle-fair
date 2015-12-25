package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public RestApiResponse add(@RequestBody @Valid UserRequest userRequest){
        System.out.println("UserRequest in Controller: " + userRequest.getFirstName());
        return userService.add(userRequest);
    }

}
