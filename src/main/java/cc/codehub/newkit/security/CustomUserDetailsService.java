package cc.codehub.newkit.security;

import cc.codehub.newkit.model.User;
import cc.codehub.newkit.service.AccessAndRoleService;
import cc.codehub.newkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessAndRoleService accessAndRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userService.getUserByUsernameOrMobileOrEmail(username, username, username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        List<String> listRoleName = accessAndRoleService.getRoleNamesByUser(userInfo);
        CustomUserDetails CustomUserDetails = new CustomUserDetails(userInfo, listRoleName);

        return CustomUserDetails;
    }

}