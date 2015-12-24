package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.interfaces.request.User;
import ch.smartlinksa.intern.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public boolean add(@RequestBody User user){
        System.out.println("User in Controller: " + user.getFirstName());
        boolean result = userService.add(user);
        return result;
    }

}
