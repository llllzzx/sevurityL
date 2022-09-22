package com.lll.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('sys:dept:list')")
    @PreAuthorize("@ex.hasAuthority('sys:dept:list')")
    public String hello(){
        return "hello";
    }
    System.out.println("test");

}
