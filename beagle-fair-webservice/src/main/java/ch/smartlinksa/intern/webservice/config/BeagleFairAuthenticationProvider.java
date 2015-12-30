package ch.smartlinksa.intern.webservice.config;

import ch.smartlinksa.intern.business.util.EncryptMD5;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class BeagleFairAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserRepository userRepository;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = getUserByUsernameAndPassword(username, password);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER_ROLE"));
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        ((UsernamePasswordAuthenticationToken) auth).setDetails(user);
        return auth;
    }

    private User getUserByUsernameAndPassword(String userName, String password) {
        User user = userRepository.findByUserNameAndPassword(userName, EncryptMD5.convertToMD5(password));
        if (user == null){
            throw new BadCredentialsException(MessageCodeConstant.ERROR_USER_NOT_FOUND);
        }
        return user;
    }

    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}