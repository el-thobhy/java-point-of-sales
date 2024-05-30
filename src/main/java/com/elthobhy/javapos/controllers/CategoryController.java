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
            ResponseEntity<Category[]> apiResponse = restTemp.getForEntity(apiUrl + "/allNative", Category[].class);
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
    ResponseEntity<?> Save(@ModelAttribute CategoryViewModel data) {
        try {
            ResponseEntity<CategoryViewModel> apiResponse = restTemp.postForEntity(apiUrl, data,
                    CategoryViewModel.class);
            if (apiResponse.getStatusCode() == HttpStatus.CREATED) {
                System.out.println("New Category added");
                return new ResponseEntity<CategoryViewModel>(apiResponse.getBody(), apiResponse.getStatusCode());
            } else {
                throw new Exception("New Category cannot be added");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit/{id}")
    ModelAndView Edit(@PathVariable Long id) throws Exception {
        ModelAndView view = new ModelAndView("/category/edit");
        try {
            ResponseEntity<CategoryViewModel> apiResponse = restTemp.getForEntity(apiUrl + "/getById/" + id,
                    CategoryViewModel.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryViewModel cat = apiResponse.getBody();
                view.addObject("fill", cat);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ":" + apiResponse.getBody());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return view;
    }

    @PostMapping("/update")
    ResponseEntity<?> Update(CategoryViewModel data) {
        ResponseEntity<?> apiResponse = null;
        try {
            restTemp.put(apiUrl, data);
            apiResponse = restTemp.getForEntity(apiUrl + "/getById/" + data.getId(), CategoryViewModel.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            apiResponse = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return apiResponse;
    }

    @PostMapping("/deleteAction")
    ResponseEntity<?> Delete(CategoryViewModel data) {
        ResponseEntity<?> apiResponse = null;
        try {
            apiResponse = restTemp.getForEntity(apiUrl + "/getById/" + data.getId(), CategoryViewModel.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                restTemp.delete(apiUrl + "/" + data.getId() + "/" + data.getUpdateBy());
                apiResponse = new ResponseEntity<String>("Category ID " + data.getId() + " Deleted", HttpStatus.OK);
            } else
                throw new Exception("Category Id " + data.getId() + " cannot be deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            apiResponse = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return apiResponse;
    }

    @GetMapping("/delete/{id}")
    ModelAndView GetdataDelete(@PathVariable Long id) throws Exception {
        ModelAndView view = new ModelAndView("/category/delete");
        try {
            ResponseEntity<CategoryViewModel> apiResponse = restTemp.getForEntity(apiUrl + "/getById/" + id,
                    CategoryViewModel.class);
            if (apiResponse.getStatusCode() == HttpStatus.OK) {
                CategoryViewModel cat = apiResponse.getBody();
                view.addObject("fill", cat);
            } else {
                throw new Exception(apiResponse.getStatusCode().toString() + ":" + apiResponse.getBody());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return view;
    }
}
