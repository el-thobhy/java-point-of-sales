package com.elthobhy.javapos.reposiotries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elthobhy.javapos.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {// datatype dan primary key
    Optional<Customer> findByName(String name);

    Optional<Customer> findById(long id);

    Optional<List<Customer>> findByNameContainsIgnoreCase(String name); // jika cek kata yang banyak

    // // Optional<List<Customer>> findByDescriptionContainsIgnoreCase(String
    // description); // jika cek kata yang banyak

    // // Optional<List<Customer>>
    // findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name,
    // String description); // jika cek
    // kata yang
    // banyak
}
