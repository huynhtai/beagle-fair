package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.service.IReverseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReverseStringController {

    @Autowired
    private IReverseString reverseService;

    @RequestMapping(value = "/reverseString", method = RequestMethod.POST)
    public String reverseString(@RequestBody String string){
        return reverseService.reverse(string);
    }
}
