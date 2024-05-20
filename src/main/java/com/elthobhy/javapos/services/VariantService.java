package com.elthobhy.javapos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Variant;
import com.elthobhy.javapos.reposiotries.VariantRepository;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepo;

    public List<Variant> getAll() throws Exception {
        try {
            List<Variant> data = variantRepo.findAll();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Variant has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public void create(Variant data) {
        Optional<Variant> categoryExist = variantRepo.findByName(data.getName());
        if (categoryExist.isEmpty()) {
            // create category
            variantRepo.save(data);
            throw new ResponseStatusException(HttpStatus.CREATED, "New Variant Created");
        } else {
            // cancel
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Variant Already Exist");
        }

    }
}
