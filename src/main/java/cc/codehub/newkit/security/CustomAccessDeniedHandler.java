package cc.codehub.newkit.security;

import cc.codehub.newkit.base.ErrorResponse;
import cc.codehub.newkit.common.ApiError;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private String errorPage = "/403";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if (isAjax) {
            response.setContentType("application/json;charset=utf-8");
            ErrorResponse err = new ErrorResponse(ApiError.ACCESS_DENIED.getCode(), ApiError.ACCESS_DENIED.getMessage());
            PrintWriter out = response.getWriter();
            out.print(err.toJsonString());
            out.flush();
            out.close();

            return;
        } else if (errorPage != null) {
            request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        }
    }
}