package cc.codehub.newkit.security;

import cc.codehub.newkit.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String)authentication.getCredentials();

        CustomUserDetails customUserDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername(username);
        if (customUserDetails == null || customUserDetails.getUsername() == null) {
            throw new BadCredentialsException("Username not found.");
        }
        if (!customUserDetails.getPassword().equals(PasswordEncoder.encryptPassword(password, customUserDetails.getSalt()))) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> listAuthority = customUserDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(customUserDetails, password, listAuthority);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}