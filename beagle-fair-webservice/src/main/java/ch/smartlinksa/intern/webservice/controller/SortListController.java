package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.dto.User;
import ch.smartlinksa.intern.business.service.ISortList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SortListController {
    @Autowired
    ISortList sortService ;

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public List<User> sort(@RequestBody List<User> users){
        return sortService.sortByName(users);
    }
}
