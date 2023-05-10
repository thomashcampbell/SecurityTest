package com.aotscc.securitytest.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {
    @GetMapping("/helloWorld")
    public String testMessage() throws Exception {
        return "Hello World";
    }
}
