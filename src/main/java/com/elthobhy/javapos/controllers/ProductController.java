package com.elthobhy.javapos.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.elthobhy.javapos.services.ProductService;
import com.elthobhy.javapos.models.Product;
import com.elthobhy.javapos.services.CategoryService;
import com.elthobhy.javapos.services.VariantService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService proSvc;
    @Autowired
    CategoryService catSvc;
    @Autowired
    VariantService varSvc;

    @GetMapping("")
    ModelAndView Index() {
        ModelAndView view = new ModelAndView("/product/index");
        try {
            List<Map<String, Object>> data = proSvc.getAllNative();
            view.addObject("data", data);
            return view;
        } catch (Exception e) {
            return view;
        }
    }

    @GetMapping("/{id}")
    ModelAndView Detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/product/detail");
        view.addObject("id", id);
        return view;
    }

    @GetMapping("/add")
    ModelAndView Add() {
        ModelAndView view = new ModelAndView("/product/add");
        try {
            view.addObject("categories", catSvc.getAll());
            view.addObject("variants", varSvc.getAll());
            return view;
        } catch (Exception e) {
            return view;
        }
    }

    @PostMapping("/save")
    ModelAndView Save(@ModelAttribute Product data) throws Exception {
        try {
            ModelAndView view = new ModelAndView("redirect:/product");
            Product newVar = proSvc.create(data);
            if (newVar.getId() > 0) {
                System.out.println("New Product added");
                return view;
            } else {
                throw new Exception("New Product cannot be added");
            }
        } catch (Exception e) {
            ModelAndView view = new ModelAndView("/product/error");
            System.out.println(e.getMessage());
            view.addObject("alertMessage", e.getMessage());
            return view;
        }
    }
}
