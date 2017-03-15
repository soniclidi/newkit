package cc.codehub.newkit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NewkitController {

    @RequestMapping("/hello")
    public String hello() {
        return  "hello world!";
    }

    @RequestMapping("/high")
    public String high() {
        return  "high mountain, need auth!";
    }
}
