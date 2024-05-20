package com.elthobhy.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elthobhy.javapos.models.Category;
import com.elthobhy.javapos.services.CategoryService;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/category")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Category> data = categoryService.getAll();
            return new ResponseEntity<List<Category>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public void Create(@RequestBody Category data) {
        categoryService.create(data);
    }

    @PutMapping("")
    public void Update(@RequestBody Category data) {
        categoryService.update(data);
    }

    @DeleteMapping("/{id}/{userId}")
    public void delete(@PathVariable final long id, @PathVariable int userId) {
        categoryService.delete(id, userId);
    }
}
