package com.elthobhy.javapos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Customer;
import com.elthobhy.javapos.reposiotries.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository CustomerRepo;
    
    public List<Customer> getAll() throws Exception {
        try {
            List<Customer> data = CustomerRepo.findAll();
            if(data.size()>0){
                return data;
            }
            else throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Customer has no data");
        } catch (Exception e) {
            throw e;
        }
    }
}
