package com.elthobhy.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elthobhy.javapos.models.Product;
import com.elthobhy.javapos.services.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Product> data = productService.getAll();
            return new ResponseEntity<List<Product>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) throws Exception {
        try {
            List<Product> data = productService.getByName(name);
            if (data.size() > 0) {
                return new ResponseEntity<List<Product>>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Product>>(data, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable final long id) {
        try {
            Product exist = productService.getById(id);
            if (exist.getId() > 0) {
                return new ResponseEntity<Product>(exist, HttpStatus.OK);
            } else
                return new ResponseEntity<String>("Product Not Found", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> Create(@RequestBody Product data) throws Exception {
        try {
            Product exist = productService.create(data);
            if (exist.getId() > 0) {
                return new ResponseEntity<Product>(exist, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Product Already Exist", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update(@RequestBody Product data) throws Exception {
        try {
            Product update = productService.update(data);
            if (update.getId() > 0) {
                return new ResponseEntity<Product>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Product does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<?> Delete(@PathVariable final long id, @PathVariable int userId) throws Exception {
        try {
            Product data = productService.delete(id, userId);
            if (data.getId() > 0) {
                return new ResponseEntity<Product>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Product does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
