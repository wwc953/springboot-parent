package com.example.service.impl;

import com.example.service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello() {
        return "hello hhhh";
    }
}
