package com.elthobhy.javapos.controllers;

// import java.util.List;
// import java.util.Map;

// import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.RequestEntity;
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
import com.elthobhy.javapos.viewmodel.VariantViewModel;

@Controller
@RequestMapping("/variant")
public class VariantController {

    private final String apiUrl = "http://localhost:8080/api/variant";
    private final String apiUrlCat = "http://localhost:8080/api/category";
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
            ResponseEntity<CategoryViewModel[]> apiResponse = restTemp.getForEntity(apiUrlCat,
                    CategoryViewModel[].class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                view.addObject("categories", apiResponse.getBody());
            } else {
                throw new Exception("Fail get Category");
            }
        } catch (Exception e) {
            view.addObject("errorMessage", e.getMessage());
        }
        return view;
    }

    @PostMapping("/save")
    ModelAndView Save(@ModelAttribute VariantViewModel data) throws Exception {
        ModelAndView view = new ModelAndView("redirect:/variant");
        try {
            ResponseEntity<CategoryViewModel> apiResponse = restTemp.postForEntity(apiUrl, data,
                    CategoryViewModel.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("New Variant added");
            } else {
                throw new Exception("New Variant cannot be added");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return view;
    }
}
