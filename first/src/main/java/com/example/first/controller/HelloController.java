package com.example.first.controller;

import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("/hello")
    public String sayHello(){
        return helloService.sayHello();
    }
}
