package ch.smartlinksa.intern.business.util;

import ch.smartlinksa.intern.dao.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtil {
    public static String getCurrentUserId(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return user.getId();
    }
}
