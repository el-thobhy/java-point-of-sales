package com.elthobhy.javapos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Product;
import com.elthobhy.javapos.reposiotries.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository ProductRepo;
    
    public List<Product> getAll() throws Exception {
        try {
            List<Product> data = ProductRepo.findAll();
            if(data.size()>0){
                return data;
            }
            else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product has no data");
        } catch (Exception e) {
            throw e;
        }
    }
}
