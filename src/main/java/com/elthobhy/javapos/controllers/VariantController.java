package com.elthobhy.javapos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/variant")
public class VariantController {
    @GetMapping("")
    ModelAndView Index(){
        return new ModelAndView("/variant/index");
    }
}
