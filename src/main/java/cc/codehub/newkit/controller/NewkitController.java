package cc.codehub.newkit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Li Di on 2017/3/13.
 */
@RestController
public class NewkitController {

    @RequestMapping("/hello")
    public String hello() {
        return  "hello world!";
    }
}
