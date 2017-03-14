package cc.codehub.newkit.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Li Di on 2017/3/13.
 */
@Controller
public class SystemController {

    @RequestMapping("/")
    public String index() {
        return "/index.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/401")
    public String notAuth() {
        return "/notauthorized.html";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "/accessdenied.html";
    }

    @RequestMapping("/404")
    public String notFound() {
        return "/notfound.html";
    }
}
