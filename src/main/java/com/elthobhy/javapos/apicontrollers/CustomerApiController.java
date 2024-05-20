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

import com.elthobhy.javapos.models.Customer;
import com.elthobhy.javapos.services.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerApiController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Customer> data = customerService.getAll();
            return new ResponseEntity<List<Customer>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) throws Exception {
        try {
            List<Customer> data = customerService.getByName(name);
            if (data.size() > 0) {
                return new ResponseEntity<List<Customer>>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<Customer>>(data, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable final long id) {
        try {
            Customer exist = customerService.getById(id);
            if (exist.getId() > 0) {
                return new ResponseEntity<Customer>(exist, HttpStatus.OK);
            } else
                return new ResponseEntity<String>("Customer Not Found", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> Create(@RequestBody Customer data) throws Exception {
        try {
            Customer exist = customerService.create(data);
            if (exist.getId() > 0) {
                return new ResponseEntity<Customer>(exist, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Customer Already Exist", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> Update(@RequestBody Customer data) throws Exception {
        try {
            Customer update = customerService.update(data);
            if (update.getId() > 0) {
                return new ResponseEntity<Customer>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Customer does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<?> Delete(@PathVariable final long id, @PathVariable int userId) throws Exception {
        try {
            Customer data = customerService.delete(id, userId);
            if (data.getId() > 0) {
                return new ResponseEntity<Customer>(data, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Customer does'nt exist", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
