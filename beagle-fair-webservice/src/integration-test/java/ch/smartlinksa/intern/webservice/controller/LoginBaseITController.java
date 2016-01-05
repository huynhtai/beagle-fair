package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.util.EncryptMD5;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import java.util.ArrayList;
import java.util.List;

public class LoginBaseITController extends BaseITController {

    protected static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Autowired
    private UserRepository userRepository;

    protected MockHttpSession getSession() {
        UsernamePasswordAuthenticationToken authentication = getAuthentication("tranductrinh", "123456aA");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SEC_CONTEXT_ATTR, new MockSecurityContext(authentication));
        return session;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String username, String password) {
        User user = userRepository.findByUserNameAndPassword(username, EncryptMD5.convertToMD5(password));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER_ROLE"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        ((UsernamePasswordAuthenticationToken) auth).setDetails(user);

        return auth;
    }

    public static class MockSecurityContext implements SecurityContext {

        private static final long serialVersionUID = -1386535243513362694L;

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return this.authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }

}
