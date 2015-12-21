package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.business.dto.User;

import java.util.List;

public interface ISortList {
    List<User> sortByName(List<User> users);
}
