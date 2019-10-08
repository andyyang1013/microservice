package cn.andyyang1013.microservice.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/base")
public class BaseController {
    @GetMapping()
    public String provider(@RequestParam("consumer") String consumer) {
        return String.format("%s get user service", consumer);
    }
}
