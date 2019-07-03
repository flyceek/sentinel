package com.alibaba.csp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("morning")
    public String morning(){
        return "hello, good morning , i am paranora. the value .";
    }

    @RequestMapping("afternoon")
    public String afternoon(){
        return "hello, good afternoon, i am paranora. the value .";
    }

}
