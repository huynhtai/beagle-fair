package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.service.IReverseString;
import org.springframework.stereotype.Service;

@Service
public class ReverseStringServiceImpl implements IReverseString{

    public String reverse(String string) {
        StringBuffer result = new StringBuffer(string);
        return result.reverse().toString();
    }
}
