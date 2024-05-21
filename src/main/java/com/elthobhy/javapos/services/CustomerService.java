package com.elthobhy.javapos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.elthobhy.javapos.models.Customer;
import com.elthobhy.javapos.reposiotries.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> getAll() throws Exception {
        try {
            List<Customer> data = customerRepo.findByDeleted(false).get();
            if (data.size() > 0) {
                return data;
            } else
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Customer has no data");
        } catch (Exception e) {
            throw e;
        }
    }

    public Customer create(Customer data) {
        Optional<Customer> CustomerExist = customerRepo.findByName(data.getName());
        if (CustomerExist.isEmpty()) {
            // create Customer
            return customerRepo.save(data);
        } else {
            // cancel
            return new Customer();
        }
    }

    public Customer update(Customer data) {
        Optional<Customer> exist = customerRepo.findById(data.getId());
        if (!exist.isEmpty()) {
            // update fields
            data.setCreateBy(exist.get().getCreateBy());
            data.setCreateDate(exist.get().getCreateDate());
            data.setDeleted(exist.get().isDeleted());
            data.setUpdateDate(LocalDateTime.now());

            return customerRepo.save(data);

        } else {
            // cancel
            return new Customer();
        }
    }

    public Customer delete(long id, int userId) {
        Optional<Customer> exist = customerRepo.findById(id);
        if (!exist.isEmpty()) {
            // update fields
            Customer data = exist.get();
            data.setDeleted(true);
            data.setUpdateDate(LocalDateTime.now());
            data.setUpdateBy(userId);

            return customerRepo.save(data);
        } else {
            // cancel
            return new Customer();
        }
    }

    public Customer getById(long id) throws Exception {
        return customerRepo.findById(id).orElseThrow(() -> new Exception("Data not found"));
    }

    public List<Customer> getByName(String name) throws Exception {
        // get Customer using part of Customer name
        return customerRepo.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new Exception("data not found"));

    }
}
