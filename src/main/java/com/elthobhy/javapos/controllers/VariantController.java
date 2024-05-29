package com.elthobhy.javapos.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.elthobhy.javapos.models.Variant;
import com.elthobhy.javapos.services.CategoryService;
import com.elthobhy.javapos.services.VariantService;
import com.elthobhy.javapos.viewmodel.VariantViewModel;

@Controller
@RequestMapping("/variant")
public class VariantController {
    @Autowired
    VariantService vasSvc;
    @Autowired
    CategoryService catSvc;

    private final String apiUrl = "http://localhost:8080/api/variant";
    private RestTemplate restTemp = new RestTemplate();

    @GetMapping("")
    ModelAndView Index() {
        ModelAndView view = new ModelAndView("/variant/index");
        // cara lain
        // try {
        // RequestEntity<Void> req = RequestEntity.get(apiUrl +
        // "/var").accept(MediaType.APPLICATION_JSON).build();
        // ParameterizedTypeReference<VariantViewModel[]> resType = new
        // ParameterizedTypeReference<VariantViewModel[]>() {
        // };

        // ResponseEntity<VariantViewModel[]> apiResponse = restTemp.exchange(req,
        // resType);
        // if (apiResponse.getStatusCode() == HttpStatus.OK) {
        // VariantViewModel[] data = apiResponse.getBody();
        // view.addObject("data", data);
        // } else {
        // throw new Exception(apiResponse.getStatusCode() + ":" +
        // apiResponse.getBody());
        // }
        // } catch (Exception e) {
        // view.addObject("errorMessage", e.getMessage());
        // }
        try {
            ResponseEntity<VariantViewModel[]> apiResponse = restTemp.getForEntity(apiUrl + "/var",
                    VariantViewModel[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                VariantViewModel[] data = apiResponse.getBody();
                view.addObject("data", data);
            } else {
                throw new Exception(apiResponse.getStatusCode() + ":" + apiResponse.getBody());
            }
        } catch (Exception e) {
            // TODO: handle exception
            view.addObject("errorMessage", e.getMessage());
        }
        return view;
    }

    @GetMapping("/{id}")
    ModelAndView Detail(@PathVariable int id) {
        ModelAndView view = new ModelAndView("/variant/detail");
        view.addObject("id", id);
        return view;
    }

    @GetMapping("/add")
    ModelAndView Add() {
        ModelAndView view = new ModelAndView("/variant/add");
        try {
            view.addObject("categories", catSvc.getAll());
            return view;
        } catch (Exception e) {
            return view;
        }
    }

    @PostMapping("/save")
    ModelAndView Save(@ModelAttribute Variant data) throws Exception {
        try {
            ModelAndView view = new ModelAndView("redirect:/variant");
            Variant newVar = vasSvc.create(data);
            if (newVar.getId() > 0) {
                System.out.println("New Variant added");
                return view;
            } else {
                throw new Exception("New Variant cannot be added");
            }
        } catch (Exception e) {
            ModelAndView view = new ModelAndView("/variant/error");
            System.out.println(e.getMessage());
            view.addObject("alertMessage", e.getMessage());
            return view;
        }
    }
}
