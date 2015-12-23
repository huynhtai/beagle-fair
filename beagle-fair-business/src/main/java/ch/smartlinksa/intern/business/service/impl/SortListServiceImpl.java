package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.dto.User;
import ch.smartlinksa.intern.business.exception.UnformatJsonListException;
import ch.smartlinksa.intern.business.service.ISortList;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SortListServiceImpl implements ISortList{
    public List sortByName(List<User> users){
            Collections.sort(users, new Comparator<User>() {
                public int compare(User p1, User p2) {
                    return p1.getName().compareTo(p2.getName());
                }
            });

        return users;
    }
}
