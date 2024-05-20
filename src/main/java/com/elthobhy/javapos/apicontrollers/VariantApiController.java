package com.elthobhy.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elthobhy.javapos.models.Variant;
import com.elthobhy.javapos.services.VariantService;

@RestController
@RequestMapping("api/variant")
public class VariantApiController {
    @Autowired
    private VariantService variantService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Variant> data = variantService.getAll();
            return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public void Create(@RequestBody Variant data) {
        variantService.create(data);
    }
}
