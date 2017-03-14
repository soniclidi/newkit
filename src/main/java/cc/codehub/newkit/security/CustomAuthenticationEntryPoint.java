package cc.codehub.newkit.security;

import cc.codehub.newkit.base.ErrorResponse;
import cc.codehub.newkit.common.ApiError;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ErrorResponse err = new ErrorResponse(ApiError.SESSION_TIMEOUT.getCode(), ApiError.SESSION_TIMEOUT.getMessage());
        PrintWriter out = response.getWriter();
        out.print(err.toJsonString());
        out.flush();
        out.close();

        return;
    }

}
