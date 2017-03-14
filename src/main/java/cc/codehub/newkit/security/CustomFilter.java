package cc.codehub.newkit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


@Component
public class CustomFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private CustomFilterInvocationSecurityMetadataSource customMetadataSource;

    @Autowired
    @Qualifier("customAccessDecisionManager")
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager){
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public Class<? extends Object> getSecureObjectClass(){
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource(){
        return this.customMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation( request, response, chain );
        invoke(fi);
    }

    public void destroy(){
        System.out.println("filter=============结束");
    }

    public void invoke( FilterInvocation fi ) throws IOException, ServletException {
        System.out.println("filter=============开始");
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }






}