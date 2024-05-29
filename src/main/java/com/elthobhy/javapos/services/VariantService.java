package com.elthobhy.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Category;
import com.elthobhy.javapos.models.Variant;
import com.elthobhy.javapos.reposiotries.CategoryRepository;
import com.elthobhy.javapos.reposiotries.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Variant> getAll() throws Exception {
        try {
            List<Variant> data = variantRepo.findByDeleted(false).get();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Variant has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Map<String, Object>> getAllNative() throws Exception {
        try {
            List<Map<String, Object>> data = variantRepo.findAllNative().get();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Variant has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public Variant create(Variant data) throws Exception {
        Optional<Variant> variantExist = variantRepo.findByName(data.getName());
        if (variantExist.isEmpty()) {
            // create category
            Optional<Category> catExist = categoryRepo.findById(data.getCategoryId());
            if (catExist.isPresent()) {
                return variantRepo.save(data);
            } else {
                throw new Exception("Category doesn't Exist");
            }
        } else {
            // cancel
            return new Variant();
        }
    }

    public Variant Update(Variant data) {
        Optional<Variant> exist = variantRepo.findById(data.getId());
        if (!exist.isEmpty()) {
            // update fields
            data.setCreateBy(exist.get().getCreateBy());
            data.setCreateDate(exist.get().getCreateDate());
            data.setDeleted(exist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());

            return variantRepo.save(data);

        } else {
            // cancel
            return new Variant();
        }
    }

    public Variant delete(long id, int userId) {
        Optional<Variant> exist = variantRepo.findById(id);
        if (!exist.isEmpty()) {
            // update fields
            Variant data = exist.get();
            data.setDeleted(true);
            data.setUpdateDate(LocalDateTime.now());
            data.setUpdateBy(userId);

            return variantRepo.save(data);
        } else {
            // cancel
            return new Variant();
        }
    }

    public Variant getById(long id) throws Exception {
        return variantRepo.findById(id).orElseThrow(() -> new Exception("Data not found"));
    }

    public Map<String, Object> getByIdNative(long id) throws Exception {
        return variantRepo.findByIdNative(id).orElseThrow(() -> new Exception("Data not found"));
    }

    public List<Variant> getByName(String name) throws Exception {
        // get category using part of category name
        return variantRepo.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new Exception("data not found"));
    }

    public List<Map<String, Object>> getByCategoryNameNative(String name) throws Exception {
        // get category using part of category name
        return variantRepo.findByCategoryNameNative(name)
                .orElseThrow(() -> new Exception("data not found"));
    }

    public List<Map<String, Object>> getByCategoryNameByIdNative(long id) throws Exception {
        // get category using part of category name
        return variantRepo.findByCategoryNameByIdNative(id)
                .orElseThrow(() -> new Exception("data not found"));
    }
}
