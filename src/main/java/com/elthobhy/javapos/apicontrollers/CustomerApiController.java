package com.elthobhy.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elthobhy.javapos.models.Customer;
import com.elthobhy.javapos.services.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerApiController {
    @Autowired
    private CustomerService CustomerService;

    @GetMapping("")
    public ResponseEntity<?> get() {
        try {
            List<Customer> data = CustomerService.getAll();
            return new ResponseEntity<List<Customer>>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
