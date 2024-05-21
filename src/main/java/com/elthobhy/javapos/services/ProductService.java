package com.elthobhy.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Product;
import com.elthobhy.javapos.reposiotries.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public List<Product> getAll() throws Exception {
        try {
            List<Product> data = productRepo.findByDeleted(false).get();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Map<String, Object[]>> getAllNative() throws Exception {
        try {
            List<Map<String, Object[]>> data = productRepo.findAllNative().get();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public Product create(Product data) {
        Optional<Product> exist = productRepo.findByName(data.getName());
        if (exist.isEmpty()) {
            // create category
            return productRepo.save(data);
        } else {
            // cancel
            return new Product();
        }
    }

    public Product update(Product data) {
        Optional<Product> exist = productRepo.findById(data.getId());
        if (!exist.isEmpty()) {
            // update fields
            data.setCreateBy(exist.get().getCreateBy());
            data.setCreateDate(exist.get().getCreateDate());
            data.setDeleted(exist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());

            return productRepo.save(data);

        } else {
            // cancel
            return new Product();
        }
    }

    public Product delete(long id, int userId) {
        Optional<Product> exist = productRepo.findById(id);
        if (!exist.isEmpty()) {
            // update fields
            Product data = exist.get();
            data.setDeleted(true);
            data.setUpdateDate(LocalDateTime.now());
            data.setUpdateBy(userId);

            return productRepo.save(data);
        } else {
            // cancel
            return new Product();
        }
    }

    public Product getById(long id) throws Exception {
        return productRepo.findById(id).orElseThrow(() -> new Exception("Data not found"));
    }

    public List<Product> getByName(String name) throws Exception {
        // get category using part of category name
        return productRepo.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new Exception("data not found"));

    }
}
