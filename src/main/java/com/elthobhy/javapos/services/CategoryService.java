package com.elthobhy.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Category;
import com.elthobhy.javapos.reposiotries.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;
    private Optional<Category> exist;

    public List<Category> getAll() throws Exception {
        try {
            List<Category> data = categoryRepo.findByDeleted(false).get();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Category has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public Category create(Category data) {
        this.exist = categoryRepo.findByName(data.getName());
        if (exist.isEmpty()) {
            // create category
            return categoryRepo.save(data);
        } else {
            // cancel
            return new Category();
        }

    }

    public Category update(Category data) {
        this.exist = categoryRepo.findById(data.getId());
        if (!exist.isEmpty()) {
            // update fields
            data.setCreateBy(exist.get().getCreateBy());
            data.setCraeteDate(exist.get().getCraeteDate());
            data.setDeleted(exist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());

            return categoryRepo.save(data);

        } else {
            // cancel
            return new Category();
        }
    }

    public Category delete(long id, int userId) {
        this.exist = categoryRepo.findById(id);
        if (!exist.isEmpty()) {
            // update fields
            Category data = exist.get();
            data.setDeleted(true);
            data.setUpdateDate(LocalDateTime.now());
            data.setUpdateBy(userId);

            return categoryRepo.save(data);
        } else {
            // cancel
            return new Category();
        }
    }

    public Category getById(long id) throws Exception {
        return categoryRepo.findById(id).orElseThrow(() -> new Exception("Data not found"));
    }

    public List<Category> getByName(String name) throws Exception {
        // get category using part of category name
        return categoryRepo.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new Exception("data not found"));

    }

    public List<Category> getByFilter(String name) throws Exception {
        // get category using part of category name
        return categoryRepo.findByNameOrDescriptionNative(name)
                .orElseThrow(() -> new Exception("data not found"));

    }
}
