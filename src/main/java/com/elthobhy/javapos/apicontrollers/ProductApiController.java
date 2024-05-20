package com.elthobhy.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elthobhy.javapos.models.Product;
import com.elthobhy.javapos.services.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductApiController {
    @Autowired
    private ProductService ProductService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Product> data = ProductService.getAll();
            return new ResponseEntity<List<Product>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
