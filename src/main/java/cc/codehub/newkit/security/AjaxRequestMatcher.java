package cc.codehub.newkit.security;

import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.http.HttpServletRequest;


public class AjaxRequestMatcher implements RequestMatcher {
    @Override
    public boolean matches(HttpServletRequest request) {
        request.getServletPath();

        boolean isMatcherAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ||
                request.getHeader("Accept") != null && request.getHeader("Accept").contains("application/json");

        return isMatcherAjax;
    }
}
