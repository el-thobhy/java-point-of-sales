package com.elthobhy.javapos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elthobhy.javapost.models.Salam;

@RestController
public class HomeController {
    @GetMapping("/")
    public String Index(){
        return "Hello World!";
    }

    @GetMapping("/Salam/{nama}")
    public String Salam(@PathVariable final String nama) {
    	return "Selamat datang"+ nama;
    }
    @GetMapping("/Salam")
    public Salam SalamJuga(@RequestParam(value = "nama", defaultValue = "Manusia") final String nama) {
        return new Salam(13, nama);    	
    }
}