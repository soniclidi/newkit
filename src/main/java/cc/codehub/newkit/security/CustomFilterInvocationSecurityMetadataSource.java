package cc.codehub.newkit.security;

import cc.codehub.newkit.model.Access;
import cc.codehub.newkit.service.AccessAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;


@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> accessRoleMap = null;

    private AntPathMatcher urlMatcher = new AntPathMatcher();

    @Autowired
    private AccessAndRoleService accessAndRoleService;


    private void loadAccessRoleMap() {
        accessRoleMap = new HashMap<String, Collection<ConfigAttribute>>();

        List<Access> access = accessAndRoleService.getAccessList();
        for (Access a : access){
            List<String> roleNames = accessAndRoleService.getRoleNamesByAccessId(a.getAccessId());
            Collection<ConfigAttribute> value = new ArrayList<ConfigAttribute>();

            for (String roleName : roleNames){
                ConfigAttribute ca = new SecurityConfig(roleName);
                value.add(ca);
            }

            if (roleNames.size() == 0){
                ConfigAttribute ca = new SecurityConfig("NONE");
                value.add(ca);
            }
            accessRoleMap.put(a.getUrl(), value);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (accessRoleMap == null){
            loadAccessRoleMap();
        }

        FilterInvocation requestFi = (FilterInvocation)object;
        String requestUrl = requestFi.getRequestUrl();
        int firstQuestionMarkIndex = requestUrl.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
        }

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : accessRoleMap.entrySet()) {
            if (urlMatcher.match(entry.getKey(), requestUrl)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}