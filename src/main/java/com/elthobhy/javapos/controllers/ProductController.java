package com.elthobhy.javapos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.elthobhy.javapos.viewmodel.CategoryViewModel;
import com.elthobhy.javapos.viewmodel.ProductViewModel;
import com.elthobhy.javapos.viewmodel.VariantViewModel;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final String apiVar = "http://localhost:8080/api/variant";
    private final String apiCat = "http://localhost:8080/api/category";
    private final String apiPro = "http://localhost:8080/api/product";
    private final RestTemplate restTemp = new RestTemplate();

    @GetMapping("")
    ModelAndView Index() {
        ModelAndView view = new ModelAndView("/product/index");
        try {
            ResponseEntity<ProductViewModel[]> apiResponse = restTemp.getForEntity(apiPro, ProductViewModel[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                ProductViewModel[] data = apiResponse.getBody();
                view.addObject("data", data);
            } else {
                throw new Exception("Fail to get Data");
            }
        } catch (Exception e) {
            view.addObject("errorMessage", e.getMessage());
        }
        return view;
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
            ResponseEntity<VariantViewModel[]> varRes = restTemp.getForEntity(apiVar, VariantViewModel[].class);
            ResponseEntity<CategoryViewModel[]> catRes = restTemp.getForEntity(apiCat, CategoryViewModel[].class);
            if (catRes.getStatusCode() == HttpStatus.OK) {
                view.addObject("categories", catRes.getBody());
            } else {
                throw new Exception("Fail Get Data category");
            }
            if (varRes.getStatusCode() == HttpStatus.OK) {
                view.addObject("variants", varRes.getBody());
            } else {
                throw new Exception("Fail Get Data variant");
            }
        } catch (Exception e) {
            view.addObject("errorMessage", e.getMessage());
        }
        return view;
    }

    @PostMapping("/save")
    ModelAndView Save(@ModelAttribute ProductViewModel data) throws Exception {
        ModelAndView view = new ModelAndView("redirect:/product");
        try {
            ResponseEntity<ProductViewModel> apiRes = restTemp.postForEntity(apiPro, data, ProductViewModel.class);
            if (apiRes.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("New Product added");
            } else {
                throw new Exception("New Product cannot be added");
            }
        } catch (Exception e) {
            view.addObject("alertMessage", e.getMessage());
        }
        return view;
    }
}
