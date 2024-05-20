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

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) throws Exception {
        try {
            List<Variant> data = variantService.getByName(name);
            if (data.size() > 0) {
                return new ResponseEntity<List<Variant>>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Variant>>(data, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable final long id) {
        try {
            Variant exist = variantService.getById(id);
            if (exist.getId() > 0) {
                return new ResponseEntity<Variant>(exist, HttpStatus.OK);
            } else
                return new ResponseEntity<String>("Variant Not Found", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> Create(@RequestBody Variant data) throws Exception {
        try {
            Variant var = variantService.create(data);
            if (var.getId() > 0) {
                return new ResponseEntity<Variant>(var, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Varian Already Exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Variant cannot be reached", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update(@RequestBody Variant data) throws Exception {
        try {
            Variant update = variantService.Update(data);
            if (update.getId() > 0) {
                return new ResponseEntity<Variant>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Variant does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<?> Delete(@PathVariable final long id, @PathVariable int userId) throws Exception {
        try {
            Variant data = variantService.delete(id, userId);
            if (data.getId() > 0) {
                return new ResponseEntity<Variant>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Variant does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
