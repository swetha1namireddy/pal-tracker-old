package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    String msg = null;

    public WelcomeController(@Value("${welcome.message}") String msg)
    {
        this.msg = msg;
    }

    @GetMapping("/")
    public Object sayHello() {
        return msg;
    }
}
