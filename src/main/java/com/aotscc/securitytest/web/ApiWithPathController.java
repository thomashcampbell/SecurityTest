package com.aotscc.securitytest.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class ApiWithPathController {
    @GetMapping("/helloWorld")
    public String testMessage() throws Exception {
        return "Hello World";
    }
}
