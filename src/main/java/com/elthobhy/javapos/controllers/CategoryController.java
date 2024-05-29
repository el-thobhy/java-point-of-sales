package com.elthobhy.javapos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.elthobhy.javapos.models.Category;
import com.elthobhy.javapos.viewmodel.CategoryViewModel;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//pola uri = {domain}/{controller}/{method/action}/{param}
@Controller
@RequestMapping("/category")
public class CategoryController {

    private final String apiUrl = "http://localhost:8080/api/category";
    private RestTemplate restTemp = new RestTemplate();

    @GetMapping("")
    ModelAndView Index() {
        // add data to view
        ModelAndView view = new ModelAndView("/category/index");
        try {
            ResponseEntity<Category[]> apiResponse = restTemp.getForEntity(apiUrl, Category[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                Category[] data = apiResponse.getBody();
                view.addObject("data", data);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ":" + apiResponse.getBody());
            }

        } catch (Exception e) {
            view.addObject("errorMessage", e.getMessage());
        }
        return view;

        // cara langsung ke api controller
        // try {
        // // get data from api service directly
        // List<Category> data = catSvc.getAll();

        // view.addObject("data", data);
        // return view;
        // } catch (Exception e) {
        // // TODO: handle exception
        // return view;
        // }
        // fill data
        // data.add(new Category());
        // data.get(data.size() - 1).setId(10);
        // data.get(data.size() - 1).setName("Makanan");
        // data.get(data.size() - 1).setDescription("Makanan 2");
        // data.add(new Category());
        // data.get(data.size() - 1).setId(11);
        // data.get(data.size() - 1).setName("Minuman");
        // data.get(data.size() - 1).setDescription("Minuman 2");

    }

    @GetMapping("/{id}")
    ModelAndView Detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/category/detail");
        view.addObject("id", id);
        return view;
    }

    @GetMapping("/add")
    ModelAndView Add() {
        ModelAndView view = new ModelAndView("/category/add");
        Category cat = new Category();
        view.addObject("category", cat);
        return view;
    }

    @PostMapping("/save")
    ModelAndView Save(@ModelAttribute CategoryViewModel data) {
        try {
            ResponseEntity<CategoryViewModel> apiResponse = restTemp.postForEntity(apiUrl, data,
                    CategoryViewModel.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("New Category added");
            } else {
                throw new Exception("New Category cannot be added");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ModelAndView("redirect:/category");
    }
}
