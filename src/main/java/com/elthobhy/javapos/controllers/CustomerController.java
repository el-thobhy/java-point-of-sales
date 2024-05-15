package com.elthobhy.javapos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("")
    ModelAndView Index(){
        return new ModelAndView("/customer/index");
    }
}
