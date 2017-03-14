package cc.codehub.newkit.security;

import cc.codehub.newkit.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails extends User implements UserDetails {

    private List<String> listRoleName;

    public CustomUserDetails(User userInfo, List<String> listRoleName)
    {
        this.setUid(userInfo.getUid());
        this.setUsername(userInfo.getUsername());
        this.setPassword(userInfo.getPassword());
        this.setSalt(userInfo.getSalt());
        this.setType(userInfo.getType());
        this.setStatus(userInfo.getStatus());
        this.setName(userInfo.getName());
        this.setMobile(userInfo.getMobile());
        this.setEmail(userInfo.getEmail());
        this.setIdNo(userInfo.getIdNo());

        this.listRoleName = listRoleName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> listAuthority = new ArrayList<GrantedAuthority>();

        if (listRoleName != null && listRoleName.size() >= 1) {
            for (String roleName : listRoleName) {
                listAuthority.add(new SimpleGrantedAuthority(roleName));
            }
        }

        return listAuthority;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == 0;
    }

}
