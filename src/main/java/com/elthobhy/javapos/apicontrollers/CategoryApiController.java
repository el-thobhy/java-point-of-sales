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
            if (data.size() > 0) {
                return new ResponseEntity<List<Category>>(data, HttpStatus.OK);
            } else
                return new ResponseEntity<List<Category>>(data, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) throws Exception {
        try {
            List<Category> data = categoryService.getByName(name);
            if (data.size() > 0) {
                return new ResponseEntity<List<Category>>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Category>>(data, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable final long id) {
        try {
            Category exist = categoryService.getById(id);
            if (exist.getId() > 0) {
                return new ResponseEntity<Category>(exist, HttpStatus.OK);
            } else
                return new ResponseEntity<String>("Category Not Found", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> Create(@RequestBody Category data) throws Exception {
        try {
            Category exist = categoryService.create(data);
            if (exist.getId() > 0) {
                return new ResponseEntity<Category>(exist, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Category Already Exist", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update(@RequestBody Category data) throws Exception {
        try {
            Category update = categoryService.update(data);
            if (update.getId() > 0) {
                return new ResponseEntity<Category>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Category does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<?> Delete(@PathVariable final long id, @PathVariable int userId) throws Exception {
        try {
            Category data = categoryService.delete(id, userId);
            if (data.getId() > 0) {
                return new ResponseEntity<Category>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Category does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
