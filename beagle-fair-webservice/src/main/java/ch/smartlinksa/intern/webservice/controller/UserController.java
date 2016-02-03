package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.service.IUserService;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserService userService;


    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView defaultPage(HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        model.addObject("title", "PAGE HOME");
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        model.addObject("title", "LOGIN PAGE");
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RestApiResponse<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @RequestMapping(value = "/listUser", method = RequestMethod.GET)
    public @ResponseBody RestApiResponse<List<UserResponse>> listUser(){
        return new RestApiResponse<List<UserResponse>>(null);
    }

}
