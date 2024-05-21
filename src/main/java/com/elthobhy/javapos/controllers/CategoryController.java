package com.elthobhy.javapos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.elthobhy.javapos.models.Category;

@Controller
@RequestMapping("/category")
public class CategoryController {
    
    @GetMapping("")
    ModelAndView Index(){
        List<Category> data = new ArrayList<Category>();
        // fill data
        data.add(new Category());
        data.get(data.size()-1).setId(10);
        data.get(data.size()-1).setName("Makanan");
        data.get(data.size()-1).setDescription("Makanan 2");
        data.add(new Category());
        data.get(data.size()-1).setId(11);
        data.get(data.size()-1).setName("Minuman");
        data.get(data.size()-1).setDescription("Minuman 2");

        //add data to view
        ModelAndView view = new ModelAndView("/category/index");
        view.addObject("data", data);
        return view;
    }

    @GetMapping("/{id}")
    ModelAndView Detail(@PathVariable int id){
        ModelAndView view = new ModelAndView("/category/detail");
        view.addObject("id", id);
        return view;
    }
    @GetMapping("/add")
    ModelAndView Add(){
        ModelAndView view = new ModelAndView("/category/add");
        Category cat = new Category();
        view.addObject("category", cat);
        return view;
    }

    @PostMapping("/save")
    ModelAndView Save(@ModelAttribute Category data){
        return new ModelAndView("redirect:/category");
    }
}
