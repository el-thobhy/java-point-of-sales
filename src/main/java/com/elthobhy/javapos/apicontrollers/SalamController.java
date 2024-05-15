package com.elthobhy.javapos.apicontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elthobhy.javapos.models.Salam;

@RestController
@RequestMapping("/api/Salam")
public class SalamController {
    @GetMapping("/{nama}")
    public String Salam(@PathVariable final String nama) {
    	return "Selamat datang, "+ nama;
    }
    @GetMapping("")//request param itu adalah query parameter biasanya pakain ? Salam?nama=variable
    public Salam SalamJuga(@RequestParam(value = "nama", defaultValue = "Manusia") final String nama) {
        return new Salam(13, nama);    	
    }
}
