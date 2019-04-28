package masao999.springbootsample.controller;

import masao999.springbootsample.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(path = "/hello")
    public String hello() {
        return "hello";
    }
}
